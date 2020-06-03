package cn.edu.llhc.zhs.controller;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Student;
import cn.edu.llhc.zhs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/manage/student")
public class StudentCon {

    @Autowired
    private StudentService studentService;

    //用户分页
    @ResponseBody
    @RequestMapping("/list")
    public HigherResponse<Object> pageCon(@RequestParam(required = true,defaultValue = "1")Integer pageNum, @RequestParam(required = true,defaultValue = "20")Integer pageSize){
        return studentService.pageQueryStudent(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/login")
    public HigherResponse login(String name, String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        return studentService.login(name,password,session);
    }

    @ResponseBody
    @RequestMapping("/register")
    public HigherResponse<Boolean> addStudent(@RequestParam(required = true)String stu_name,
                                              @RequestParam(required = true)String stu_pwd,
                                              @RequestParam(required = true)String stu_email){
        Student student = new Student();
        student.setStu_name(stu_name);
        student.setStu_pwd(stu_pwd);
        student.setStu_email(stu_email);
        student.setPhoto("img/photo/default.png");
        return studentService.addStudent(student);
    }

    @RequestMapping("/getUser")
    public HigherResponse<Student> getUser(HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        if (user == null){
            return HigherResponse.getResponseFaild("未登录");
        }else {
            return HigherResponse.getResponseSuccess(user);
        }
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().setAttribute("user",null);
        try {
            response.sendRedirect("../../index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getStudent")
    public HigherResponse<Student> findStudentById(@RequestParam(required = true)Integer stu_id){
        return studentService.findStudentById(stu_id);
    }

    @RequestMapping("/updatePhoto")
    public HigherResponse updatePhoto(@RequestParam(required = true)Integer stu_id,
                                      @RequestParam(required = true)MultipartFile myPhotofile,
                                      HttpServletRequest request){
        if (null == stu_id){
            return HigherResponse.getResponseFaild("id获取失败");
        }if (null == myPhotofile){
            return HigherResponse.getResponseFaild("文件获取失败");
        }
        String name = myPhotofile.getOriginalFilename();
        try {
            String path = request.getServletContext().getRealPath("/");
            path += "\\img\\photo\\";
            path += name;
            System.out.println(path);
            myPhotofile.transferTo(new File(path));
            String photo = "img/photo/"+name;
            System.out.println("进入controller，photo为"+photo);
            HigherResponse higherResponse = studentService.updatePhoto(photo, stu_id);
            return higherResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return HigherResponse.getResponseFaild("失败");
        }
    }

    @RequestMapping("/updateName")
    public HigherResponse updateName(String stu_name,Integer stu_id){
        return studentService.updateName(stu_name,stu_id);
    }

    @RequestMapping("/updateEmail")
    public HigherResponse updateEmail(String stu_email,Integer stu_id){
        return studentService.updateEmail(stu_email,stu_id);
    }

    @RequestMapping("/updatePassword")
    public HigherResponse updatePassword(String oldPassword,String stu_pwd,Integer stu_id){
        return studentService.updatePassword(oldPassword,stu_pwd,stu_id);
    }

    @RequestMapping("/updateMoney")
    public HigherResponse updateMoney(Integer stu_money,Integer stu_id){
        return studentService.updateMoney(stu_money,stu_id);
    }

    @RequestMapping("/search")
    public HigherResponse<Object> pageQuerySearch(@RequestParam(required = true)String name,
                                                  @RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                                  @RequestParam(required = true,defaultValue = "10")Integer pageSize) {
        return studentService.pageQuerySearch(name, pageNum, pageSize);
    }

    @RequestMapping("/updateLevel")
    public HigherResponse updateLevel(Integer stu_level,Integer stu_id){
        return studentService.updateLevel(stu_level, stu_id);
    }
}
