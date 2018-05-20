<%-- 
    Document   : erro
    Created on : 24/03/2018, 01:29:10
    Author     : luck
--%>

<%@page import="com.mysql.cj.util.StringUtils"%>
<%@page import="javax.servlet.jsp.PageContext" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <title>Error</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row bordered">
                <h1 class="red-text">
                    <c:out value="${exception.message}" /></h1>
                <h2 class="red-text">
                    <c:out value="${pageContext.out()}" /><br/>
                    <c:out value="${exception.printStackTrace(pageContext.response.writer)}" /><br/>
                </h2>
                <h3 class="white-text">Para voltar à Home <a href="index.jsp" class="black-text pulse"><strong>Clique aqui</strong></a>.</h3>
            </div>
        </div>
<%@include file="footer.jsp" %>
    </body>
</html>