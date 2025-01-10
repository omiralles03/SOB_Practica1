<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>404 Error Page</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-neutral-900 flex items-center justify-center min-h-screen">
        <div class="bg-neutral-800 text-center text-white p-8 rounded-lg shadow-lg w-96">
            <h1 class="text-6xl font-bold mb-4 text-gray-300">404</h1>
            <p class="text-lg text-gray-400 mb-6">Page Could Not Be Found</p>
            <a href="<c:url value='/Web/Home' />" class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-400">
                Back To Home
            </a>
        </div>
    </body>
</html>
