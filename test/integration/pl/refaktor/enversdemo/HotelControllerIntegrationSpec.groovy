package pl.refaktor.enversdemo

import grails.plugin.spock.IntegrationSpec
import org.hibernate.SessionFactory

class HotelControllerIntegrationSpec extends IntegrationSpec {

    static transactional = false

    HotelController controller = null
    SessionFactory sessionFactory

    def setup() {
        controller = new HotelController()
    }

    def cleanup() {
        DatabaseManualCleaner.cleanupDatabase(sessionFactory)
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
