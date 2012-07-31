package pl.refaktor.enversdemo

import org.hibernate.envers.Audited

@Audited
class Booking {

    String surname
    Date   startDate
    Integer daysCount

    static constraints = {
    }

    static belongsTo = [hotel: Hotel]
}
