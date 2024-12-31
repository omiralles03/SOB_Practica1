<header class="bg-neutral-900 border-b border-neutral-700 px-4 sticky top-0 z-50">
  <nav class="h-20 flex items-center justify-between">
    <!-- Left Section: Logo and Menu Button -->
    <div class="flex items-center gap-4 w-1/4">
      <!-- Logo -->
      <a href=<c:url value="/Web/Home"/> class="flex items-center gap-2">
        <img src="${pageContext.request.contextPath}/resources/img/SOBlog.png" width="56" height="56">
        </img>
        <span class="text-3xl font-bold text-white hidden md:block">SOBlog</span>
      </a>
    </div>

    <!-- Center Section: Search Bar -->
    <div class="flex-1 mx-4 max-w-lg">
      <form action="/search" method="GET" class="relative flex items-center w-full">
        <!-- Search Icon -->
        <button type="submit" class="absolute left-3 h-8 w-8 flex items-center justify-center">
          <svg class="w-5 h-5 text-gray-500 dark:text-gray-400" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
          </svg>
        </button>
        <!-- Search Input -->
        <input 
          type="search" 
          name="q" 
          placeholder="Search..." 
          maxlength="25" 
          class="w-full h-10 pl-12 pr-4 py-2 text-sm text-white bg-[#333d42] rounded-full focus:outline-none focus:ring-2 focus:ring-[#3d464c] hover:bg-[#3d464c] transition-colors duration-300"
        >
      </form>
    </div>

    <!-- Right Section: Login Button -->
    <div class="flex items-center gap-4 w-1/4 justify-end">
      <a href="/login" 
         class="flex items-center justify-center px-6 py-2 bg-[#f0895c] text-white rounded-full hover:bg-[#e07750] focus:outline-none focus:ring-2 focus:ring-[#e07750] transition-colors duration-300">
        Log In
      </a>
    </div>

  </nav>
</header>
