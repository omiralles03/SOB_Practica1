<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - MyApp</title>
    <!-- Tailwind CSS -->
    <link rel="stylesheet" href="/css/tailwind.css">
</head>
<body class="bg-gray-100">

    <!-- Navbar -->
    <nav class="bg-gray-800 p-4">
        <div class="container mx-auto flex items-center justify-between">
            <a href="${pageContext.request.contextPath}/Home" class="text-white font-bold text-lg">MyApp</a>
            <div>
                <a href="${pageContext.request.contextPath}/Login" class="text-gray-300 hover:text-white mr-4">Login</a>
                <a href="${pageContext.request.contextPath}/Signup" class="text-gray-300 hover:text-white">Sign Up</a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <main class="container mx-auto mt-8">
        <h1 class="text-3xl font-bold text-center mb-6">Welcome to MyApp</h1>

        <!-- Filters Section -->
        <div class="flex justify-between items-center mb-8">
            <div>
                <label for="topic-filter" class="mr-2">Filter by Topic:</label>
                <select id="topic-filter" class="border border-gray-300 rounded px-4 py-2">
                    <option value="all">All</option>
                    <option value="javascript">JavaScript</option>
                    <option value="blockchain">Blockchain</option>
                    <option value="webassembly">WebAssembly</option>
                </select>
            </div>
            <div>
                <label for="author-filter" class="mr-2">Filter by Author:</label>
                <input id="author-filter" type="text" placeholder="Author name"
                       class="border border-gray-300 rounded px-4 py-2">
            </div>
        </div>

        <!-- Articles Section -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            <div class="bg-white shadow-md rounded overflow-hidden">
                <img src="https://picsum.photos/200/300" alt="Article Image" class="w-full h-48 object-cover">
                <div class="p-4">
                    <h2 class="text-xl font-bold mb-2">Article Title</h2>
                    <p class="text-sm text-gray-600 mb-4">Author: <span class="text-gray-800">John Doe</span></p>
                    <p class="text-sm text-gray-600 mb-4">Published: <span class="text-gray-800">2024-12-28</span></p>
                    <p class="text-gray-700 text-sm">This is a short summary of the article that provides a quick
                        overview...</p>
                </div>
            </div>
            <!-- Repeat this block for more articles -->
        </div>
    </main>

    <!-- Footer -->
    <footer class="bg-gray-800 text-gray-300 p-4 mt-8 text-center">
        <p>&copy; 2024 MyApp. All rights reserved.</p>
    </footer>

</body>
</html>
