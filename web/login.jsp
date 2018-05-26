<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
                            <div class="card-title center-align"><h3>Formulário de Login</h3></div>
                            <form action="LoginServlet" method="post">
                                E-mail: <input type="email" name="email" value=""/><br/>
                                Senha: <input type="password" name="senha" value=""/><br/>
                                <input type="submit" value="entrar" class="btn blue-grey">
                            </form>
                            <h5></br>
                                <div class="red-text">
                                    <c:out value="${ (empty requestScope.msg) ? ((empty param.msg) ? '' : param.msg): requestScope.msg }" />
                                </div>
                            </h5>
</body>
</html>