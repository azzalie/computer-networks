<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.List" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>MavenServlet</title>
</head>
<body>
    <h1>MavenServlet</h1>

    <form action="SimpleServlet" method="post">
        Start: <input type="text" name="a"><br>
        End: <input type="text" name="b"><br>
        <input type="submit" value="Submit">
    </form>

    <% if (request.getAttribute("result") != null) { %>
        <h3>Found Numbers:</h3>
        <ul>
            <% for (Integer number : (List<Integer>) request.getAttribute("result")) { %>
                <li><%= number %></li>
            <% } %>
        </ul>
    <% } %>
</body>
</html>

