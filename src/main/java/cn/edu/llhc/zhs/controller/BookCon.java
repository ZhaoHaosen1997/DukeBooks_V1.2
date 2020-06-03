package cn.edu.llhc.zhs.controller;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Book;
import cn.edu.llhc.zhs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

@RestController
@RequestMapping("/manage/book")
public class BookCon {

    @Autowired
    private BookService bookService;

    private String name = null;

    @ResponseBody
    @RequestMapping("/list")
    public HigherResponse<Object> pageCon(@RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                          @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQueryBook(pageNum,pageSize);
    }

    @RequestMapping("/listDesc")
    public HigherResponse<Object> pageConDesc(@RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                          @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQueryBookDesc(pageNum,pageSize);
    }



    @ResponseBody
    @RequestMapping("/listByType")
    public HigherResponse<Object> pageQueryBookByType(@RequestParam(required = true)Integer typeId,
                                                      @RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                                      @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQyeryBookByTypr(typeId,pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/search")
    public HigherResponse<Object> pageQuerySearch(@RequestParam(required = true)String noa,
                                                  @RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                                  @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQuerySearch(noa,pageNum,pageSize);
    }

    @RequestMapping("/uploadFile")
    public HigherResponse<Object> uploadFile(MultipartFile file, HttpServletRequest request){
        if (null == file){
            return HigherResponse.getResponseFaild("上传失败");
        }
        name = file.getOriginalFilename();
        try {
            String path = request.getServletContext().getRealPath("/");
            path += "\\library\\";
            path += name;
            System.out.println(path);
            file.transferTo(new File(path));
            System.out.println(name+"上传成功！");
            return HigherResponse.getResponseSuccess(name,"成功");
        } catch (IOException e) {
            e.printStackTrace();
            return HigherResponse.getResponseFaild("失败");
        }
    }

    @RequestMapping("/findBookById")
    public HigherResponse<Book> findBookById(Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("/uploadBook")
    public HigherResponse uploadBook(String book_name,Integer book_type_id,String book_author,String book_pub,
                                     Integer price,String book_description,Integer book_owner_id,
                                     MultipartFile bookCover,HttpServletRequest request
                                     ) throws IOException {
        if (name == null){
            return HigherResponse.getResponseFaild("请先上传书籍文件");
        }
        if (book_name == null || book_name == ""){
            return HigherResponse.getResponseFaild("请输入正确的书名！");
        }
        if (book_type_id == null){
            return HigherResponse.getResponseFaild("获取图书分类失败！！");
        }
        if (book_author == null || book_author == ""){
            book_author = "未知";
        }
        if (book_pub == null || book_pub == ""){
            book_pub = "未知";
        }
        if (book_description == null || book_description == ""){
            book_description = "没有添加描述";
        }
        if (price == null){
            price =0;
        }
        if (book_owner_id == null){
            return HigherResponse.getResponseFaild("获取上传者id失败！");
        }
        if (bookCover == null){
            return  HigherResponse.getResponseFaild("上传封面出错！");
        }
        Book book = new Book();
        book.setBook_name(book_name);
        book.setBook_type_id(book_type_id);
        book.setBook_author(book_author);
        book.setBook_pub(book_pub);
        book.setShelf_time(new Date());
        book.setPrice(price);
        book.setBook_description(book_description);
        book.setBook_owner_id(book_owner_id);
        //获取封面文件
        String pictureName = bookCover.getOriginalFilename();
        String path = request.getServletContext().getRealPath("/");
        path += "\\img\\books\\";
        path += pictureName;
        bookCover.transferTo(new File(path));
        String book_picture = "img/books/"+pictureName;
        book.setBook_picture(book_picture);
        //获取书本文件路径
        String ebook_url = "/library/" + name;
        book.setEbook_url(ebook_url);
        return bookService.uploadBook(book);
    }

    @RequestMapping("/listByOwner")
    public HigherResponse<Object> pageQueryBookByOwner(@RequestParam(required = true)Integer owner,
                                                      @RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                                      @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQyeryBookByOwner(owner,pageNum,pageSize);
    }

    @RequestMapping("/download")
    public HigherResponse downloadBook(HttpServletRequest request, HttpServletResponse response,Integer bookId) throws IOException {
        //获取文件的绝对路径名称
        HigherResponse<Book> higherResponse = bookService.findBookById(bookId);
        Book book = higherResponse.getData();
        String ebook_url = book.getEbook_url();
        String fileName = ebook_url.split("/")[1];
        //将文件名转码
        fileName = URLEncoder.encode(fileName,"UTF-8");
        String path = request.getServletContext().getRealPath("/");
        path += ebook_url;
        System.out.println(path);
        //得到要下载的文件
        File file = new File(path);
        if (!file.exists()) {
            return HigherResponse.getResponseFaild("没有找到您需要的文件");
        }
        //设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path);
        // 创建输出流
        OutputStream out = response.getOutputStream();
        // 创建缓冲区
        byte buffer[] = new byte[1024]; // 缓冲区的大小设置是个迷  我也没搞明白
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len = in.read(buffer)) > 0){
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        // 关闭输出流
        out.close();
        return HigherResponse.getResponseSuccess("文件开始下载");
    }

    @RequestMapping("/listAdmin")
    public HigherResponse<Object> listAll(@RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                          @RequestParam(required = true,defaultValue = "10")Integer pageSize){
        return bookService.pageQueryBookAdmin(pageNum,pageSize);
    }

    @RequestMapping("/changeStatus")
    public HigherResponse changeStatus(Integer id,boolean flag){
        return bookService.changeStatus(id,flag);
    }

    @RequestMapping("/delete")
    public HigherResponse delete(Integer id){
        return bookService.delete(id);
    }
}
