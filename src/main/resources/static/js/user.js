let index = {
    init:function (){
        $("#btn-save").on("click",()=>{
            this.save();
        });

    },

    save: function () {

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        let data1 = JSON.stringify(data);


        // console.log(data);
        $.ajax({
            type: "POST",
            url: "/auth/join",
            data: JSON.stringify(data),//http body 데이터
            contentType: "application/json; charset=utf-8",
            dataType: "json"
           //body 데이터가 어떤 타입인지(MIME)
              //요청을 서버로 하여 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if(res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function (error){

            alert(JSON.stringify(error));
        });//ajax통신을 이용해서 3개의 파라미터를 데이터를 json으로 변경하여 insert요청
    }

}

index.init();