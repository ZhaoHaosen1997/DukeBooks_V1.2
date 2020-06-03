function login(){
    var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    var regEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    if(username==""){
        $.jGrowl("邮箱不能为空！", { header: '提醒' });
    }else if(!regEmail.test(username)){
        $.jGrowl("邮箱格式不正确！", { header: '提醒' });
    }else if(password==""){
        $.jGrowl("密码不能为空！", { header: '提醒' });
    }else{
        AjaxFunc();
    }
}
function AjaxFunc()
{
    var username = document.getElementById("ID").value;
    var password = document.getElementById("PASSWORD").value;
    $.ajax({
        type:"post",
        url:"../manage/student/login",
        data:{
            name:username,
            password:password
        },
        dataType:"json",
        //回调方法
        success:function (response) {
            if (response.status == 1){
                window.location.replace("../index.html");
            }else {
                $.jGrowl(response.msg, { header: '提醒' });
                document.getElementById("ID").val("");
                document.getElementById("PASSWORD").val("");
            }
        }
    });
}