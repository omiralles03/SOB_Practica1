<div class="flex items-center gap-4 text-white bg-transparent hover:bg-[#181c1f] py-4 px-6 rounded-lg transition duration-300 shadow-lg">
    <!-- User Image -->
    <img 
        src="${user.imageURL}"
        alt="${user.username}" 
        class="w-16 h-16 rounded-full object-cover border border-gray-600">
    
    <!-- Username -->
    <div class="flex-1 min-w-0">
        <p class="text-lg font-bold text-gray-200 truncate">${user.username}</p>
    </div>
</div>
