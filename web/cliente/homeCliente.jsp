<%--
    Document   : homeCliente
    Created on : 28/05/2018, 18:08:59
    Author     : Luck
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="erro.jsp"%>
<%-- Procura se existe um usu�rio instanciado --%>
<c:choose>
	<c:when test="${sessionScope.user.tipoUsuario == \"Cliente\"}">
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>
			<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="icon" href="fa.ico">			
			<title>Cliente</title>
			</head>
			<body>
			</body>
		</html>
	</c:when>
	<c:otherwise>
		<jsp:forward page="../login.jsp">
			<jsp:param name="msg"
				value="Usu�rio deve se autenticar para acessar o sistema." />
		</jsp:forward>
	</c:otherwise>
</c:choose>