package cn.edu.llhc.zhs.dao;

import cn.edu.llhc.zhs.domain.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    List<Admin> findAllAdmin();
    /**
     * 查询管理员是否存在
     * @param name 管理员名
     * @return
     */
    int isAdminExist(String name);

    Admin selectAdminByNameAndPwd(@Param("name") String name, @Param("password") String password);

    boolean insert(@Param("name") String name, @Param("password") String password);

    boolean delete(Integer id);


}




