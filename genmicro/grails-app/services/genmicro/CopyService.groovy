package genmicro

import java.io.File;
import genmicro.tools.Utils
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.lang.time.DateFormatUtils;

class CopyService {

    static transactional = true
	String baseSource = ApplicationHolder.application.config.micrositio.origen
	def baseDestination = ApplicationHolder.application.config.micrositio.destino
	def adminUserEmail = ApplicationHolder.application.config.admin.user.email
	def binaryExts = [".pdf", ".zip", ".rar", ".exe", ".xls", ".doc", ".xlsx", ".docx", ".ppt", ".pptx"]
	def dirScanned = 0
	def fileScanned = 0
	def dirCopied = 0
	def fileCopied = 0

    def String copy(String newName, String oldName, String oldFormalName, String newFormalName, String oldClientCode, String newClientCode, boolean customItemCodes, String multipleClientNum, String custCode, String shipToCode, boolean holdPO, String nombreContacto, String correoContacto, String extContacto) {
		log.debug('-Entering copy method')
		File source = new File(baseSource)
		File destination = new File(baseDestination)	
		
		def result = processCopy(source, destination, newName, oldName)
		
		def replacer = ["$oldName" : newName, "$oldFormalName" : "$newFormalName", (oldClientCode.toUpperCase()) : newClientCode.toUpperCase(), "$oldClientCode" : newClientCode, holdpo : holdPO]				  	    
		
		if(!customItemCodes){
			replacer["dmx2"] = "oms"  
		}	
		
		if(multipleClientNum?.trim()){
			replacer["multipleClient"] = multipleClientNum
		}
		
		if(nombreContacto?.trim()){
			replacer["nombreContacto"] = nombreContacto
		}
		if(correoContacto?.trim()){
			replacer["correoContacto"] = correoContacto
		}
		if(extContacto?.trim()){
			replacer["extContacto"] = extContacto
		}
		
		
		
		processReplacements(destination, replacer)		
		result += processUserCreation(newName, adminUserEmail, custCode, shipToCode, newClientCode)
		result += buildShellCommands(newName, newClientCode)
		
		processUserCreationWEB(newName, adminUserEmail, custCode, shipToCode, newClientCode)

		return result
    }

	/**
	 * Process the copy of files for the micrositio
	 * @author Ramiro Serrato Paniagua
	 * @param source The directory where the base micrositio is
	 * @param destination The directory where the new micrositio is being created
	 * @param name The name of the new micrositio
	 * @return The verbose result output for the method
	 */
	private String processCopy(File source, File destination, String name, String oldName){
		log.debug('-Entering processCopy method')
		def firstDirBeenProcessed = null  // flag which indicates if the first directory has been processed
		dirScanned = 0
		fileScanned = 0
		dirCopied = 0
		fileCopied = 0
		
		copyFILE source,destination  // this creates the destination folder if it does not exist
		
		// Now recursively inside the directory 
		source.eachFileRecurse{
			if(!it.canonicalPath.contains(".svn") && it.canonicalPath.contains(oldName)){ // not copying svn folders, and from all the other ones copying only those related to the actual basis micrositio
				String newDestPath = it.canonicalPath.replaceAll(Utils.escapeRegex(File.separator), "/").replace(baseSource, baseDestination) // replace the actual file separator with a slash and then the old base directory name with the new one
				
				if(firstDirBeenProcessed){  // we need to save the files from the old micrositio's directory to the appropriate new micrositio's folder
					newDestPath = newDestPath.replaceAll(Utils.escapeRegex(baseDestination + "/" + firstDirBeenProcessed.name),baseDestination + "/" + name)
				}
				
				else if(it.isDirectory()){  // lets rename the first and base directory for the micrositio
					firstDirBeenProcessed = it
					newDestPath = baseDestination + "/" + name // this assuming that only one directory (the micrositio's one) is at the first level
				}										
				
				def destFile = new File(newDestPath)
				copyFILE it,destFile							
			}
			
			else{
				println "Skipped file ${it.canonicalPath}"
			}
		}
		
		def micrositioName = firstDirBeenProcessed.name
		println "Name of the micrositio's folder: $micrositioName"
		
		// we need to rename the <oldname>loging.jsp to <newname>login.jsp
		def loginFile = new File(baseDestination+"/"+micrositioName+"login.jsp").renameTo(baseDestination+"/"+name+"login.jsp")
		
		def result = """
**********************************************
Summary:
${dirScanned+fileScanned} total Files scanned:
- $dirScanned directories
- $fileScanned regular files
${dirCopied+fileCopied} total Files copied:
- $dirCopied directories
- $fileCopied regular files
"""
		println result
		return result
	}
	
	/**
	 * Process the text replacements in all the micrositio
	 * @author Ramiro Serrato Paniagua
	 * @param directory The directory where the new micrositio was copied
	 * @param replacer The map with the replacement pairs
	 */
	private String processReplacements(File directory, Map replacer){
		log.debug('-Entering processReplacements method')
		println "**********************************************\nProcessing replacements..."
		println "Searching for replacements: $replacer"
		
		directory.eachFileRecurse groovy.io.FileType.FILES, { 
			replaceTextInFile(it, replacer)
		}		
		
		println "Replacements completed"
	}
		
	/**
	* Copies a file (directory or regular file) 
	* @author Ramiro Serrato Paniagua
	* @param source
	* @param destination
	*/
   private void copyFILE(File source, File destination) throws Exception{	  
	   log.debug('-Entering copyFILE method')
	   if(source.isDirectory()){
		   print "copying directory ${destination.canonicalPath}..."
		   dirScanned++					   
		   
		   if(!destination.mkdir()){
			   println "FAILED"
		   }
		   
		   else {
			   dirCopied++
			   println "OK"
		   }		   
	   }
	   
	   else{
		   print "copying file ${destination.canonicalPath}..."
		   fileScanned++
		   
		   try{   		  
			   destination.delete()  // lets be sure that file does not exist previously			   
			   
			   if(isBinary(source)){
				   print "Binary file..."
				   new FileOutputStream(destination).withStream { it << source.newInputStream() }
			   }
			   
			   // for text files
			   else{	
				   print "Text file..."
				   destination << source.asWritable("ISO-8859-1")
			   }
			   
			   fileCopied++
			   println "OK"
		   }

		   catch(Exception e){
			   println "FAILED:"
			   println e
		   }
	   }
   }      
   
   /**
   * Copies a Directory with all its contents
   * @author Ramiro Serrato Paniagua
   * @param source
   * @param destination
   * @deprecated
   */
  private void copyDirectory(File source, File destination) throws Exception{
	  log.debug('-Entering copyDirectory method')
	  String originalSource = source.canonicalPath.replaceAll(Utils.escapeRegex(File.separator),"/");
	  String destPath = originalSource.replace(baseSource, baseDestination)
	  
	  if(source.isDirectory()){
		  print "copying directory ${destination.canonicalPath}..."
		  dirScanned++
					  
		  File newDirectory = new File(destPath)
		  
		  if(!newDirectory.mkdir()){
			  println "FAILED"
		  }
		  
		  else {
			  dirCopied++
			  println "OK"
		  }
		  
		  source.eachFile{
			  String newDestPath = it.canonicalPath.replaceAll(Utils.escapeRegex(File.separator), "/").replace(baseSource, baseDestination);
			  def destFile = new File(newDestPath)
			  copyDirectory it,destFile
		  }
	  }
	  
	  else{
		  print "copying file ${destination.canonicalPath}..."
		  fileScanned++
		  
		  try{
			  copyFile source,destination
			  fileCopied++
			  println "OK"
		  }
		  
		  catch(Exception e){
			  println "FAILED"
		  }
	  }
  }
  
  /**
   * Copies a regular file
   * @author Ramiro Serrato Paniagua
   * @param source
   * @param destination
   * @deprecated
   */
  private void copyFile(File source, File destination){
	  log.debug('-Entering copyFile method')
	  destination << source.asWritable()
  }
   
   /**
    * Replaces text inside a file
    * @author Armando Jaramillo Magallon
    * @param target The target File
    * @param replace A map with the replacement data: [<toReplace>:<replacement>, ...]
    */
   private void replaceTextInFile(File target, Map replacer){
	   log.debug('-Entering replaceTextInFile method')
	   if(!isBinary(target)) { // if it is not a binary file
		   String result = processIndividualCase(target, replacer) // first we look for specific file cases   		   		   
		   
		   replacer.each {  // now we replace e
			   if(it.key != "multipleClient" && it.key != "holdpo"){
				   def prev = result                                                       
				   result = result.replaceAll(new String(Utils.escapeRegex(it.key).getBytes(),"ISO-8859-1"), it.value) //  
				   
				   if(prev != result){
					   println "${target.canonicalPath} Pattern ($it.key) was found and replaced by ($it.value)"
				   }
			   }
		   }
		   
		   target.write(result,"ISO-8859-1")	   
	   }	   
   }
   
   /**
    * Process individual replacements for specific files
    * @author Ramiro Serrato Paniagua
    * @param target The target file
    * @param replacer The map with the replacements
    * @return The String with the text to be updated in the file
    */
   private String processIndividualCase(File target, Map replacer){
	   log.debug('-Entering processIndividualCase method')
	   String result = target.text
	   
	   if(target.name == "selcostcenter.jsp" || target.name == "setcompania.jsp"){
		   int ind = result.indexOf("if (compania.equals(\"SB00010\")) {")
		   int ind1 = result.indexOf("else {", ind)
		   int ind2 = result.indexOf("}", ind1)
		   
		   if(ind > -1){
			   if(replacer["multipleClient"]){
				   LinkedHashMap.metaClass.leftShift << { String it -> def v = it.split(','); v.each { putAt(*it.split(':')) }}
				   
				   def ifBlock = ""
				   def multipleClients = [:]
				   multipleClients << replacer["multipleClient"];
				   
				   multipleClients.eachWithIndex { planta, custCode, i ->
					   if(i==0) { ifBlock += "\n if(compania.equals(\"$planta\")) { ui.setCustCode(\"$custCode\"); }\n" }
					   else { ifBlock += "else if(compania.equals(\"$planta\")) { ui.setCustCode(\"$custCode\"); }\n" }
				   }
				   
				   if(ifBlock){
					   ifBlock += "else { ui.setCustCode(\"\"); }\n"
				   }
					   			   
				   result = result[0..<ind] << ifBlock << result[ind2+1..<result.length()] // building the if else code block for the jsp
				   println "${target.canonicalPath} Multiple client code was added"
			   }
			   
			   else{
			   		result = result[0..<ind] << result[ind2+1..<result.length()] // removing the if else code block from the jsp
					println "${target.canonicalPath} Multiple client code was removed"
			   }
		   }
		   
		   else{
			   println "Warning: The code for multiple client was not found in the source in: ${target.name}"
		   }
	   }	 
	   
	   if(target.name.startsWith("system.properties")){
		   if(replacer["holdpo"]){
			   println "**HoldPO flag was added"
			   result += "\npoHold=PO"
		   }
		   if(replacer["nombreContacto"]){
			   println "**nombreContacto flag was added"
			   result += "\norder.cc.sales.name="+replacer["nombreContacto"]
		   }
		   if(replacer["correoContacto"]){
			   println "**correoContacto flag was added"
			   result += "\norder.cc.sales="+replacer["correoContacto"]
		   }
		   if(replacer["extContacto"]){
			   println "**extContacto flag was added"
			   result += "\norder.cc.sales.phone=01 800 800 8080 ext "+replacer["extContacto"]
		   }
		   		   
	   }  	   
	   
	   return result
   }
   
   /**
    * Determines if a file is binary or not, based in the extension of the file
    * @author Armando Jaramillo Magallon
    * @param target The file to be analyzed
    * @return true/false if the file is binary or not
    */
   private boolean isBinary(File target){
	   log.debug('-Entering isBinary method')
	   def isBin = false
	   
	   for(String ext in binaryExts) {
			isBin = target.canonicalPath.contains(ext)
			if(isBin) break;
	   }
	   
	   return isBin
   }

   /**
    * Creates the base admin user of the micrositio
    * @author Armando Jaramillo Magallon
    * @param newName The name of the new micrrositio
    * @param adminUserEmail The email of the new administrator user
    * @param custCode The cust code which the user is linked to
    * @param shipToCode The shipto code assigned to the user
    * @param newClientCode The 3 letters code for the micrositio
    * @return A message with information about the result of the operation
    */
   private String processUserCreation(String newName, String adminUserEmail, String custCode, String shipToCode, String newClientCode){
	   log.debug('-Entering processUserCreation method')
	   String result ="";
	   def oldUser = Omsuserr.findByUserId(newName)
	   
	   if(!oldUser){   // if the user did not exist previously
		   Omsuserr adminUser = new Omsuserr(new Long(0),newName,'A',"${newName.toUpperCase()} Admin",'ramiro.serrato@grainger.com',custCode,'Y',shipToCode,'Y',newClientCode,null,'Y',new Long(0),new Long(500000),newName,'N','wdwUT+RKKFcvBNq1sLMPZQ==','A',new Date(),'xrps002')
		    	   
		   if( !adminUser.save(flush:true)	 ) {
			   println '**There were error while trying to save the new user:'
			   adminUser.errors.each {
					println it
			   }
			   println '**'
			}
		   
		   else{
			   log.debug("Inserted admin user: $adminUser")
			   println "Inserted admin user: $adminUser"
		   }
	   }
	   
	   else {
		   println 'The user already existed in the db'
	   }
  
	   
	   def user = Omsuserr.findByUserId(newName)
	   	   
	   String query = "insert into omsuserr "
	   
	   if(user){
		   query += "values (${user.uid},'$newName','A'," + "'" + newName.toUpperCase() + " Admin','ramiro.serrato@grainger.com','$custCode','Y','$shipToCode','Y','$newClientCode',NULL,'Y',0,500000,'$newName','N','wdwUT+RKKFcvBNq1sLMPZQ==','A','${DateFormatUtils.format(new Date(), 'yyyy-MM-dd')}','xrps002')"
	   }
	   
	   else{
		   query += "values (0,'$newName','A'," + "'" + newName.toUpperCase() + " Admin','ramiro.serrato@grainger.com','$custCode','Y','$shipToCode','Y','$newClientCode',NULL,'Y',0,500000,'$newName','N','wdwUT+RKKFcvBNq1sLMPZQ==','A','${DateFormatUtils.format(new Date(), 'yyyy-MM-dd')}','xrps002')" 
	   }
	   
	   result = 
"""
...${user?'The user has been created in FOURGEN DEV/QA database':'You need to create the user in fourgen qa with the following query:'}

The query to be executed in ${user?'website dev and qa db is':'fourgen qa is'}:
_____________________________________________________
$query
_____________________________________________________
"""	    

   		println result
   		return result
   }
   
   private String buildShellCommands(String newName, String newClientCode){
	   log.debug('-Entering buildShellCommands method')
	   String commands = ""

	   commands = 
"""
The following commands must be executed in dev/qa shell, after uploading the micrositio

In the /local2/grainger/wls61sp7/(dev or stg)/wwg folder:
___________________________________________________
ln -s $newName ${newClientCode.toLowerCase()}
ln -s ${newName}login.jsp ${newClientCode.toLowerCase()}login.jsp 
___________________________________________________
"""   		

	   println commands
	   return commands
   }
   
   /**
   * Creates the base admin user of the micrositio in the WEB DB
   * @author Armando Jaramillo Magallon
   * @param newName The name of the new micrrositio
   * @param adminUserEmail The email of the new administrator user
   * @param custCode The cust code which the user is linked to
   * @param shipToCode The shipto code assigned to the user
   * @param newClientCode The 3 letters code for the micrositio
   * @return A message with information about the result of the operation
   */
  private String processUserCreationWEB(String newName, String adminUserEmail, String custCode, String shipToCode, String newClientCode){
	  log.debug('-Entering processUserCreation WEB method')
	  String result ="";
	  def oldUser = User.findByUserId(newName)
	  
	  if(!oldUser){   // if the user did not exist previously
		  User adminUser = new User(new Long(0),newName,'A',"${newName.toUpperCase()} Admin",'ramiro.serrato@grainger.com',custCode,'Y',shipToCode,'Y',newClientCode,null,'Y',new Long(0),new Long(500000),newName,'N','RW6n2x+vS3U=','A',new Date(),'xrps002')
				  
		  if( !adminUser.save(flush:true)	 ) {
			  println '**There were error while trying to save the new user:'
			  adminUser.errors.each {
				   println it
			  }
			  println '**'
		   }
		  
		  else{
			  log.debug("Inserted admin user: $adminUser")
			  println "Inserted admin user: $adminUser"
		  }
	  }
	  
	  else {
		  println 'The user already existed in the db'
	  }
 
	  
	  def user = User.findByUserId(newName)
			 
	  String query = "insert into omsuserr "
	  
	  if(user){
		  query += "values (${user.uid},'$newName','A'," + "'" + newName.toUpperCase() + " Admin','ramiro.serrato@grainger.com','$custCode','Y','$shipToCode','Y','$newClientCode',NULL,'Y',0,500000,'$newName','N','RW6n2x+vS3U=','A','${DateFormatUtils.format(new Date(), 'yyyy-MM-dd')}','xrps002')"
	  }
	  
	  else{
		  query += "values (0,'$newName','A'," + "'" + newName.toUpperCase() + " Admin','ramiro.serrato@grainger.com','$custCode','Y','$shipToCode','Y','$newClientCode',NULL,'Y',0,500000,'$newName','N','RW6n2x+vS3U=','A','${DateFormatUtils.format(new Date(), 'yyyy-MM-dd')}','xrps002')"
	  }
	  
	  result =
"""
...${user?'The user has been created in wwgdev DEV/QA database':'You need to create the user in fourgen qa with the following query:'}

The query to be executed in ${user?'website dev and qa db is':'fourgen qa is'}:
_____________________________________________________
$query
_____________________________________________________
"""

		  println result
		  return result
  }
   
}
