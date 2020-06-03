package cn.edu.llhc.zhs.service.impl;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.dao.StudentMapper;
import cn.edu.llhc.zhs.domain.Student;
import cn.edu.llhc.zhs.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public HigherResponse<Object> pageQueryStudent(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Student> students = studentMapper.findAllStudent();
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse login(String name, String password, HttpSession session) {

        if (name == null || name.length() == 0){
            return HigherResponse.getResponseFaild("用户名不能为空！！");
        }
        if (password == null || password.length() == 0){
            return HigherResponse.getResponseFaild("密码不能为空！！");
        }
        int i = studentMapper.isStudentExist(name);
        if (i == 0){
            return HigherResponse.getResponseFaild("用户名不存在！！");
        }
        Student student = studentMapper.selectStudentByNameAndPwd(name,password);
        if (student == null){
            return HigherResponse.getResponseFaild("用户名或密码错误！！");
        }
        if (student.getStu_level()==0){
            return HigherResponse.getResponseFaild("该用户已被封禁！！！");
        }
        session.setAttribute("user",student);
        return HigherResponse.getResponseSuccess(student,"登录成功！");
    }

    @Override
    public HigherResponse<Boolean> addStudent(Student student) {
        if (null == student){
            return HigherResponse.getResponseFaild("服务器错误！");
        }
        int i = studentMapper.isStudentExist(student.getStu_email());
        if (i != 0){
            return HigherResponse.getResponseFaild("邮箱已被注册！");
        }else {
            boolean b = studentMapper.addStudent(student);
            if (b){
                return HigherResponse.getResponseSuccess(true,"注册成功");
            }else {
                return HigherResponse.getResponseFaild("注册失败");
            }
        }
    }

    @Override
    public HigherResponse<Student> findStudentById(Integer id) {
        if (null == id){
            return HigherResponse.getResponseFaild("获取失败！");
        }else {
            Student student = studentMapper.findStudentById(id);
            if (null == student){
                return HigherResponse.getResponseFaild("没有查找到该学生");
            }else {
                return HigherResponse.getResponseSuccess(student,"获取成功");
            }
        }
    }

    @Override
    public HigherResponse updatePhoto(String photo, Integer stu_id) {
        if (null == photo || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            boolean flag = studentMapper.updatePhoto(photo,stu_id);
            if (flag){
                return HigherResponse.getResponseSuccess("头像修改成功！");
            }else {
                return HigherResponse.getResponseFaild("更新失败!");
            }
        }
    }

    @Override
    public HigherResponse updateName(String stu_name, Integer stu_id) {
        if (null == stu_name || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            boolean flag = studentMapper.updateName(stu_name,stu_id);
            if (flag){
                return HigherResponse.getResponseSuccess("用户名修改成功！");
            }else {
                return HigherResponse.getResponseFaild("更新失败!");
            }
        }
    }

    @Override
    public HigherResponse updateEmail(String stu_email, Integer stu_id) {
        if (null == stu_email || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            int i = studentMapper.isStudentExist(stu_email);
            if (i != 0){
                return HigherResponse.getResponseFaild("该邮箱已被注册");
            }else {
                boolean flag = studentMapper.updateEmail(stu_email,stu_id);
                if (flag){
                    return HigherResponse.getResponseSuccess("邮箱修改成功！");
                }else {
                    return HigherResponse.getResponseFaild("更新失败!");
                }
            }
        }
    }

    @Override
    public HigherResponse updatePassword(String oldPassword,String stu_pwd, Integer stu_id) {
        if (null == stu_pwd || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            Student student = studentMapper.findStudentById(stu_id);
            if (student.getStu_pwd().equals(oldPassword.trim())){

                boolean flag = studentMapper.updatePassword(stu_pwd,stu_id);
                if (flag){
                    return HigherResponse.getResponseSuccess("密码修改成功！请牢记您的密码");
                }else {
                    return HigherResponse.getResponseFaild("更新失败!");
                }
            }else {
                return HigherResponse.getResponseFaild("旧密码输入错误，请重新输入");
            }
        }
    }

    @Override
    public HigherResponse<Integer> updateMoney(Integer stu_money, Integer stu_id) {
        if (null == stu_money || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            boolean flag = studentMapper.updateMoney(stu_money,stu_id);
            if (flag){
                return HigherResponse.getResponseSuccess(stu_money,"修改成功");
            }else {
                return HigherResponse.getResponseFaild("支付失败!");
            }
        }
    }

    @Override
    public HigherResponse updateLevel(Integer stu_level, Integer stu_id) {
        if (null == stu_level || null == stu_id){
            return HigherResponse.getResponseFaild("服务器错误");
        }else {
            boolean flag = studentMapper.updateLevel(stu_level,stu_id);
            if (flag){
                return HigherResponse.getResponseSuccess("修改成功");
            }else {
                return HigherResponse.getResponseFaild("修改失败!");
            }
        }
    }

    @Override
    public HigherResponse<Object> pageQuerySearch(String name, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        if (null == name || "" == name || name.length() == 0){
            return HigherResponse.getResponseFaild("输入为空");
        }
        List<Student> books = studentMapper.findStudentLikeName(name);
        PageInfo<Student> pageInfo = new PageInfo<>(books);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("没有找到数据");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }
}
