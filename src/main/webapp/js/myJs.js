//格式化时间
function add0(m){return m<10?'0'+m:m }
function format(shijianchuo){
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    return y+'-'+add0(m)+'-'+add0(d);
}

//封装分页方法
function page(typeId,pn){
    currentPage = pn;
    $.ajax({
        type:"get",
        url:"manage/book/listByType",
        data:{
            typeId:typeId,
            pageNum:pn,
            pageSize:10
        },
        dataType:"json",
        success:function (response) {
            var data = response.data.list;
            var bookList = $("#bookList");
            bookList.empty();
            findTypeName(typeId)
            for (var i = 0 ; i < data.length ; i ++){
                bookList.append("<li class='ease'><a href='list_info.html?id="+ data[i].book_id +"'><img src='"+ data[i].book_picture +"'></a>\n" +
                    "    <p class='s_n'><a href='list_info.html?id="+ data[i].book_id +"'>"+ data[i].book_name +"</a></p>\n" +
                    "    </li>")
            }
            //分页导航
            var nums = response.data.navigatepageNums;
            $("#pages").empty();
            $("#pages").append("<li onclick='page("+ typeId +","+ response.data.firstPage +")'><a href='#'>首页</a></li>");
            if (currentPage == response.data.firstPage){
                $("#pages").append("<li class='disabled'>上一页</li>");
            } else {
                $("#pages").append("<li onclick='page("+ typeId +","+ (currentPage - 1) +")'><a href='#'>上一页</a></li>");
            }
            for (var i = 0 ; i < nums.length ; i ++){
                $("#pages").append("<li onclick='page("+ typeId +"," + nums[i] + ")'><a>" + nums[i] + "</a></li>");
            }
            if (currentPage == response.data.lastPage){
                $("#pages").append("<li class='disabled'>下一页</li>");
            }else {
                $("#pages").append("<li onclick='page("+ typeId +","+ (currentPage + 1) +")'><a href='#'>下一页</a></li>");
            }
            $("#pages").append("<li li onclick='page("+ typeId +","+ response.data.lastPage +")'><a href='#'>尾页</a></li>");
        }
    });
}

//根据类别id查类名
function findTypeName(typeId) {
    $.ajax({
        type : "get",
        url : "manage/type/get_name",
        data : {
            id : typeId
        },
        dataType : "json",
        success : function(response) {
            if (response.status == 0) {
                alert("获取父类别失败，请重新获取！！！");
                return;
            } else {
                $("#thisType").empty();
                $("#thisType").text(response.msg);
            }
        }
    })
}

//封装查询子类别
function getChild(num,fatherid) {
    $.ajax({
        type:"get",
        url:"manage/type/selectChild",
        data:{
            num:num
        },
        dataType:"json",
        success:function (response) {
            var data = response.data;
            var typeList = $(fatherid)
            for (var i = 0;i < data.length;i ++){
                typeList.append("<li onclick='page("+ data[i].type_id +",1)'><a href='javascript:;'>"+ data[i].type_name +"</a></li>")
            }
        }
    });
}

//读取单个图书
function showBook(id) {
    var thisBook = null;
    $.ajax({
        type:"get",
        url:"manage/book/findBookById",
        async: false,
        data:{
            id:id
        },
        dataType:"json",
        success:function (response) {
            var book = response.data;
            thisBook = book;
            $("#bookName").html(book.book_name);
            $("#bookName2").html(book.book_name);
            $("#bookPic").attr("src",book.book_picture);
            $("#bookAuthor").append(book.book_author);
            $("#bookDesc").html(book.book_description);
            $("#bookPub").append(book.book_pub);
            $("#bookPrice").append(book.price);
            findTypeName(book.book_type_id);
        }
    });
    return thisBook;
}



//读取图书评论
function showMessage(id,pn,thisUser){
    currentPage = pn;
    $.ajax({
        type:"get",
        url:"manage/message/byBookId",
        async: false,
        data:{
            bookId:id
        },
        dataType:"json",
        success:function (response) {
            var data = response.data.list;
            var messageList = $("#messageList");
            messageList.empty();
            for (var i = 0 ; i < data.length ; i ++){
                if (0 == data[i].reply || null == data[i].reply){
                    var list = $("<div class='com_bor'></div>");
                    var deleteButton = "";
                    if (data[i].stu_id == thisUser.stu_id){
                        deleteButton = "删除";
                    }
                    list.append("       <li class='go'>\n" +
                        "         <div class='com_1 clear'>\n" +
                        "              <div class='portrait left'>\n" +
                        "                  <img src='"+ data[i].photo +"'><br/>\n" +
                        "                  <span>"+ data[i].stu_name +"</span>\n" +
                        "              </div>\n" +
                        "             <div class='word left kdit_w'>\n" +
                        "                  <p>"+ data[i].mescontent +"</p>\n" +
                        "                  <p class='right'><a href='javascript:;' onclick='deleteMsg("+ data[i].mesid +")'>"+ deleteButton +"</a>\n" +
                        "                  <time>"+ format(data[i].time) +"</time></p>\n" +
                        "             </div>\n" +
                        "          </div><!--评论内容-->\n" +
                        "        </li>  <!--第一条评论内容-->");
                    messageList.append(list);
                }
            }
            //分页导航
            var nums = response.data.navigatepageNums;
            $("#pages").empty();
            $("#pages").append("<li onclick='page(" + response.data.firstPage +")'><a href='#'>首页</a></li>");
            if (currentPage == response.data.firstPage){
                $("#pages").append("<li class='disabled'>上一页</li>");
            } else {
                $("#pages").append("<li onclick='page("+ (currentPage - 1) +")'><a href='#'>上一页</a></li>");
            }
            for (var i = 0 ; i < nums.length ; i ++){
                $("#pages").append("<li onclick='page("+ nums[i] + ")'><a>" + nums[i] + "</a></li>");
            }
            if (currentPage == response.data.lastPage){
                $("#pages").append("<li class='disabled'>下一页</li>");
            }else {
                $("#pages").append("<li onclick='page("+ (currentPage + 1) +")'><a href='#'>下一页</a></li>");
            }
            $("#pages").append("<li li onclick='page("+ response.data.lastPage +")'><a href='#'>尾页</a></li>");
        }
    });
}

