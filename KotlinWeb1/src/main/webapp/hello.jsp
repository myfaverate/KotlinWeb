<%--
  Created by IntelliJ IDEA.
  User: 29051
  Date: 2024/11/24
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello 你好</h1>
    <%
        for (int i = 0; i < 10; i ++ ) {
            response.getWriter().println("<h1>你好啊: " + i + "</h1>");
        }
    %>
</body>
</html>
