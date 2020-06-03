package cn.edu.llhc.zhs.controller;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Admin;
import cn.edu.llhc.zhs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/manage/admin")
public class AdminCon {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public HigherResponse login(String name, String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        return adminService.login(name,password,session);
    }

    @RequestMapping("/test")
    public String test(){
        return "success";
    }

    //管理员分页
    @RequestMapping("/list")
    public HigherResponse<Object> pageCon(@RequestParam(required = true,defaultValue = "1")Integer pageNum, @RequestParam(required = true,defaultValue = "20")Integer pageSize){
        return adminService.pageQueryAdmin(pageNum,pageSize);
    }

    @RequestMapping("/getUser")
    public HigherResponse<Admin> getUser(HttpServletRequest request){
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
            response.sendRedirect("../../adminLogin.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/insert")
    public HigherResponse insert(String name,String password){
        return adminService.insert(name, password);
    }

    @RequestMapping("/delete")
    public HigherResponse delete(Integer id){
        if (id == 1){
            return HigherResponse.getResponseFaild("无法删除超级管理员账户");
        }else {
            return adminService.delete(id);
        }
    }

}
