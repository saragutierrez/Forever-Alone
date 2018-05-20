<%-- 
    Document   : index
    Created on : 29/03/2018, 15:08:52
    Author     : luck
--%>
<%@ page import="com.mysql.cj.util.StringUtils"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="erro.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <title>Home</title>
    </head>
    <body class="bgimg">
        <div style="height: 50px;"></div>
        <div class="container">
            <div class="row">
                <div class="col offset-m3 m6">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title center-align"><h3>Formulário de Login</h3></div>
                            <form action="LoginServlet" method="post">
                                Login: <input type="text" name="login" value=""/><br/>
                                Senha: <input type="password" name="senha" value=""/><br/>
                                <input type="submit" value="entrar" class="btn blue-grey">
                            </form>
                            <h5></br>
                                <div class="red-text">
                                    <c:out value="${ (empty requestScope.msg) ? ((empty param.msg) ? '' : param.msg): requestScope.msg }" />
                                </div>
                            </h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
