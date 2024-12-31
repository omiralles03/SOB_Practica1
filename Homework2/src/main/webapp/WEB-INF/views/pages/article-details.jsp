<%-- 
    Document   : detail
    Created on : Dec 28, 2024, 8:33:31 PM
    Author     : oupma
--%>

<%@ include file="/WEB-INF/layout/baseLayout.jsp" %>
<div class="article-details">
    <img src="${article.imageURL}" alt="${article.title}" class="w-full h-64 object-cover">
    <h1 class="text-3xl font-bold mt-4">${article.title}</h1>
    <p class="text-sm text-gray-600">${article.publishedAt} - ${article.views} views</p>
    <div class="text-gray-800 mt-4">${article.body}</div>
</div>

