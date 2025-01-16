<%-- 
    Document   : login
    Created on : Jan 5, 2025, 6:23:24 PM
    Author     : oupma
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tailwindcss.css">
    </head>
    <body class="flex items-center justify-center min-h-screen bg-neutral-900 text-white">
        <div class="bg-neutral-800 p-8 rounded-lg w-[400px]">
            <h1 class="text-2xl font-bold mb-4 text-center">Login</h1>

            <!--Error login-->
            <c:if test="${not empty error}">
                <div class="bg-red-600 text-white text-center py-2 rounded mb-4 font-bold">
                    <p style="color: rgb(125, 47, 47)">${error}</p>
                </div>
            </c:if>

            <form action="${mvc.uri('login')}" class="form-horizontal" method="POST">
                <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                <div class="flex flex-col gap-4">
                    <label for="username" class="text-white">Username:</label>
                    <input 
                        type="text" 
                        id="username" 
                        name="username" 
                        class="px-4 py-2 rounded bg-gray-700 text-white" 
                        required>

                    <label for="password" class="text-white">Password:</label>
                    <input 
                        type="password" 
                        id="password" 
                        name="password" 
                        class="px-4 py-2 rounded bg-gray-700 text-white" 
                        required>

                    <button 
                        type="submit" 
                        class="mt-4 px-6 py-2 rounded-full bg-primary hover:bg-primary-dark text-white">
                        Log In
                    </button>
                </div>
            </form>
            <div class="text-center mt-4">
                <p class="text-gray-400">Don't have an account? 
                    <a href="<c:url value='/Web/Register' />" class="text-primary text-white hover:underline">Register</a>
                </p>
            </div>

        </div>
    </body>
</html>
