package webec

//import grails.plugin.springsecurity.annotation.Secured

import grails.converters.JSON

//@Secured('ROLE_USER') // when using annotations
class MyBookingController {

    def probiere() {
        // find the last names of all people that booked
        // room named "1.313"
        def booker = Person.findByLastName('Holz')
        def result = Booking.findAllByBooker(booker)*.room
        respond result
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
