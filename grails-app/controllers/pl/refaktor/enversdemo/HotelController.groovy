package pl.refaktor.enversdemo

import org.springframework.transaction.annotation.Transactional

@Transactional
class HotelController {
    static scaffold = true

    def revisions = {
        def hotelInstanceList = Hotel.findAllRevisions()
        render(view: 'revisions', model: [hotelInstanceList: hotelInstanceList, hotelInstanceTotal: hotelInstanceList.size()])
    }
}
