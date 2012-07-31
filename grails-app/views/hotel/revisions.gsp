<%@ page import="pl.refaktor.enversdemo.Hotel" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'hotel.label', default: 'Hotel')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-hotel" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-hotel" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="revisionEntity.id" title="${message(code: 'envers.id.label', default: 'Revision id')}"/>

            <g:sortableColumn property="name" title="${message(code: 'hotel.name.label', default: 'Name')}"/>

            <g:sortableColumn property="revisionType" title="${message(code: 'envers.revisionType.label', default: 'Revision type')}"/>

            <g:sortableColumn property="revisionEntity.revisionDate" title="${message(code: 'envers.revisionDate.label', default: 'Revision date')}"/>

            <g:sortableColumn property="revisionEntity.currentUser" title="${message(code: 'envers.currentUser.label', default: 'Revision author')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${hotelInstanceList}" status="i" var="hotelRevision">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:fieldValue field="revisionEntity.id" bean="${hotelRevision}" /></td>

                <td><g:link action="show" id="${hotelRevision.id}"><g:fieldValue field="name" bean="${hotelRevision}" /></g:link></td>

                <td><g:fieldValue field="revisionType" bean="${hotelRevision}" /></td>

                <td><g:fieldValue field="revisionEntity.revisionDate" bean="${hotelRevision}" /></td>

                <td><g:fieldValue field="revisionEntity.currentUser" bean="${hotelRevision}" /></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${hotelInstanceTotal}"/>
    </div>
</div>
</body>
</html>
