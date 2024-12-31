<%-- 
    Document   : navbar
    Created on : Dec 28, 2024, 8:32:35 PM
    Author     : oupma
--%>

<nav class="bg-gray-800 p-4">
    <div class="container mx-auto flex items-center justify-between">
        <a href="<c:url value='/Home' />" class="text-white font-bold">MyApp</a>
        <div>
            <a href="<c:url value='/Login' />" class="text-gray-300 hover:text-white">Login</a>
            <a href="<c:url value='/Signup' />" class="ml-4 text-gray-300 hover:text-white">Sign Up</a>
        </div>
    </div>
</nav>


