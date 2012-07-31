import pl.refaktor.enversdemo.Hotel
import pl.refaktor.enversdemo.Booking

class BootStrap {

    def init = { servletContext ->
        println("Bootstrap")
        if (Hotel.count() == 0) {
            createAstoria()
            createHolidayInn()
        }
    }
    def destroy = {
    }

    void createAstoria() {
        Hotel.withTransaction {
            def hotel = new Hotel(name: 'Astoria')
            def weekBooking = new Booking(surname: 'Doe', startDate: new Date(), daysCount: 7)
            def weekendBooking = new Booking(surname: 'Marcy', startDate: new Date(), daysCount: 2)
            hotel.addToBookings(weekBooking)
            hotel.addToBookings(weekendBooking)
            hotel.save(failOnError: true, flush: true)
        }
    }

    void createHolidayInn() {
        Hotel.withTransaction {
            def hotel = new Hotel(name: 'Holiday Inn')
            def honeymoonBooking = new Booking(surname: 'Smiths', startDate: new Date(), daysCount: 10)
            hotel.addToBookings(honeymoonBooking)
            hotel.save(failOnError: true, flush: true)
        }
    }
}
