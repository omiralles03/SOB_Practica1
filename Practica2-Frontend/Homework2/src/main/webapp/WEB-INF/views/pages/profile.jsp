<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tailwindcss.css">
    </head>
    <body class="flex flex-col min-h-screen bg-neutral-900 text-white">
        <%@ include file="../layout/navbar.jsp" %>

        <!-- Main Content Wrapper -->
        <div class="flex flex-col lg:flex-row w-full">
            <!-- Left Sidebar -->
            <%@ include file="../layout/sidebar.jsp" %>

            <!-- Main Section -->
            <main class="flex-1 mx-auto max-w-5xl px-6 py-8">
                <!-- Profile Header -->
                <div class="flex items-center gap-6 mb-8">
                    <!-- Profile Picture -->
                    <div class="w-32 h-32 rounded-full overflow-hidden">
                        <img 
                            src="${sessionScope.profileImageURL}" 
                            alt="User Avatar"
                            class="w-full h-full object-cover">
                    </div>

                    <!-- User Info -->
                    <div>
                        <h1 class="text-3xl font-bold">${sessionScope.username}</h1>
                        <a 
                            href="<c:url value='/Web/Settings'/>" 
                            class="mt-2 inline-block text-primary hover:underline">Edit Profile</a>
                    </div>

                </div>

                <!-- Articles Section -->
                <div>
                    <h2 class="text-2xl font-bold mb-6">Last Article Published</h2>
                    <!-- Last Article Published -->
                   <c:if test="${not empty article}">
                       <%@include file="../layout/articleCard.jsp" %>
                   </c:if>                    
                    <c:if test="${empty article}">
                        <p class="text-gray-400">You have not published any articles yet.</p>
                    </c:if>
                </div>
            </main>

            <!-- Right Sidebar (Optional) -->
            <aside class="hidden lg:block w-80">
                <!-- Placeholder for future content -->
            </aside>
        </div>

        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>