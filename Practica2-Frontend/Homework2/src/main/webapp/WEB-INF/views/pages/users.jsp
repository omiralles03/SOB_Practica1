<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/tailwindcss.css">
    </head>
    <body class="flex flex-col min-h-screen bg-neutral-900">
        <%@ include file="../layout/navbar.jsp" %>

        <!-- Main Content Wrapper -->
        <div class="flex w-full">
            <!-- Left Sidebar -->
            <%@ include file="../layout/sidebar.jsp" %>

            <!-- Main Section -->
            <main class="flex-1">
                <div class="grid gap-4 w-full max-w-5xl mx-auto px-4 mt-4"
                     style="grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));">
                    <c:forEach var="user" items="${users}">
                        <%@ include file="../layout/userBanner.jsp" %>
                    </c:forEach>
                </div>
            </main>

            <!-- Right Sidebar -->
            <aside class="hidden lg:block w-80">
                <!-- Placeholder for future content -->
            </aside>
        </div>

        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
