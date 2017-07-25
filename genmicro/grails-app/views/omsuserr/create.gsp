

<%@ page import="genmicro.Omsuserr" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'omsuserr.label', default: 'Omsuserr')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${omsuserrInstance}">
            <div class="errors">
                <g:renderErrors bean="${omsuserrInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="costCenter"><g:message code="omsuserr.costCenter.label" default="Cost Center" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'costCenter', 'errors')}">
                                    <g:textField name="costCenter" value="${omsuserrInstance?.costCenter}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="approveLimit"><g:message code="omsuserr.approveLimit.label" default="Approve Limit" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'approveLimit', 'errors')}">
                                    <g:textField name="approveLimit" value="${fieldValue(bean: omsuserrInstance, field: 'approveLimit')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="buyLimit"><g:message code="omsuserr.buyLimit.label" default="Buy Limit" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'buyLimit', 'errors')}">
                                    <g:textField name="buyLimit" value="${fieldValue(bean: omsuserrInstance, field: 'buyLimit')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="clientCode"><g:message code="omsuserr.clientCode.label" default="Client Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'clientCode', 'errors')}">
                                    <g:textField name="clientCode" value="${omsuserrInstance?.clientCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="custCode"><g:message code="omsuserr.custCode.label" default="Cust Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'custCode', 'errors')}">
                                    <g:textField name="custCode" value="${omsuserrInstance?.custCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="defaultApprover"><g:message code="omsuserr.defaultApprover.label" default="Default Approver" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'defaultApprover', 'errors')}">
                                    <g:textField name="defaultApprover" value="${omsuserrInstance?.defaultApprover}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="omsuserr.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${omsuserrInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifyDate"><g:message code="omsuserr.modifyDate.label" default="Modify Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'modifyDate', 'errors')}">
                                    <g:datePicker name="modifyDate" precision="day" value="${omsuserrInstance?.modifyDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="modifyId"><g:message code="omsuserr.modifyId.label" default="Modify Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'modifyId', 'errors')}">
                                    <g:textField name="modifyId" value="${omsuserrInstance?.modifyId}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="overrideApprover"><g:message code="omsuserr.overrideApprover.label" default="Override Approver" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'overrideApprover', 'errors')}">
                                    <g:textField name="overrideApprover" value="${omsuserrInstance?.overrideApprover}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="overrideCostCenter"><g:message code="omsuserr.overrideCostCenter.label" default="Override Cost Center" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'overrideCostCenter', 'errors')}">
                                    <g:textField name="overrideCostCenter" value="${omsuserrInstance?.overrideCostCenter}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="overrideCustCode"><g:message code="omsuserr.overrideCustCode.label" default="Override Cust Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'overrideCustCode', 'errors')}">
                                    <g:textField name="overrideCustCode" value="${omsuserrInstance?.overrideCustCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="overrideShipToCode"><g:message code="omsuserr.overrideShipToCode.label" default="Override Ship To Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'overrideShipToCode', 'errors')}">
                                    <g:textField name="overrideShipToCode" value="${omsuserrInstance?.overrideShipToCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password"><g:message code="omsuserr.password.label" default="Password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'password', 'errors')}">
                                    <g:textField name="password" value="${omsuserrInstance?.password}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="shipToCode"><g:message code="omsuserr.shipToCode.label" default="Ship To Code" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'shipToCode', 'errors')}">
                                    <g:textField name="shipToCode" value="${omsuserrInstance?.shipToCode}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="status"><g:message code="omsuserr.status.label" default="Status" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'status', 'errors')}">
                                    <g:textField name="status" value="${omsuserrInstance?.status}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="uid"><g:message code="omsuserr.uid.label" default="Uid" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'uid', 'errors')}">
                                    <g:textField name="uid" value="${fieldValue(bean: omsuserrInstance, field: 'uid')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userId"><g:message code="omsuserr.userId.label" default="User Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'userId', 'errors')}">
                                    <g:textField name="userId" value="${omsuserrInstance?.userId}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userName"><g:message code="omsuserr.userName.label" default="User Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'userName', 'errors')}">
                                    <g:textField name="userName" value="${omsuserrInstance?.userName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="userType"><g:message code="omsuserr.userType.label" default="User Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: omsuserrInstance, field: 'userType', 'errors')}">
                                    <g:textField name="userType" value="${omsuserrInstance?.userType}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
