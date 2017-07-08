<%--
  Created by IntelliJ IDEA.
  User: 黄孝君
  Date: 2017/7/8
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>天网登录系统</title>
</head>
<body>
    <form action="/login/tohome" method="post">
        <label for="userName">用户名:</label>
        <input name="userName" id="userName" type="text"/><br/>
        <label>密码:</label>
        <input name="password" type="password" id="password"/><br/>
        <input type="submit" name="btnSub" value="登录"/>
    </form>
</body>
</html>
