<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
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
                <!-- Filter Section -->
                <div class="flex flex-col items-center">
                    <div class="flex justify-between items-center w-[700px] mx-auto my-4">
                        <button class="text-white bg-neutral-700 hover:bg-neutral-600 px-4 py-2 rounded-lg">
                            Sort by Popularity
                        </button>
                        <div class="relative">
                            <button class="text-white bg-neutral-700 hover:bg-neutral-600 px-4 py-2 rounded-lg">
                                Change Format
                            </button>
                            <div class="absolute right-0 mt-2 bg-neutral-800 rounded-lg shadow-lg hidden">
                                <button class="block text-white px-4 py-2 hover:bg-neutral-700">
                                    Card View
                                </button>
                                <button class="block text-white px-4 py-2 hover:bg-neutral-700">
                                    Compact View
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex flex-col gap-6">
                   <c:forEach var="article" items="${articles}">
                       <%@ include file="../layout/articleCard.jsp" %>
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
