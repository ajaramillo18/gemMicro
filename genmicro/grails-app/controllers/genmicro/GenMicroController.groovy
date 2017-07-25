package genmicro

class GenMicroController {

    def index = { redirect(action:welcome) }	
	
	def micrositio = {
		def copyService = new CopyService()
		def customCodes = params.customItemCodes != null		
		def holdPO = params.holdpo != null
		
		[name: "${params.newName}" , action: "${copyService.copy(params.newName?.trim(),params.oldName?.trim(),params.oldFormalName?.trim(),params.newFormalName?.trim(),params.oldClientCode?.trim(),params.newClientCode?.trim(),customCodes,params.multiClientText?.trim() ?: null, params.custCode, params.shipToCode, holdPO,params.nombreContacto?.trim(),params.correoContacto?.trim(),params.extContacto?.trim())}"]			
	}
	
	def welcome = {
		
	}
}
