<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${article.title}</title>
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
                
            </main>

            <!-- Right Sidebar -->
            <aside class="hidden lg:block w-80">
                <!-- Placeholder for future content -->
            </aside>
        </div>
        
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
