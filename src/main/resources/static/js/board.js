let index = {
    init:function (){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
        $("#btn-update").on("click",()=>{
            this.update();
        });
        $("#btn-reply-save").on("click",()=>{
            this.replySave();
        });$("#btn-reply-Delete").on("click",()=>{
            this.replyDelete();
        })


    },

    save: function () {

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            if(res.status === 500) {
                alert("글 작성이 실패하였습니다.");
            } else {
                alert("글 작성이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });//ajax통신을 이용해서 3개의 파라미터를 데이터를 json으로 변경하여 insert요청
    },
    deleteById : function (){
        let id = $("#id").text();

        $.ajax({
            type: "DELETE",
            url: "/api/board/"+id,
            dataType: "json"
        }).done(function (res) {
            if(res.status === 500) {
                alert("글 삭제가 실패하였습니다.");
            } else {
                alert("글 삭제가 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });
    },
    update : function (){

        let id =$("#id").val();

        let data ={
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/"+id,
            data:JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            if(res.status === 500) {
                alert("글 수정이 실패하였습니다.");
            } else {
                alert("글 수정이 완료되었습니다.");
                location.href = "/board/"+id;
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });
    },
    replySave : function (){
        let data ={
            content : $("#reply-content").val(),
            userId : $("#userId").val(),
            boardId : $("#boardId").val()
        };
        console.log(data);

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            if(res.status === 500) {
                alert("댓글 작성이 실패하였습니다.");
            } else {
                alert("댓글 작성이 완료되었습니다.");
                location.href = `/board/${data.boardId}`;
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });
    },
    replyDelete : function (boardId,replyId){

        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function (res) {
            if(res.status === 500) {
                alert("댓글 삭제 실패하였습니다.");
            } else {
                alert("댓글 삭제가 완료되었습니다.");
                location.href = `/board/${boardId}`;
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });
    }

}

index.init();