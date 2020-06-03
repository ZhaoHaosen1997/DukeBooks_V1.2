package cn.edu.llhc.zhs.dao;

import cn.edu.llhc.zhs.domain.Student;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface StudentMapper {

    List<Student> findAllStudent();

    Student selectStudentByNameAndPwd(@Param("name") String name, @Param("password") String password);

    int isStudentExist(String email);

    //添加学生
    boolean addStudent(Student student);

    Student findStudentById(Integer id);

    //修改头像
    boolean updatePhoto(@Param("photo")String photo,@Param("stu_id")Integer stu_id);

    //修改用户名
    boolean updateName(@Param("stu_name") String stu_name,@Param("stu_id") Integer stu_id);

    //修改邮箱
    boolean updateEmail(@Param("stu_email") String stu_email,@Param("stu_id") Integer stu_id);

    //修改密码
    boolean updatePassword(@Param("stu_pwd") String stu_pwd,@Param("stu_id") Integer stu_id);

    //修改积分
    boolean updateMoney(@Param("stu_money") Integer stu_money,@Param("stu_id") Integer stu_id);

    //修改等级
    boolean updateLevel(@Param("stu_level")Integer stu_level,@Param("stu_id")Integer stu_id);

    //通过用户名模糊查询用户
    List<Student> findStudentLikeName(String name);


}
