<%@ page import="pl.refaktor.enversdemo.Booking" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'booking.label', default: 'Booking')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-booking" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="revisions" action="revisions"><g:message code="default.revisions.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-booking" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="daysCount"
                              title="${message(code: 'booking.daysCount.label', default: 'Days Count')}"/>

            <th><g:message code="booking.hotel.label" default="Hotel"/></th>

            <g:sortableColumn property="startDate"
                              title="${message(code: 'booking.startDate.label', default: 'Start Date')}"/>

            <g:sortableColumn property="surname" title="${message(code: 'booking.surname.label', default: 'Surname')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${bookingInstanceList}" status="i" var="bookingInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${bookingInstance.id}">${fieldValue(bean: bookingInstance, field: "daysCount")}</g:link></td>

                <td>${fieldValue(bean: bookingInstance, field: "hotel")}</td>

                <td><g:formatDate date="${bookingInstance.startDate}"/></td>

                <td>${fieldValue(bean: bookingInstance, field: "surname")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${bookingInstanceTotal}"/>
    </div>
</div>
</body>
</html>
