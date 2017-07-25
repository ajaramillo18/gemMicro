<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Alta masiva de Usuarios</title>
</head>
<body>
  <div class="body">
  Alta masiva de Usuarios  
  	<g:form>  
	  <table>
	  	<tbody>
	
	  		<tr>	  			
	  			<!--  <td>Archivo CSV :</td><td><input type="file" name="csvFile" size ="100"/></td> -->
	  			<td>Archivo CSV :</td><td><g:textField name="csvFile" size ="100"/></td>
	  		</tr>
	  		<tr>	
	  			<td>Nombre Micrositio :</td><td><g:textField name="micrositio" /></td>
	  		</tr>
	  		<tr>	
	  			<td>resultado :</td><td>${params.resultado}</td>
	  		</tr> 
	  		<tr>
	  			<td colspan="4"><g:actionSubmit value="Alta" action="altaUsuarios" /></td>	
	  		</tr>
	  	</tbody>
	  </table>
	</g:form>  
  </div>
</body>
</html>