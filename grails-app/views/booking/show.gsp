<%@ page import="pl.refaktor.enversdemo.Booking" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'booking.label', default: 'Booking')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-booking" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="revisions" action="revisions"><g:message code="default.revisions.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-booking" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list booking">

        <g:if test="${bookingInstance?.daysCount}">
            <li class="fieldcontain">
                <span id="daysCount-label" class="property-label"><g:message code="booking.daysCount.label"
                                                                             default="Days Count"/></span>

                <span class="property-value" aria-labelledby="daysCount-label"><g:fieldValue bean="${bookingInstance}"
                                                                                             field="daysCount"/></span>

            </li>
        </g:if>

        <g:if test="${bookingInstance?.hotel}">
            <li class="fieldcontain">
                <span id="hotel-label" class="property-label"><g:message code="booking.hotel.label"
                                                                         default="Hotel"/></span>

                <span class="property-value" aria-labelledby="hotel-label"><g:link controller="hotel" action="show"
                                                                                   id="${bookingInstance?.hotel?.id}">${bookingInstance?.hotel?.encodeAsHTML()}</g:link></span>

            </li>
        </g:if>

        <g:if test="${bookingInstance?.startDate}">
            <li class="fieldcontain">
                <span id="startDate-label" class="property-label"><g:message code="booking.startDate.label"
                                                                             default="Start Date"/></span>

                <span class="property-value" aria-labelledby="startDate-label"><g:formatDate
                        date="${bookingInstance?.startDate}"/></span>

            </li>
        </g:if>

        <g:if test="${bookingInstance?.surname}">
            <li class="fieldcontain">
                <span id="surname-label" class="property-label"><g:message code="booking.surname.label"
                                                                           default="Surname"/></span>

                <span class="property-value" aria-labelledby="surname-label"><g:fieldValue bean="${bookingInstance}"
                                                                                           field="surname"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${bookingInstance?.id}"/>
            <g:link class="edit" action="edit" id="${bookingInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
