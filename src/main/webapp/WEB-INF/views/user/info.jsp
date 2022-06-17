<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="username"  value="${principal.user.username}" readonly>
        </div>
        <c:if test="${not empty principla.user.oauth}">
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" placeholder="Enter Password" id="password" autocomplete="off" >
            </div>
        </c:if>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter Email" id="email" value="${principal.user.email}" readonly>
        </div>

    </form>
        <button id="btn-update" class="btn btn-primary">회원 수정 완료</button>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>




