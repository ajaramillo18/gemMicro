<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Welcome to the Microsite Generator</title>
<g:javascript library="prototype" />
<g:javascript>
<%--	function addMultiClient(element) {--%>
<%--		if($("multiClient").innerHTML.indexOf("name=\"multipleClientNum\"") > -1--%>
<%--		   && element.checked){--%>
<%--			$("multiClient").insert("<textField name=\"multiClientText\" />");--%>
<%--		}--%>
<%--		--%>
<%--		else if(!element.checked){			--%>
<%--			$("multiClient").update("");		--%>
<%--		}		--%>
<%--	}--%>
	
	function addMultiClient(element) {
		if(element.checked){
			$("multiClientLabel").show();
			$("multiClient").show();
			$("multiClientText").clear();
		}
		
		else {			
			$("multiClientLabel").hide();
			$("multiClient").hide();	
		}		
	}	
	
	function selectType(tipo){
		if(tipo == 'nuevo'){
			$('oldname').value = '';
			$('oldformalname').value = ''; 
			$('oldclientcode').value = '';
		}
		
		else if(tipo == 'express'){
			$('oldname').value = 'msexpress';
			$('oldformalname').value = 'MicroSitio Express Edition';
			$('oldclientcode').value = 'MSX';
		}
		
		else if(tipo == 'dragamex'){
			$('oldname').value = 'dragamex';
			$('oldformalname').value = 'Dragamex'; 
			$('oldclientcode').value = 'DMX';
		}	
	}
</g:javascript>
</head>
<body>
  <div class="body">
  Generate Micrositio:
	<g:form>  
	  <table>
	  	<tbody>
	  		<tr>
	  			<td colspan="4">Type:&nbsp;
	  				<g:radio name="tipo" value="Nuevo" onClick="selectType('nuevo')" checked="true" />Nuevo 
	  	  			<g:radio name="tipo" value="Express" onClick="selectType('express')" />Express
	      			<g:radio name="tipo" value="Dragamex" onClick="selectType('dragamex')" />Dragamex
	  			</td>  				
	  		</tr>
	  		<tr>
	  			<td>Old Name:</td><td><g:textField id="oldname" name="oldName" /></td>
	  			<td>New Name:</td><td><g:textField name="newName" /></td>
	  		</tr>
	  		<tr>
	  			<td>Old Formal Name:</td><td><g:textField id="oldformalname" name="oldFormalName" /></td>
	  			<td>New Formal Name:</td><td><g:textField name="newFormalName" /></td>
	  		</tr>  	
	  		<tr>
	  			<td>Old Client Code:</td><td><g:textField id="oldclientcode" name="oldClientCode" /></td>
	  			<td>New Client Code:</td><td><g:textField name="newClientCode" /></td>
	  		</tr>  
	  		<tr>
	  			<td>Cust Code:</td><td><g:textField name="custCode" /></td>
	  			<td>Shipto Code:</td><td><g:textField name="shipToCode" /></td>
	  		</tr>  	  		
	  		<tr>
	  			<td colspan="4" valign="middle">Custom Item Codes:&nbsp;<g:checkBox name="customItemCodes" checked="false" /></td>  			
	  		</tr> 
	  		<tr>
	  			<td colspan="4" valign="middle">Hold PO:&nbsp;<g:checkBox name="holdpo" checked="false" /></td>  			
	  		</tr> 
	  		<tr>
	  			<td colspan="4">Multiple Client Numbers:&nbsp;<g:checkBox name="multipleClientNum" checked="false" onClick="addMultiClient(this)" /></td>
	  		</tr>
	  		<tr>
	  			<td colspan="4">
	  				<div id="multiClientLabel" style="display: none;">Enter Client numbers (planta1:custcode1,planta2:custcode2,...):</div>
	  				<div id="multiClient" style="display: none;"><g:textField name="multiClientText" size="100" /></div>			
	  			</td>  			
	  		</tr>     		
	  		<tr>
	  			<td colspan="4">
	  			Enter contact data:	
	  			</td>   		
	  		</tr>
	  		<tr>
	  			
	  			<td>Nombre :</td><td><g:textField name="nombreContacto" /></td>
	  			<td>Correo :</td><td><g:textField name="correoContacto" /></td>
	  			<td>Extension :</td><td><g:textField name="extContacto" /></td>
	  		</tr> 
	  		<tr>
	  			<td colspan="4"><g:actionSubmit value="generate" action="micrositio" /></td>	
	  		</tr>
	  	</tbody>
	  </table>
	</g:form>  
  </div>
</body>
</html>