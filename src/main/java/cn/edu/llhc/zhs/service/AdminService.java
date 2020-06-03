package cn.edu.llhc.zhs.service;

import cn.edu.llhc.zhs.common.HigherResponse;

import javax.servlet.http.HttpSession;

public interface AdminService {

    HigherResponse login(String name, String password, HttpSession session);

    HigherResponse<Object> pageQueryAdmin(Integer pageNum, Integer pageSize);

    HigherResponse insert(String name, String password);

    HigherResponse delete(Integer id);

}
