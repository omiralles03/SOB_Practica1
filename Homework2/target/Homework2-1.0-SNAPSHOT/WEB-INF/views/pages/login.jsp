<%-- 
    Document   : login
    Created on : Dec 28, 2024, 8:33:39 PM
    Author     : oupma
--%>

<%@ include file="/WEB-INF/layout/baseLayout.jsp" %>
<div class="login-form">
    <form action="${pageContext.request.contextPath}/auth/login" method="POST" class="max-w-md mx-auto">
        <label for="username" class="block text-sm">Username</label>
        <input type="text" name="username" id="username" class="block w-full border mt-1">
        <label for="password" class="block text-sm mt-4">Password</label>
        <input type="password" name="password" id="password" class="block w-full border mt-1">
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 mt-4">Login</button>
    </form>
</div>

