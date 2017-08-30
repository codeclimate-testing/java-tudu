<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://struts-menu.sf.net/tag" prefix="menu" %>

 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        
<%
 request.setAttribute("ctx", request.getContextPath()); 
 %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Tudu Lists</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link href="${ctx}/css/global.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" media="screen"
        href="${ctx}/css/tabs.css" />
    <script type="text/javascript" src="${ctx}/scripts/tabs.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/scriptaculous/prototype.js"></script>
    <script type="text/javascript" src="${ctx}/scripts/scriptaculous/scriptaculous.js"></script>
<%
 if (request.isUserInRole("ROLE_USER")) {
%>
    <script type="text/javascript" src="${ctx}/secure/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/secure/dwr/util.js"></script>
</head>
<body marginwidth="0" marginheight="0" onload="initMenu();DWRUtil.useLoadingMessage();">
<menu:useMenuDisplayer 	name="TabbedMenu"
  						bundle="org.apache.struts.action.MESSAGE">
  						
  <menu:displayMenu name="Info"/>
  <menu:displayMenu name="TodoLists"/>
  <menu:displayMenu name="Todos"/>
  <%
   if (request.isUserInRole("ROLE_ADMIN")) {
  %>
   <menu:displayMenu name="Administration"/>
   <menu:displayMenu name="Monitoring"/>
  <%
   }
  %>
  <menu:displayMenu name="Logout"/>
</menu:useMenuDisplayer>
<%
 } else {
%>
</head>
<body onload="initMenu();">
<menu:useMenuDisplayer 	name="TabbedMenu"
  						bundle="org.apache.struts.action.MESSAGE">
  						
  <menu:displayMenu name="Welcome"/>
  <menu:displayMenu name="Register"/>
</menu:useMenuDisplayer>
<%
 }
%>
 <table align="center" id="page">
  <tr>
   <td>
    <div id="title">Tudu Lists</div>
   </td>
   <td>
    <% if (request.getRemoteUser() != null) { %>
     <div id="username"><fmt:message key="header.user"/> <%=request.getRemoteUser()%></div>
    <% } %>
   </td>
  </tr>
  <tr>
   <td align="center" colspan="2">
    <div id="content">
    