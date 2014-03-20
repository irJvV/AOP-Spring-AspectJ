<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/cave-webshop">
select id, email, password from users
</sql:query>

<html>
  <head>
    <title>DB Test cave-webshop</title>
  </head>
  <body>

  <h2>Results</h2>
  
<c:forEach var="row" items="${rs.rows}">
    id ${row.id}<br/>
    email ${row.email}<br/>
    password ${row.password}<br/>
</c:forEach>

  </body>
</html>