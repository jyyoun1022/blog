<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
    <form action="" method="post">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text"  class="form-control" placeholder="Enter Title" id="title">
        </div>

        <div class="form-group">
            <label for="content">Content:</label>
            <textarea id="content" cols="30" rows="10" class="form-control summernote" placeholder="Enter Content"></textarea>
        </div>


    </form>
    <button id="btn-save" class="btn btn-primary">글쓰기 완료</button>
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




