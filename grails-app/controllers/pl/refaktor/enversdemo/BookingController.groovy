package pl.refaktor.enversdemo

import org.springframework.transaction.annotation.Transactional

@Transactional
class BookingController {
    static scaffold = true

    def revisions = {
        def bookingInstanceList = Booking.findAllRevisions()
        render(view: 'revisions', model: [bookingInstanceList: bookingInstanceList, bookingInstanceTotal: bookingInstanceList.size()])
    }
}
