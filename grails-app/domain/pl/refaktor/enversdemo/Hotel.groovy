package pl.refaktor.enversdemo

import org.hibernate.envers.Audited

@Audited
class Hotel {

    String name

    static constraints = {
    }

    static hasMany = [bookings: Booking]
}
