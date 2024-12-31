<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty flashMessage and not empty flashMessage.text}">
    <div class="alert alert-${flashMessage.type} alert-dismissible"
        role="alert">
        ${flashMessage.text}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span class="fa fa-times-circle" aria-hidden="true">${null}</span>
        </button>
    </div>
</c:if>   
<c:if test="${not empty errors}">
    <c:forEach var="error" items="${errors.errors}">
        <div class="alert alert-danger alert-dismissible"
            role="alert">
            <strong>${error.field}</strong>: ${error.message}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span class="fa fa-times-circle" aria-hidden="true">${null}</span>
            </button>
        </div>
    </c:forEach>
</c:if>   

