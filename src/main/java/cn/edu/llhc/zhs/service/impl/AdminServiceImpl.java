package cn.edu.llhc.zhs.service.impl;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.dao.AdminMapper;
import cn.edu.llhc.zhs.domain.Admin;
import cn.edu.llhc.zhs.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public HigherResponse login(String name, String password, HttpSession session) {
        if (name == null || name.length() == 0){
            return HigherResponse.getResponseFaild("用户名不能为空！！");
        }
        if (password == null || password.length() == 0){
            return HigherResponse.getResponseFaild("密码不能为空！！");
        }
        int i = adminMapper.isAdminExist(name);
        if (i == 0){
            return HigherResponse.getResponseFaild("用户名不存在！！");
        }
        Admin admin = adminMapper.selectAdminByNameAndPwd(name,password);
        if (admin == null){
            return HigherResponse.getResponseFaild("用户名或密码错误！！");
        }
        session.setAttribute("user",admin);
        return HigherResponse.getResponseSuccess(admin,"登录成功！");
    }

    @Override
    public HigherResponse<Object> pageQueryAdmin(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Admin> students = adminMapper.findAllAdmin();
        PageInfo<Admin> pageInfo = new PageInfo<>(students);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse insert(String name, String password) {
        if (null == name || "" == name){
            return HigherResponse.getResponseFaild("请输入管理员名");
        }
        if (null == password || "" == password){
            return HigherResponse.getResponseFaild("请输入用户名");
        }
        boolean flag = adminMapper.insert(name, password);
        if (flag){
            return HigherResponse.getResponseSuccess("添加管理员账户成功！");
        }else {
            return HigherResponse.getResponseFaild("添加失败");
        }
    }

    @Override
    public HigherResponse delete(Integer id) {
        if (id == null || id == 0){
            return HigherResponse.getResponseFaild("服务器错误");
        }
        boolean flag = adminMapper.delete(id);
        if (flag){
            return HigherResponse.getResponseSuccess("删除成功！");
        }else {
            return HigherResponse.getResponseFaild("服务器错误，删除失败");
        }
    }
}
