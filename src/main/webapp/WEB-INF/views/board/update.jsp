<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="" method="post">
        <input type="hidden" id="id" value="${board.id}">
        <div class="form-group">
            <input value="${board.title}" type="text"  class="form-control" placeholder="Enter Title" id="title">
        </div>


        <div class="form-group">
            <textarea id="content" cols="30" rows="10" class="form-control summernote" placeholder="Enter Content">${board.content}</textarea>
        </div>


    </form>
    <button id="btn-update" class="btn btn-primary">글 수정 완료</button>
</div>
<script src="/js/board.js"></script>
<script>
    $('.summernote').summernote({
        placeholder: 'Enter Content',
        tabsize: 2,
        height: 300
    });
</script>

<%@ include file="../layout/footer.jsp"%>




