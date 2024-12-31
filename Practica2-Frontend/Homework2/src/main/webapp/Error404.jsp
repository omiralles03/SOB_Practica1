<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>404 Error Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <style>
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 1px solid rgba(0, 0, 0, 0.04);
            border-radius: .25rem;
        }

        .card .card-header {
            background-color: #fff;
            border-bottom: none;
        }
    </style>
  </head>
  <body>
    <div class="container">
        <div class="row text-center">
            <div class="col-md-12 col-sm-12">
                <div class="card shadow-lg border-0 rounded-lg mt-5 mx-auto" style="width: 30rem;">
                    <h3 class="card-header display-1 text-muted text-center">
                        404
                    </h3>
                    <span class="card-subtitle mb-2 text-muted text-center">
                        Page Could Not Be Found 
                    </span>

                     <div class="card-body mx-auto">
                        <a href="<c:url value="/Web/SignUp" />" class="btn btn-sm btn-info text-white"> Back To Home </a>
                     </div>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
