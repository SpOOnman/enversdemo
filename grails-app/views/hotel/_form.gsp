<%@ page import="pl.refaktor.enversdemo.Hotel" %>



<div class="fieldcontain ${hasErrors(bean: hotelInstance, field: 'bookings', 'error')} ">
    <label for="bookings">
        <g:message code="hotel.bookings.label" default="Bookings"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${hotelInstance?.bookings ?}" var="b">
            <li><g:link controller="booking" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="booking" action="create"
                    params="['hotel.id': hotelInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'booking.label', default: 'Booking')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: hotelInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="hotel.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${hotelInstance?.name}"/>
</div>

