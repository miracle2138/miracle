<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Oauth2接口集成Demo</title>

    <!-- Bootstrap -->
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
    <script src="resources/bootstrap/js/html5shiv.min.js"></script>
    <script src="resources/bootstrap/js/respond.min.js"></script>
    <![endif]-->



    <%--toastr提示条--%>
    <link href="resources/toastr/css/toastr.min.css" rel="stylesheet">

    <link href="resources/css/normalize.css" rel="stylesheet">
    <link href="resources/css/common.css" rel="stylesheet">
    <script type="text/javascript">
        // 暂时写固定
        var path = '<%=request.getContextPath() %>';
        window.path = path;
    </script>
</head>
