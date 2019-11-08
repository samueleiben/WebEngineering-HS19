package webec

import grails.converters.JSON

class BookingController {

    def probiere() {
        // find the last names of all people that booked
        // room named "1.313"
        def booker = Person.findByLastName('Holz')
        def result = Booking.findAllByBooker(booker)*.room
        render text: result.toString()
    }
    static scaffold = Booking

    def probierejson() {
        def room = Room.findByName("1.313")
        def lastNames = Booking.findAllByRoom(room).booker.lastName.unique()

        def booker = Person.findByLastName("Holz")
        def bookings = Booking.findAllByBooker(booker)*.room
        render contentType: "text/json" , text: lastNames as JSON
    }
}
