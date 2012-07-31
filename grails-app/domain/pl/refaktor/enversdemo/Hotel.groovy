package pl.refaktor.enversdemo

class Hotel {

    String name

    static constraints = {
    }

    static hasMany = [bookings: Booking]
}
