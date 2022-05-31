let index = {
    init:function (){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },

    save: function () {
        // console.log("user의 save함수");
        let data ={
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val()
        }
        // console.log(data);
        $.ajax({
            type: "post",
            url: "/blog/api/user",
            data: JSON.stringify(data),//http body 데이터
            contentType: "application/json; charset=utf-8",//body 데이터가 어떤 타입인지
            dataType: "json",    //요청을 서버로 하여 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경

        }).done(function (resp){
            alert("회원가입이 완료되었습니다.");
            location.href = "/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });//ajax통신을 이용해서 3개의 파라미터를 데이터를 json으로 변경하여 insert요청
    }
}

index.init();