<%--
  Created by IntelliJ IDEA.
  User: 黄孝君
  Date: 2017/7/13
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>注册页面</title>
    <script src="/statics/js/jquery-3.1.1.min.js"></script>
    <script src="/statics/js/calendar.js"></script>
    <link rel="stylesheet" href="/statics/css/calendar.css">
    <script>
        $(function () {
            //日历
            $('#dd').calendar({
                trigger: '#birthDay',
                zIndex: 999,
                format: 'yyyy-mm-dd',
                onSelected: function (view, date, data) {
                    console.log('event: onSelected')
                },
                onClose: function (view, date, data) {
                    console.log('event: onClose')
                    console.log('view:' + view)
                    console.log('date:' + date)
                    console.log('data:' + (data || 'None'));
                }
            });
        })
    </script>
    <style type="text/css">
        html {
            font: 500 14px 'roboto';
            color: #333;
            background-color: #fafafa;
        }
        a {
            text-decoration: none;
        }
        ul,ol,li {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        p {
            margin: 0;
        }
        #birthDay {
            margin: 30px auto;
            height: 28px;
            width: 200px;
            padding: 0 6px;
            border: 1px solid #ccc;
            outline: none;
        }
    </style>
</head>
<body>
    <form action="register/toregister">
        <label>用户名:</label><input type="text" name="userName"/>
        <label>出生年月:</label><input type="text" id="birthDay" name="birthDay"/>
        <input type="submit" value="立即注册"/>
    </form>
    <div id="dd"></div>
</body>
</html>
