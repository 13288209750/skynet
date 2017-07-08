<%--
  Created by IntelliJ IDEA.
  User: 黄孝君
  Date: 2017/7/8
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <h1>欢迎登录</h1>
    <h5><c:out value="${requestScope.user}" default="请登录"/> </h5>
</body>
</html>
