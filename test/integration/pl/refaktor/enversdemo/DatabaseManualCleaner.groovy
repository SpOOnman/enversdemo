package pl.refaktor.enversdemo

import org.hibernate.SessionFactory

class DatabaseManualCleaner {

    static void cleanupDatabase(SessionFactory sessionFactory) {
        ['booking', 'aud_booking', 'hotel', 'aud_hotel', 'user_revision_entity'].each {
            sessionFactory.currentSession.createSQLQuery("delete from $it").executeUpdate()
        }
    }
}
