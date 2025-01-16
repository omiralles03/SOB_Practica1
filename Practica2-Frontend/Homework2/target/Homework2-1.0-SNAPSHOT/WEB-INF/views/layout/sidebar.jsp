<aside class="hidden lg:block w-80 bg-neutral-900 border-r border-neutral-700 sticky top-20 h-[calc(100vh-5rem)]">
    <div class="p-4">
        <h2 class="text-lg font-bold text-gray-200">Communities</h2>
        <ul class="mt-4 space-y-2">
            <li>
                <a href="<c:url value='/Web/Home' />" class="block text-gray-400 hover:text-blue-500">Home</a>
            </li>
            <li>
               <a href="<c:url value='/Web/Users' />" class="block text-gray-400 hover:text-blue-500">Users</a>
            </li>
            <li>
                <a href="#" id="randomArticle" class="block text-gray-400 hover:text-blue-500">Random Article</a>
            </li>
            <li>
                <a href="<c:url value='/Web/Error404' />" class="block text-gray-400 hover:text-blue-500">Error 404</a>
            </li>
        </ul>
    </div>
</aside>

<script>
    document.getElementById("randomArticle").addEventListener("click", function (event) {
        // Evita la navegació immediata
        event.preventDefault();

        // Genera un número aleatori entre 1 i el total d'articles
        const randomId = Math.floor(Math.random() * ${sessionScope.totalArticles}) + 1;

        // Redirigeix l'usuari al link de l'article aleatori
        window.location.href = `<c:url value='/Web/Article/' />` + randomId;
    });
</script>
