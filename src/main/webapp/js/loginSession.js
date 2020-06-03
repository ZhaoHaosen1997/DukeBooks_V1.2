//获取登录学生
function getStudent(){
    var data;
    $.ajax({
        type:"get",
        async: false,
        url:"manage/student/getUser",
        dataType:"json",
        success:function (response) {
            data = response.data;
            var loginStatus = $("#loginStatus");
            if (null == data){
                loginStatus.empty();
                loginStatus.append("    <div class='delu'>\n" +
                    "        <a  href='login/index.html'>登录/注册</a>\n" +
                    "        <span>|</span>\n" +
                    "        <a  href='adminLogin.html'>管理员登录</a>\n" +
                    "    </div>")
            } else {
                loginStatus.empty();
                loginStatus.append("<div class='delu2 clear'>\n" +
                    "         <div class='self ease'>\n" +
                    "             <a href='javascript:;' class='clear'>"+ data.stu_name +"<span class=\"icon ease\"></span></a>\n" +
                    "             <div class='clear'></div>\n" +
                    "             <ul  class='clear'>\n" +
                    "                 <li><a href='self.html'>个人中心</a></li>\n" +
                    "                 <li><a href='self.html'>我的书架</a></li>\n" +
                    "                 <li><a href='self.html'>账号设置</a></li>\n" +
                    "                 <li><a href='self.html'>上传文件</a></li>\n" +
                    "                 <li><a href='manage/student/logout'>退出登录</a></li>\n" +
                    "             </ul>\n" +
                    "         </div>");
            }
        }
    });
    return data;
}

//获取登录管理员
function getAdmin() {
    var admin
    $.ajax({
        type: "get",
        url: "manage/admin/getUser",
        dataType: "json",
        async: false,
        success: function (response) {
            var data = response.data;
            admin = data;
            var loginStatus = $("#loginStatus");
            if (null == data) {
                loginStatus.empty();
                loginStatus.append("<li><a href='adminLogin.html'>登录</a></li>")
            } else {
                loginStatus.empty();
                if (data.admin_id == 1){
                    loginStatus.append("<li><a href='manage/admin/logout'><b style='color: red'>超级管理员</b> "+ data.admin_name +" 已登录，点击退出</a></li>");
                }else {
                    loginStatus.append("<li><a href='manage/admin/logout'>管理员 "+ data.admin_name +" 已登录，点击退出</a></li>");
                }
            }
        }
    });
    return admin;
}
