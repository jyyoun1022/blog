<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <a class="btn btn-primary" href="/">리스트</a>
        <c:if test="${board.user.id == principal.user.id}">
            <a  href="/board/${board.id}/update" class="btn btn-warning" >수정</a>
            <button class="btn btn-danger" id="btn-delete">삭제</button>
        </c:if>
        <br/><br/>
        <div>
           <h4> 글 번호 : <span id="id"><i>${board.id}</i></span></h4>
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
                <div class="card-body"><textarea rows="1" class="form-control"></textarea></div>
                <div class="card-footer"><button class="btn btn-primary">댓글 등록</button></div>
        </div>
        <br/>
        <div class="card">
            <b><div class="card-header">댓글 리스트</div></b>
            <ul id="comment--box" class="list-group">
                <li id="comment--1" class="list-group-item d-flex justify-content-between">
                    <b><div>댓글 내용입니다</div></b>
                    <div class="d-flex ">
                        <b><div class="font-italic">작성자 : codeJ &nbsp;</div></b>
                        <button class="badge btn btn-danger">삭제</button>
                    </div>
                </li>
            </ul>
        </div>
    </div>
<script src="/js/board.js"></script>


<%@ include file="../layout/footer.jsp"%>




