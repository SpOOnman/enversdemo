package pl.refaktor.enversdemo

class Booking {

    String surname
    Date   startDate
    Integer daysCount

    static constraints = {
    }

    static belongsTo = [hotel: Hotel]
}
