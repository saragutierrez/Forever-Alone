<%--
    Document   : homeAdmin
    Created on : 26/05/2018, 18:28:59
    Author     : Luck
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="erro.jsp"%>
<%-- Procura se existe um usuário instanciado --%>
<c:choose>
	<c:when test="${sessionScope.user.tipoUsuario == \"Admin\"}">
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>
			<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <!-- <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
	        <style type="text/css">@import url("materialize/css/materialize.css");</style>
	        <style type="text/css">@import url("materialize/css/web2.css");</style>
	        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
	        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	        <link rel="stylesheet" href="templates/template.css" type="text/css"/>
			<link rel="icon" href="fa.ico">			
			<title>Admin</title>
			</head>
			<body>
			<!-- INICIO HEADER -->
			<%@include file="../header.jsp" %>
			<!-- FIM HEADER -->
			<!-- INICIO SIDENAV -->
			<div class="sidenavigation">
	            <div class="sidenavigation__div">
	                <img src="https://i.pinimg.com/originals/1b/b8/c2/1bb8c2a02aee9737883fef2040523257.jpg" class="sidenavigation__profile">
	                <h6 class="sidenavigation__title"><c:out value="${user.nome}" /></h6>
	            </div>
	            <a href="AdminServlet?action=list">
	            <div class="sidenavigation__div sidenavigation__links">
	                <i class="fas fa-users sidenavigation__icon"></i>
	                <h6 class="sidenavigation__text">Funcionários</h6>
	            </div>
	            </a>
	            <a href="AdminServlet?action=formNew">
	            <div class="sidenavigation__div sidenavigation__links">
	                <i class="fas fa-address-card sidenavigation__icon"></i>
	                <h6 class="sidenavigation__text">Cadastro</h6>
	            </div>
	            </a>
	            <a href="AdminServlet?action=reports">
	            <div class="sidenavigation__div sidenavigation__links">
	                <i class="fas fa-file sidenavigation__icon"></i>
	                <h6 class="sidenavigation__text">Relatórios</h6>
	            </div>
	            </a>
	        </div>
	        <!-- FIM SIDENAV -->
	        
	        <!-- INICIO BODY -->
	        <c:choose>
	        	<c:when test="${menu == \"list\"}">
					<%@include file="funcionariosListar.jsp" %>					
				</c:when>
				<c:when test="${menu == \"form\"}">
					<%@include file="funcionariosForm.jsp" %>					
				</c:when>
				<c:when test="${menu == \"reports\"}">
					<%@include file="relatorios.jsp" %>					
				</c:when>
				<c:otherwise>
					<%@include file="funcionariosListar.jsp" %>
				</c:otherwise>
	        </c:choose>
	        <!-- FIM BODY -->
	        
			<!-- INICIO FOOTER-->			
			<%--@include file="../footer.jsp" --%>
			<!-- FIM FOOTER-->
			</body>
		</html>
	</c:when>
	<c:otherwise>
		<jsp:forward page="../login.jsp">
			<jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
		</jsp:forward>
	</c:otherwise>
</c:choose>