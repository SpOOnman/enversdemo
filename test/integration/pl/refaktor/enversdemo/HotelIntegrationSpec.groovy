package pl.refaktor.enversdemo

import grails.plugin.spock.IntegrationSpec
import org.hibernate.SessionFactory

class HotelIntegrationSpec extends IntegrationSpec {

    // http://grails.org/doc/latest/guide/testing.html#integrationTesting
    // Don't wrap into database transaction that is rolled back at the end.
    // Explicitly persist entites in database, I will cleanup, I promise.
    static transactional = false

    SessionFactory sessionFactory

    def cleanup() {
        DatabaseManualCleaner.cleanupDatabase(sessionFactory)
    }

    def "Hotel should be audited with Envers"() {
        given:
            def hotel = new Hotel(name: "Marriott")
        when:
            Hotel.withTransaction {
                hotel.save(failOnError: true, flush: true)
            }
        then:
            Hotel.count() == 1
            Hotel.findAllRevisionsById(hotel.id).size() == 1
            UserRevisionEntity.count() == 1
    }
}
