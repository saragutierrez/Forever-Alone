<%-- 
    Document   : clientesListar
    Created on : 29/03/2018, 20:05:12
    Author     : luck
    View não atualizada para o projeto, apenas um exemplo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="com.sun.faces.application.WebPrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@page import="com.mysql.cj.util.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="erro.jsp" %>
<%-- Procura se existe um usuário instanciado --%>
<c:choose>
<c:when test="${empty sessionScope.user}">
<jsp:forward page="index.jsp">
    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
</jsp:forward>
</c:when>
    <c:otherwise>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <script type="text/javascript" src="materialize/js/web2.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" href="java.ico">
        <title>Portal</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col m12">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, ${user.nome}</h2>
                    <div class="divider"></div>
                    <div class="card white">
                        <div class="card-content">
                            <span class="card-title center-align"><h3>Cadastro de Clientes</h3></span>
                        </div>
                    </div>
                    <table class="responsive-table highlight centered">
                        <thead>
                            <tr class="card blue-grey lighten-5">
                                <th>CPF</th>
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Visualizar / Alterar / Remover</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${lista}" var="aux">
                                <tr class='card'>
                                <td>${aux.cpfCliente}</td>
                                <td>${aux.nomeCliente}</td>
                                <td>${aux.emailCliente}</td>
                                <td class="center-align" style="width: 220px;">
                                <a href="ClientesServlet?action=show&id=${aux.idCliente}" class="btn-floating pulse green opt"><i class="small material-icons white-text">visibility</i></a>
                                <a href="ClientesServlet?action=formUpdate&id=${aux.idCliente}" class="btn-floating pulse yellow opt"><i class="small material-icons white-text">edit</i></a>
                                <a href="" onclick="confirmar(this,${aux.idCliente});" class="btn-floating pulse red opt"><i class="small material-icons white-text">delete</i></a>
                                </td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="height: 10px;"></div>
                    <div class="container">
                        <div class="row">
                            <div class="col left-align">
                                <a href="ClientesServlet?action=formNew" class="waves-effect waves-teal btn">Novo<i class="material-icons right">add</i></a>                                
                            </div>
                            <div class="col offset-s6">
                                <a href="portal.jsp" class="waves-effect waves-teal btn">Voltar<i class="material-icons right">arrow_back</i></a>                                
                            </div>
                        </div>
                    </div>
                    <div style="height: 100px;"></div>
                </div>
            </div>
        </div>
                    <%@include file="footer.jsp" %>
    </body>
</html>
    </c:otherwise>    
</c:choose>