package pl.refaktor.enversdemo

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

@Transactional
class BookingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bookingInstanceList: Booking.list(params), bookingInstanceTotal: Booking.count()]
    }

    def revisions = {
        def bookingInstanceList = Booking.findAllRevisions()
        render(view: 'revisions', model: [bookingInstanceList: bookingInstanceList, bookingInstanceTotal: bookingInstanceList.size()])
    }

    @Secured(['ROLE_USER'])
    def create() {
        [bookingInstance: new Booking(params)]
    }

    @Secured(['ROLE_USER'])
    def save() {
        def bookingInstance = new Booking(params)
        if (!bookingInstance.save(flush: true)) {
            render(view: "create", model: [bookingInstance: bookingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'booking.label', default: 'Booking'), bookingInstance.id])
        redirect(action: "show", id: bookingInstance.id)
    }

    def show(Long id) {
        def bookingInstance = Booking.get(id)
        if (!bookingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "list")
            return
        }

        [bookingInstance: bookingInstance]
    }

    @Secured(['ROLE_USER'])
    def edit(Long id) {
        def bookingInstance = Booking.get(id)
        if (!bookingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "list")
            return
        }

        [bookingInstance: bookingInstance]
    }

    @Secured(['ROLE_USER'])
    def update(Long id, Long version) {
        def bookingInstance = Booking.get(id)
        if (!bookingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bookingInstance.version > version) {
                bookingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'booking.label', default: 'Booking')] as Object[],
                          "Another user has updated this Booking while you were editing")
                render(view: "edit", model: [bookingInstance: bookingInstance])
                return
            }
        }

        bookingInstance.properties = params

        if (!bookingInstance.save(flush: true)) {
            render(view: "edit", model: [bookingInstance: bookingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'booking.label', default: 'Booking'), bookingInstance.id])
        redirect(action: "show", id: bookingInstance.id)
    }

    @Secured(['ROLE_USER'])
    def delete(Long id) {
        def bookingInstance = Booking.get(id)
        if (!bookingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "list")
            return
        }

        try {
            bookingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'booking.label', default: 'Booking'), id])
            redirect(action: "show", id: id)
        }
    }
}
