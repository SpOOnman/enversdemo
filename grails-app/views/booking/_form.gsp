<%@ page import="pl.refaktor.enversdemo.Booking" %>



<div class="fieldcontain ${hasErrors(bean: bookingInstance, field: 'daysCount', 'error')} required">
    <label for="daysCount">
        <g:message code="booking.daysCount.label" default="Days Count"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="daysCount" type="number" value="${bookingInstance.daysCount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookingInstance, field: 'hotel', 'error')} required">
    <label for="hotel">
        <g:message code="booking.hotel.label" default="Hotel"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="hotel" name="hotel.id" from="${pl.refaktor.enversdemo.Hotel.list()}" optionKey="id" required=""
              value="${bookingInstance?.hotel?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookingInstance, field: 'startDate', 'error')} required">
    <label for="startDate">
        <g:message code="booking.startDate.label" default="Start Date"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="startDate" precision="day" value="${bookingInstance?.startDate}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bookingInstance, field: 'surname', 'error')} ">
    <label for="surname">
        <g:message code="booking.surname.label" default="Surname"/>

    </label>
    <g:textField name="surname" value="${bookingInstance?.surname}"/>
</div>

