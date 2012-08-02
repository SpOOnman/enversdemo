package pl.refaktor.enversdemo

import grails.plugin.spock.IntegrationSpec

class BookingControllerIntegrationSpec extends IntegrationSpec {

    static transactional = false

    BookingController controller = null

    def setup() {
        controller = new BookingController()
    }

    def cleanup() {
        Booking.list()*.delete()
        Hotel.list()*.delete()
    }

    def "should save booking with valid parameters along with Envers revision"() {
        given:
            def date = new Date()
            def hotel = new Hotel(name: "Marriott").save(flush: true, failOnError: true)
            controller.params.putAll('hotel.id': hotel.id, surname: 'Smiths', startDate: date, daysCount: 2)
        when:
            controller.save()
        then:
            Hotel.count() == 1
            Booking.count() == 1
            def savedBooking = Booking.list().last()
            savedBooking.surname == "Smiths"
            savedBooking.startDate == date
            savedBooking.daysCount == 2
            Booking.findAllRevisions().size() == 1
    }
}
