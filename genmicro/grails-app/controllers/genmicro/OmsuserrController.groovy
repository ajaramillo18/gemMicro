package genmicro

class OmsuserrController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
		def oldUser = Omsuserr.findAllByClientCode("PEP")
		//oldUser.
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [omsuserrInstanceList: Omsuserr.findAllByClientCode("PEP"), omsuserrInstanceTotal: oldUser.count()]
    }

    def create = {
        def omsuserrInstance = new Omsuserr()
        omsuserrInstance.properties = params
        return [omsuserrInstance: omsuserrInstance]
    }

    def save = {
        def omsuserrInstance = new Omsuserr(params)
        if (omsuserrInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), omsuserrInstance.id])}"
            redirect(action: "show", id: omsuserrInstance.id)
        }
        else {
            render(view: "create", model: [omsuserrInstance: omsuserrInstance])
        }
    }

    def show = {
        def omsuserrInstance = Omsuserr.get(params.id)
        if (!omsuserrInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
            redirect(action: "list")
        }
        else {
            [omsuserrInstance: omsuserrInstance]
        }
    }

    def edit = {
        def omsuserrInstance = Omsuserr.get(params.id)
        if (!omsuserrInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [omsuserrInstance: omsuserrInstance]
        }
    }

    def update = {
        def omsuserrInstance = Omsuserr.get(params.id)
        if (omsuserrInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (omsuserrInstance.version > version) {
                    
                    omsuserrInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'omsuserr.label', default: 'Omsuserr')] as Object[], "Another user has updated this Omsuserr while you were editing")
                    render(view: "edit", model: [omsuserrInstance: omsuserrInstance])
                    return
                }
            }
            omsuserrInstance.properties = params
            if (!omsuserrInstance.hasErrors() && omsuserrInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), omsuserrInstance.id])}"
                redirect(action: "show", id: omsuserrInstance.id)
            }
            else {
                render(view: "edit", model: [omsuserrInstance: omsuserrInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def omsuserrInstance = Omsuserr.get(params.id)
        if (omsuserrInstance) {
            try {
                omsuserrInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'omsuserr.label', default: 'Omsuserr'), params.id])}"
            redirect(action: "list")
        }
    }
}
