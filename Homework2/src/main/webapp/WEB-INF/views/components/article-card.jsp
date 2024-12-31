<%-- 
    Document   : article-card
    Created on : Dec 28, 2024, 8:33:55 PM
    Author     : oupma
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="article-card border p-4">
    <img src="${article.imageURL}" alt="${article.title}" class="w-full h-40 object-cover">
    <h2 class="text-lg font-bold mt-2">${article.title}</h2>
    <p class="text-sm text-gray-600">${article.publishedAt} - ${article.views} views</p>
    <p class="text-gray-800 mt-2">${article.summary}</p>
    <c:if test="${article.isPrivate}">
        <span class="text-red-500">Private</span>
    </c:if>
</div>

