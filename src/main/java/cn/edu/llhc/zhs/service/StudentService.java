package cn.edu.llhc.zhs.service;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Student;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;

public interface StudentService {

    HigherResponse<Object> pageQueryStudent(Integer pageNum, Integer pageSize);

    HigherResponse login(String name, String password, HttpSession session);

    HigherResponse<Boolean> addStudent(Student student);

    HigherResponse<Student> findStudentById(Integer id);

    HigherResponse updatePhoto(String photo,Integer stu_id);

    HigherResponse updateName(String stu_name,Integer stu_id);

    HigherResponse updateEmail(String stu_email,Integer stu_id);

    HigherResponse updatePassword(String oldPassword,String stu_pwd,Integer stu_id);

    HigherResponse<Integer> updateMoney(Integer stu_money,Integer stu_id);

    HigherResponse updateLevel(Integer stu_level,Integer stu_id);

    HigherResponse<Object> pageQuerySearch(String name,Integer pageNum, Integer pageSize);

}
