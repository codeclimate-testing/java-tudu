<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div align="center">
 <h3><fmt:message key="administration.title"/></h3>
 <html:form action="/secure/admin/administration" focus="smtpHost">
 <c:if test="${success eq 'true'}">
  <span class="success"><fmt:message key="form.success"/></span>
 </c:if>
 <html:errors/>
 <html:hidden property="method" value="cancel"/>
 
 <table class="list" style="width:450px">
  <tr>
   <th colspan="2">
    <fmt:message key="administration.email"/>
   </th>
  </tr>
  <tbody>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.host"/>
    </td>
    <td>
     <html:text property="smtpHost" size="30" maxlength="200"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="administration.email.port"/>
    </td>
    <td>
	 <html:text property="smtpPort" size="30" maxlength="200"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.user"/>
    </td>
    <td>
     <html:text property="smtpUser" size="30" maxlength="200"/>
    </td>
   </tr>
   <tr class="even">
    <td>
     <fmt:message key="administration.email.password"/>
    </td>
    <td>
     <html:password property="smtpPassword" size="30" maxlength="200"/>
    </td>
   </tr>
   <tr class="odd">
    <td>
     <fmt:message key="administration.email.from"/>
    </td>
    <td>
     <html:text property="smtpFrom" size="30" maxlength="200"/>
    </td>
   </tr>
  </tbody>
 </table>
  <br/>
  <html:submit onclick="document.forms[0].elements['method'].value='update';">
   <fmt:message key="form.submit"/>
  </html:submit>
  <html:submit><fmt:message key="form.cancel"/></html:submit>
 </html:form>
 
 <h3><fmt:message key="administration.database.dump"/></h3>
 <html:form action="/secure/admin/administration">
  <html:hidden property="method" value="dumpDatabase"/>
  <html:submit>
   <fmt:message key="form.submit"/>
  </html:submit>
 </html:form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
