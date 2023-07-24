<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo"><img src="/oauthSSODemo/resources/images/logo.png"></div>
    <div class="l-topmenu-welcome" id="topWelcome" style="">
        当前用户: ${displayName}[${loginName}]
        <span class="space">|</span>
        <a href="${globalLogoutUrl}?redirctToUrl=http://localhost:8080/oauthSSODemo/logout&redirectToLogin=true&entityId=${entityId}" class="l-link2">退出</a>
    </div>
</div>

