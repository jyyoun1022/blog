<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <a class="btn btn-primary" href="/">리스트</a>
        <c:if test="${board.user.id == principal.user.id}">
            <a  href="/board/${board.id}/update" class="btn btn-warning" >수정</a>
            <button class="btn btn-danger" id="btn-delete">삭제</button>
        </c:if>
        <br/><br/>
        <div>
            <div class="d-flex justify-content-between">
           <h4> 글 번호 : <span id="id"><i>${board.id}</i></span></h4>
            <h6>조회수 : ${board.viewCount}</h6>
            </div>
            <hr/>
           <h4> 작성자 : <span><i>${board.user.username}</i></span></h4>
        </div>
        <br/><br/>
        <div class="form-group">
            <h3 id="title">${board.title}</h3>
        </div>
        <hr />
        <div class="form-group">
            <div id="content">
                ${board.content}
            </div>
        </div>
        <hr/>


        <div class="card">
            <form action="">
                <input type="hidden" id="boardId" value="${board.id}"/>
                <input type="hidden" id="userId" value="${principal.user.id}"/>
                <div class="card-body">
                    <textarea id="reply-content" rows="1" class="form-control"></textarea>
                </div>
                <div class="card-footer" id="btn-reply-save">
                    <button type="button" class="btn btn-primary">댓글 등록</button>
                </div>
            </form>
        </div>
        <br/>
        <div class="card">
            <b><div class="card-header">댓글 리스트</div></b>
            <ul id="reply--box" class="list-group">
                <c:forEach var="reply" items="${board.replys}">
                    <li id="reply--${reply.id}" class="list-group-item d-flex justify-content-between">
                        <b><div>${reply.content}</div></b>
                        <div class="d-flex ">
                            <b><div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div></b>
                            <button onclick="index.replyDelete(${board.id},${reply.id})" class="badge btn btn-danger">삭제</button>
                        </div>
                    </li>

                </c:forEach>
            </ul>
        </div>
    </div>
<script src="/js/board.js"></script>


<%@ include file="../layout/footer.jsp"%>




