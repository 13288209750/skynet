<%--
  Created by IntelliJ IDEA.
  User: 黄孝君
  Date: 2017/7/8
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>天网登录系统</title>
    <link rel="stylesheet" href="/statics/bootstrap/css/bootstrap.min.css" type="text/css"/>
    <script src="/statics/js/jquery-3.1.1.min.js"></script>
    <script src="/statics/bootstrap/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>

        .container{
            display:table;
            height:100%;
        }

        .row{
            display: table-cell;
            vertical-align: middle;
        }

        .row-centered {
            text-align:center;
        }
        .col-centered {
            display:inline-block;
            float:none;
            text-align:center;
            margin-right:-4px;
        }

        .col-centered-left{
            display:inline-block;
            float:none;
            text-align:left;
            margin-right:-4px;
        }
        .bg{
            background:url("/statics/img/8.jpg") ;
            no-repeat: center fixed;
            background-size: 100% auto;
        }
    </style>
</head>
<body class="bg">
        <br>
        <br>
        <br>
        <br>
        <div class="container">
                <div class="row  row-centered">
                    <div class="col-md-8 col-centered-left">
                        <div class="jumbotron">
                            <h1>Hello, SkyNet!</h1>
                            <p>
                                SKYNET,创建于2017/7/10日...
                            </p>
                            <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
                        </div>
                    </div>
                    <div class="col-md-4 col-centered text-center">
                        <form method="post" action="/login/tohome">
                            <div class="input-group has-success has-feedback">
                                <span class="input-group-addon" id="basic-addon1">U</span>
                                <input id="userName" type="text" name="userName" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
                            </div>
                            <br>
                            <!--下面是密码输入框-->
                            <div class="input-group has-success has-feedback">
                                <span class="input-group-addon" id="basic-addon2">K</span>
                                <input id="password" type="password" class="form-control" name="password" placeholder="密码" aria-describedby="basic-addon1">
                            </div>
                            <br>
                            <!--下面是登陆按钮,包括颜色控制-->
                            <button type="submit" style="width:280px;" class="btn btn-primary">登 录</button>
                        </form>
                        <h5>没有账号？<a href="/index/toregister">立即注册</a></h5>
                    </div>
                </div>
        </div>
</body>
</html>
