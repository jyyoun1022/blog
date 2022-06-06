<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>
<div class="d-flex justify-content-center flex-column align-items-center">
    <div class="d-flex justify-content-center align-items-center">
    <div class="card m-2" syle="width:200px">
            <img  src="/images/myprofile.png.png" width="120" height="120" alt="Card image">
    </div>
        <div class="card-body">
            <h4 class="card-title">codeJ</h4>
            <p class="card-text">안되면 될때 까지</p>
            <a href="https://github.com/jyyoun1022" class="btn btn-primary">Git hub</a>
    </div>
    </div>
<%--    <div class="d-flex justify-content-center align-items-center">--%>
    <div class="container">
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2" >
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
            </div>
        </div>
    </c:forEach>
        <ul class="pagination justify-content-center">
            <c:choose >
                <c:when test="${boards.first}">
                    <li class="page-item disabled"><a class="page-link" href="/?page=${boards.number-1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/?page=${boards.number-1}">Previous</a></li>

                </c:otherwise>
            </c:choose>
           <c:choose>
               <c:when test="${boards.last}">
                   <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
               </c:when>
               <c:otherwise>
                   <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
               </c:otherwise>
           </c:choose>
        </ul>

<%--    </div>--%>
</div>
</div>
<%@ include file="layout/footer.jsp"%>



