<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="base/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="base/header.jsp" %>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <style>
        .container {
            padding-top: 12%;
        }

        .form-bg{
            /* 加载背景图 */
            background-image: url(/oauth/resources/images/login_bg.jpg);
            /* 背景图垂直、水平均居中 */
            background-position: center center;
            /* 背景图不平铺 */
            background-repeat: no-repeat;
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            background-attachment: fixed;
            /* 让背景图基于容器大小伸缩 */
            background-size: cover;
            /* 设置背景颜色，背景图加载过程中会显示背景色 */
            /*background-color: #464646;*/
        }
        .form-horizontal{
            background: rgba(255,255,255, 0.2);
            padding-bottom: 10px;
            border-radius: 15px;
            text-align: center;
            box-shadow: #fafafa 0px 0px 1px;
        }
        .form-horizontal .heading{
            display: block;
            font-size: 30px;
            padding: 10px 0;
            border-bottom: 1px solid #f0f0f0;
            margin-bottom: 20px;
            color: #fff;
        }
        .form-horizontal .form-group{
            padding: 0 40px;
            margin: 0 0 25px 0;
            position: relative;
        }
        .form-horizontal .form-control{
            background: #f0f0f0;
            border: none;
            border-radius: 20px;
            box-shadow: none;
            padding: 0 20px 0 45px;
            height: 40px;
            transition: all 0.3s ease 0s;
        }
        .form-horizontal .form-control:focus{
            background: #e0e0e0;
            box-shadow: none;
            outline: 0 none;
        }
        .form-horizontal .form-group i{
            position: absolute;
            top: 12px;
            left: 60px;
            font-size: 17px;
            color: #c8c8c8;
            transition : all 0.5s ease 0s;
        }
        .form-horizontal .form-control:focus + i{
            color: #00b4ef;
        }
        .form-horizontal .fa-question-circle{
            display: inline-block;
            position: absolute;
            top: 12px;
            right: 60px;
            font-size: 20px;
            color: #808080;
            transition: all 0.5s ease 0s;
        }
        .form-horizontal .fa-question-circle:hover{
            color: #000;
        }
        .form-horizontal .main-checkbox{
            float: left;
            width: 20px;
            height: 20px;
            background: #11a3fc;
            border-radius: 50%;
            position: relative;
            margin: 5px 0 0 5px;
            border: 1px solid #11a3fc;
        }
        .form-horizontal .main-checkbox label{
            width: 20px;
            height: 20px;
            position: absolute;
            top: 0;
            left: 0;
            cursor: pointer;
        }
        .form-horizontal .main-checkbox label:after{
            content: "";
            width: 10px;
            height: 5px;
            position: absolute;
            top: 5px;
            left: 4px;
            border: 3px solid #fff;
            border-top: none;
            border-right: none;
            background: transparent;
            opacity: 0;
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
        }
        .form-horizontal .main-checkbox input[type=checkbox]{
            visibility: hidden;
        }
        .form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
            opacity: 1;
        }
        .form-horizontal .text{
            float: left;
            margin-left: 7px;
            line-height: 20px;
            padding-top: 5px;
            text-transform: capitalize;
            color: #fff;
        }
        .form-horizontal .btn{
            float: right;
            font-size: 14px;
            color: #fff;
            background: #00b4ef;
            border-radius: 25px;
            padding: 5px 20px;
            border: none;
            text-transform: capitalize;
            transition: all 0.5s ease 0s;
        }
        @media only screen and (max-width: 479px){
            .form-horizontal .form-group{
                padding: 0 25px;
            }
            .form-horizontal .form-group i{
                left: 45px;
            }
            .form-horizontal .btn{
                padding: 5px 20px;
            }
        }
    </style>
</head>
<body class="form-bg">
<div class="container">
    <div class="row">
        <div class="col-md-offset-4 col-md-4">
            <form class="form-horizontal">
                <span class="heading">imptools登录</span>
                <div class="form-group">
                    <input autocomplete="on" type="text" class="form-control" id="inputUsername" name="username" placeholder="用户名" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input autocomplete="on" type="password" class="form-control" id="inputPassword" name="password" placeholder="密　码" required>
                    <i class="fa fa-lock"></i>
                    <%--<a href="#" class="fa fa-question-circle"></a>--%>
                </div>
                <div class="form-group">
                    <div class="main-checkbox">
                        <input type="checkbox" value="None" id="checkbox1" name="check"/>
                        <label for="checkbox1"></label>
                    </div>
                    <span class="text">Remember me</span>
                    <button type="button" class="btn btn-default" id="loginBtn">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<%@include file="base/script.jsp" %>
<script type="text/javascript">

    function successCallback(data) {
        console.log('cookie设置成功：'+ JSON.stringify(data));
        window.location.href = window.path + '/index';
    }

    $(document).ready(function () {
        $("#loginBtn").click(function () {
            var username = $("#inputUsername").val();
            var password = $("#inputPassword").val();
            if(username != '' && password != '') {
                $.ajax({
                    url: window.path + '/login/submit',
                    method: "post",
                    dataType: "json",
                    data: {
                        username: username,
                        password: password
                    },
                    success: function (response) {
                        var code = response.code;
                        if(code == 200) {
                            var token = response.data;
                            $.cookie('token', token, { expires: 7, path: '/imptools' });
                            $.ajax({
                                url: '${setIDPCookieUrl}',
                                method: 'get',
                                data: {
                                    appId:'${appId}',
                                    remoteIp:'${remoteIp}',
                                    tokenId: token
                                },
                                dataType: "jsonp",
                                jsonp: 'jsonpCallback',
                                jsonpCallback: 'successCallback'
                            });
                        } else {
                            $.cookie('token', null, { expires: 7, path: '/imptools' });
                            toastr.error(response.message);
                        }
                    }
                })
            } else {
                toastr.warning("用户名或密码不能为空！");
            }
        });
    });
</script>
</html>
