package genmicro

import is.common.util.StringEncrypter;

/**
 * This service class is used to proccess the  csv file with the users in it and insert them into the database
 * the main an public method is alta, ant then it uses methods enviaMail and processUserCreation
 * @author Armando Jaramillo Magallon
 */
class AltaUsuariosService {

    static transactional = true
	
	/**
	 * Reads the csv file, and for each line it insert the user in the database 
	 * and send  the confirmation email to such user
	 * @author Armando Jaramillo Magallon
	 * @param csvFile The name of the new micrositio
	 * @param micrositio The name of the micrositio
	 * @return A message with information about the result of the operation
	 */
	def alta(String csvFile, String micrositio) {
	def resultado ="todo bien"
	micrositio = micrositio.toLowerCase()
	new File(csvFile).toCsvReader(['skipLines':'4']).eachLine { tokens ->
			println tokens[0]
			resultado =tokens[1]
			String userId = tokens[2] as String
			userId = userId.substring(0, userId.indexOf("@"))	
			String encryptedPassword;
			
			def password = micrositio+ "123"
			
			// password is stored in encrypted mode in the registration table
			try {
				StringEncrypter encrypter = new StringEncrypter();
				encryptedPassword = encrypter.encrypt(password.trim());
			} catch (Exception e) {
				System.out.println("OmsUsersAction add: encryption failed with: " + e.toString());
				
			}
			
			try{							
				resultado = processUserCreation( userId,   encryptedPassword,  tokens[1],  tokens[2], tokens[0],  tokens[4],  tokens[5],  tokens[3],  micrositio)													
			}
			catch(Exception e) {
				println "error al dar de alta ussuario  ${tokens[1]}"
				log.error(e.getMessage())
				resultado= "algo falló"
			}
			if(resultado)
				enviaMail(tokens[2],tokens[1],userId,password,micrositio)						
		}
		return resultado
		//resultado = csvFile+micrositio
    }
	
	
	/**
	 * Sends the confirmation email to the added user,using the email plugin
	 * @author Armando Jaramillo Magallon
	 * @param toMail The user´s mail
	 * @param nombre The name of the user
	 * @param user The User ID
	 * @param password the password for this user (it´s actually the same for ell the users of the micriositio)
	 * @param micrositio The name of the micrositio
	 */
	private void enviaMail(String toMail, String nombre,String user, String password, String micrositio) {		
		try {
			def contenido //this stores the content of the email to be sent
			contenido="""
	Estimado ${nombre}


Bienvenido a su Sitio personalizado de compras MRO https://www.grainger.com.mx/${micrositio}
			
A continuación encontrará la información de su acceso: 
			
		Usuario: ${user}
		Contraseña: ${password}
			 		
Le sugerimos cambiar su contraseña dentro del sitio por una personalizada

			
Si requieren de cualquier soporte o ayuda estamos a sus ordenes   en el teléfono 01800 800 8080 

			
			"""		
			//the email is sent usingthe grails email plugin
			sendMail {
				  multipart true
				  to toMail
				  subject "Bienvenido al Sitio de compras MRO ${micrositio} - Grainger"
				  body contenido				 
			}
		} catch (Exception e) {
			println "error al enviar mail a ${toMail}"
			log.error(e.getMessage())
		}
	}
	
	
	/**
	 * Saves the User record in both databases: the website database and the ERP database
	 * it uses the plugin Datasources in order to save in two databases
	 * @author Armando Jaramillo Magallon
	 * @userId
	 * @param password the password for this user (it´s actually the same for ell the users of the micriositio)
	 * @param userName The name of the user
	 * @param userEmail The user´s mail
	 * @param userType, 
	 * @param buyLimit, the amount in pesos that the user is allowed to buy
	 * @param approveLimit, the amount in pesos that the user is allowed to approve (from another user request)
	 * @param defaultApprover. the default approver for this user (it has to be an user from this micrositio)
	 * @param micrositio The name of the micrositio
	 */
	private String processUserCreation(String userId,  String password, String userName, String userEmail,  String userType, String buyLimit, String approveLimit, String defaultApprover, String micrositio){
		log.debug('-Entering processUserCreation method')
		String result ="";
		String clientCode
		String custCode
		String shipToCode
		
		//retrieves the admin user of the microsite (this user is not in the list, it already exists)
		// for the sake ot performance, maybe it was not a good idea retrieving the user inside this method
		def adminUser = Omsuserr.findByUserId(micrositio)
		clientCode = adminUser.clientCode
		custCode = adminUser.custCode
		shipToCode = adminUser.shipToCode
		
		//retrieves the user to see if the user exists
		def oldUser = Omsuserr.findByUserIdAndClientCode(userName, clientCode)
		
		if(!oldUser){   // if the user did not exist previously
			
			//the domain object is created with the parameters passed to the method
			User newUser = new User(new Long(0),userId,userType,userName,userEmail,custCode,'Y',shipToCode,'Y',clientCode,null,'Y',buyLimit as Long,approveLimit as Long,defaultApprover,'N',password,'A',new Date(),'xamj003')
			
			//inserts into the website database
			if( !newUser.save(flush:true)	 ) {
				println '**There were error while trying to save the new user:'
				newUser.errors.each {
					 println it
				}
				println '**'
			}			
			else{
				log.debug("Inserted  user: $userId")
				println "Inserted  user: $userId"
				result = "Inserted  user: $userId"
				newUser.discard()
			}
			
			//the domain object is created with the parameters passed to the method
			Omsuserr newOmsUser = new Omsuserr(new Long(0),userId,userType,userName,userEmail,custCode,'Y',shipToCode,'Y',clientCode,null,'Y',buyLimit as Long,approveLimit as Long,defaultApprover,'N',password,'A',new Date(),'xamj003')
			
			//inserts into the ERP database
			if( !newOmsUser.save(flush:true)	 ) {
				println '**There were error while trying to save the new Omsuser:'
				newOmsUser.errors.each {
					 println it
				}
				println '**'
			}
			else{
				log.debug("Inserted  Omsuser: $userId")
				println "Inserted  Omsuser: $userId"
				result += "Inserted  Omsuser: $userId"
				newOmsUser.discard()
				
			}
			
		}		
		else {
			println 'The user already existed in the db'
			//result = "The user already existed in the db"
		}   

			
			return result
	}
}