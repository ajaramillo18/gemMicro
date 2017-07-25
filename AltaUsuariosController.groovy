package genmicro

class AltaUsuariosController {

	def altaUsuariosService
    def index = { redirect(action:iniciaCarga)}
	def iniciaCarga =	{
				
	}
	def altaUsuarios = {
		
		
		def resultado
		resultado = altaUsuariosService.alta(params.csvFile, params.micrositio)
		
		redirect(action:'iniciaCarga', params: [resultado: "${resultado}"])
		
	}
	
}
