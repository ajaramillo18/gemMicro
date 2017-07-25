
<%@ page import="genmicro.Omsuserr" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'omsuserr.label', default: 'Omsuserr')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'omsuserr.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="costCenter" title="${message(code: 'omsuserr.costCenter.label', default: 'Cost Center')}" />
                        
                            <g:sortableColumn property="approveLimit" title="${message(code: 'omsuserr.approveLimit.label', default: 'Approve Limit')}" />
                        
                            <g:sortableColumn property="buyLimit" title="${message(code: 'omsuserr.buyLimit.label', default: 'Buy Limit')}" />
                        
                            <g:sortableColumn property="clientCode" title="${message(code: 'omsuserr.clientCode.label', default: 'Client Code')}" />
                        
                            <g:sortableColumn property="custCode" title="${message(code: 'omsuserr.custCode.label', default: 'Cust Code')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${omsuserrInstanceList}" status="i" var="omsuserrInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${omsuserrInstance.id}">${fieldValue(bean: omsuserrInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: omsuserrInstance, field: "costCenter")}</td>
                        
                            <td>${fieldValue(bean: omsuserrInstance, field: "approveLimit")}</td>
                        
                            <td>${fieldValue(bean: omsuserrInstance, field: "buyLimit")}</td>
                        
                            <td>${fieldValue(bean: omsuserrInstance, field: "clientCode")}</td>
                        
                            <td>${fieldValue(bean: omsuserrInstance, field: "custCode")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${omsuserrInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
