package genmicro

/**
 * This controller class is used to control the flow of the alta usuarios special feature of the genmicro application
 * @author Armando Jaramillo Magallon
 */
class AltaUsuariosController {

	def altaUsuariosService //this is the service class
	
	// redirects the default url to the iniciaCarga action
    def index = { redirect(action:iniciaCarga)}
	
	// default action for showing the iniciaCarga.gsp view
	def iniciaCarga =	{
				
	}
	
	//this action is called in the iniciaCarga.gsp view. In the submit button of the g form
	def altaUsuarios = {
		
		
		def resultado // it stores the results of the service class and is passed to the gsp
		
		//calls the methor alta, the csv file with the list of users is passed, along with the name of the micriosite
		resultado = altaUsuariosService.alta(params.csvFile, params.micrositio)
		
		//shows again the gsp iniciaCarga to present the results of the operation
		redirect(action:'iniciaCarga', params: [resultado: "${resultado}"])
		
	}
	
}
