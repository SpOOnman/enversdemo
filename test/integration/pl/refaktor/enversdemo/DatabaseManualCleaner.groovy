package pl.refaktor.enversdemo

import org.hibernate.SessionFactory

class DatabaseManualCleaner {

    static void cleanupDatabase(SessionFactory sessionFactory) {
        ['booking', 'booking_aud', 'hotel', 'hotel_aud', 'user_revision_entity'].each {
            sessionFactory.currentSession.createSQLQuery("delete from $it").executeUpdate()
        }
    }
}
