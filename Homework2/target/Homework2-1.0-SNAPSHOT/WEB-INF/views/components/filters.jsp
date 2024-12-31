<%-- 
    Document   : filters
    Created on : Dec 28, 2024, 8:34:02 PM
    Author     : oupma
--%>

<div class="filters bg-gray-100 p-4">
    <form method="GET" action="${pageContext.request.contextPath}/articles">
        <label for="author" class="block">Filter by Author</label>
        <select name="author" id="author" class="block w-full mt-1">
            <option value="">All Authors</option>
            <c:forEach items="${authors}" var="author">
                <option value="${author}">${author}</option>
            </c:forEach>
        </select>
        <label for="topic" class="block mt-4">Filter by Topic</label>
        <select name="topic" id="topic" class="block w-full mt-1">
            <option value="">All Topics</option>
            <c:forEach items="${topics}" var="topic">
                <option value="${topic}">${topic}</option>
            </c:forEach>
        </select>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 mt-4">Apply Filters</button>
    </form>
</div>
