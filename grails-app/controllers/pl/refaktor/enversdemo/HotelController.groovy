package pl.refaktor.enversdemo

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

@Transactional
class HotelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hotelInstanceList: Hotel.list(params), hotelInstanceTotal: Hotel.count()]
    }

    def revisions = {
        def hotelInstanceList = Hotel.findAllRevisions()
        render(view: 'revisions', model: [hotelInstanceList: hotelInstanceList, hotelInstanceTotal: hotelInstanceList.size()])
    }

    def create() {
        [hotelInstance: new Hotel(params)]
    }

    def save() {
        def hotelInstance = new Hotel(params)
        if (!hotelInstance.save(flush: true)) {
            render(view: "create", model: [hotelInstance: hotelInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'hotel.label', default: 'Hotel'), hotelInstance.id])
        redirect(action: "show", id: hotelInstance.id)
    }

    def show(Long id) {
        def hotelInstance = Hotel.get(id)
        if (!hotelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "list")
            return
        }

        [hotelInstance: hotelInstance]
    }

    def edit(Long id) {
        def hotelInstance = Hotel.get(id)
        if (!hotelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "list")
            return
        }

        [hotelInstance: hotelInstance]
    }

    def update(Long id, Long version) {
        def hotelInstance = Hotel.get(id)
        if (!hotelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (hotelInstance.version > version) {
                hotelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'hotel.label', default: 'Hotel')] as Object[],
                        "Another user has updated this Hotel while you were editing")
                render(view: "edit", model: [hotelInstance: hotelInstance])
                return
            }
        }

        hotelInstance.properties = params

        if (!hotelInstance.save(flush: true)) {
            render(view: "edit", model: [hotelInstance: hotelInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hotel.label', default: 'Hotel'), hotelInstance.id])
        redirect(action: "show", id: hotelInstance.id)
    }

    def delete(Long id) {
        def hotelInstance = Hotel.get(id)
        if (!hotelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "list")
            return
        }

        try {
            hotelInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hotel.label', default: 'Hotel'), id])
            redirect(action: "show", id: id)
        }
    }
}
