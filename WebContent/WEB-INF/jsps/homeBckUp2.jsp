<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Hi there!<p/>

<!--  Session: <% //out.println(session.getAttribute("name")); %><p/> -->
<!--  Request: <% //out.println(request.getAttribute("name")); %><p/> -->

<c:out value="${name}"></c:out><p/>

Request (using EL): ${name}<p/>

<sql:query var="rs" dataSource="jdbc/springtutorial">
select *  from offers
</sql:query>

<c:forEach var="row" items="${rs.rows}">
    id ${row.id}<br/>
    name ${row.name}<br/>
    email ${row.email}<br/>
    text ${row.text}<br/>
</c:forEach>

</body>
</html>