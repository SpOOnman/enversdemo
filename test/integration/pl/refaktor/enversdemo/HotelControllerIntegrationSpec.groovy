package pl.refaktor.enversdemo

import grails.plugin.spock.IntegrationSpec

class HotelControllerIntegrationSpec extends IntegrationSpec {

    HotelController controller = null

    static transactional = false

    def setup() {
        controller = new HotelController()
    }

    def cleanup() {
        Hotel.list()*.delete()
    }

    def "should save hotel with valid parameters along with Envers revision"() {
        given:
            controller.params.name = "Marriott"
        when:
            controller.save()
        then:
            Hotel.count() == 1
            def savedHotel = Hotel.list().last()
            savedHotel.name == "Marriott"
            Hotel.findAllRevisions().size() == 1
    }
}
