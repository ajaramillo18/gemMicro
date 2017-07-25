
<%@ page import="genmicro.Omsuserr" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'omsuserr.label', default: 'Omsuserr')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.costCenter.label" default="Cost Center" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "costCenter")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.approveLimit.label" default="Approve Limit" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "approveLimit")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.buyLimit.label" default="Buy Limit" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "buyLimit")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.clientCode.label" default="Client Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "clientCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.custCode.label" default="Cust Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "custCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.defaultApprover.label" default="Default Approver" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "defaultApprover")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.email.label" default="Email" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "email")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.modifyDate.label" default="Modify Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${omsuserrInstance?.modifyDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.modifyId.label" default="Modify Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "modifyId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.overrideApprover.label" default="Override Approver" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "overrideApprover")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.overrideCostCenter.label" default="Override Cost Center" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "overrideCostCenter")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.overrideCustCode.label" default="Override Cust Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "overrideCustCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.overrideShipToCode.label" default="Override Ship To Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "overrideShipToCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.password.label" default="Password" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "password")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.shipToCode.label" default="Ship To Code" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "shipToCode")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.status.label" default="Status" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "status")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.uid.label" default="Uid" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "uid")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.userId.label" default="User Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "userId")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.userName.label" default="User Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "userName")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="omsuserr.userType.label" default="User Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: omsuserrInstance, field: "userType")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${omsuserrInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
