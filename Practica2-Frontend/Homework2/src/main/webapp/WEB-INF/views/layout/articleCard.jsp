<div class="text-white overflow-hidden relative w-[700px] mx-auto bg-transparent hover:bg-[#181c1f] transition duration-300">
    <a href="<c:url value='/Web/Article/${article.id}' />">
        <!-- Separation Line -->
        <div class="h-[3px] bg-neutral-700 w-full mb-4"></div>

        <!-- Row 1: Author and Date -->
        <div class="flex justify-between items-center px-6 py-2">
            <div class="flex items-center gap-2 text-gray-400 text-lg">
                <img 
                    src="${article.authorProfileURL}"
                    alt="${article.authorUsername}"
                    class="w-8 h-8 rounded-full">
                <p class="font-bold text-gray-200">${article.authorUsername}</p>
                <span class="text-xl">·</span>
                <p class="text-sm">${article.publishedAt}</p>
            </div>
        </div>

        <!-- Row 2: Title -->
        <div class="px-6 py-2">
            <h2 class="text-2xl font-bold">${article.title}</h2>
        </div>

        <!-- Row 3: Summary (only if it exists) -->
        <c:if test="${not empty article.summary}">
            <div class="px-6 py-2">
                <p class="text-lg text-gray-300">${article.summary}</p>
            </div>
        </c:if>

        <!-- Row 4: Image (only if it exists) -->
        <c:if test="${not empty article.imageURL}">
            <div class="relative h-96 w-full px-4 overflow-hidden rounded-3xl bg-neutral-900/30">
                <!-- Blurred Background -->
                <div 
                    class="absolute inset-0 blur-md bg-cover bg-center rounded-3xl"
                    style="background-image: url('${article.imageURL}')">
                </div>
                <!-- Main Image -->
                <img 
                    src="${article.imageURL}" 
                    alt="${article.title}" 
                    class="relative z-10 h-full w-auto mx-auto">
            </div>
        </c:if>

        <!-- Row 5: Views and Privacy -->
        <div class="flex justify-between items-center px-6 py-2 text-gray-400">
            <div class="flex items-center gap-2">
                <!-- Replace Inline SVG with External SVG -->
                <img 
                    src="${pageContext.request.contextPath}/resources/img/views.svg" 
                    alt="Views Icon" 
                    class="w-5 h-5">
                <p>${article.views}</p>

                <!-- Privacy Icon -->
                <c:if test="${article.isPrivate}">
                    <svg class="w-5 h-5 text-yellow-400" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z"></path>
                    </svg>
                </c:if>
            </div>
        </div>
    </a>

    <!-- Row 6: Actions -->
    <div class="flex justify-start items-center gap-4 px-6 py-2">
        <button class="flex items-center justify-center px-6 py-2 bg-[#333d42] text-white rounded-full hover:bg-[#3d464c] focus:outline-none focus:ring-2 focus:ring-[#3d464c]">
            <img 
                src="${pageContext.request.contextPath}/resources/img/comment.svg" 
                alt="Comment Icon" 
                class="w-5 h-5 mr-2">
            <span>Comment</span>
        </button>
        <button class="flex items-center justify-center px-6 py-2 bg-[#333d42] text-white rounded-full hover:bg-[#3d464c] focus:outline-none focus:ring-2 focus:ring-[#3d464c]">
            <img 
                src="${pageContext.request.contextPath}/resources/img/share.svg" 
                alt="Share Icon" 
                class="w-5 h-5 mr-2">
            <span>Share</span>
        </button>
        <button class="flex items-center justify-center px-6 py-2 bg-[#333d42] text-white rounded-full hover:bg-[#3d464c] focus:outline-none focus:ring-2 focus:ring-[#3d464c]">
            <img 
                src="${pageContext.request.contextPath}/resources/img/save.svg" 
                alt="Save Icon" 
                class="w-5 h-5 mr-2">
            <span>Save</span>
        </button>
    </div>
</div>
