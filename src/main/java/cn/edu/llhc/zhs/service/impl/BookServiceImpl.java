package cn.edu.llhc.zhs.service.impl;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.dao.BookMapper;
import cn.edu.llhc.zhs.domain.Book;
import cn.edu.llhc.zhs.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public HigherResponse<Object> pageQueryBook(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Book> students = bookMapper.findAllBook();
        PageInfo<Book> pageInfo = new PageInfo<>(students);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> pageQueryBookDesc(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Book> students = bookMapper.findAllBookDesc();
        PageInfo<Book> pageInfo = new PageInfo<>(students);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> pageQyeryBookByTypr(Integer typeId, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Book> books = bookMapper.findBookByType(typeId);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> pageQuerySearch(String noa, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        if (null == noa || "" == noa || noa.length() == 0){
            return HigherResponse.getResponseFaild("输入为空");
        }
        List<Book> books = bookMapper.findBookByNameOrAuthor(noa);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("没有找到数据");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Book> findBookById(Integer id) {
        if (null == id){
            return HigherResponse.getResponseFaild("服务器错误！");
        }else {
            Book book = bookMapper.findBookById(id);
            if (null == book){
                return HigherResponse.getResponseFaild("没有找到数据");
            }else {
                return HigherResponse.getResponseSuccess(book);
            }
        }
    }

    @Override
    public HigherResponse<Boolean> uploadBook(Book book) {
        if (null == book){
            return HigherResponse.getResponseFaild("服务器异常");
        }
        boolean flag = bookMapper.uploadBook(book);
        if (flag){
            return HigherResponse.getResponseSuccess(true,"上传成功！！");
        }else {
            return HigherResponse.getResponseFaild("上传失败");
        }
    }

    @Override
    public HigherResponse<Object> pageQyeryBookByOwner(Integer owner, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Book> books = bookMapper.findBookByOwner(owner);
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> pageQueryBookAdmin(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Book> students = bookMapper.findAllBookAdmin();
        PageInfo<Book> pageInfo = new PageInfo<>(students);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse changeStatus(Integer id, boolean flag) {
        if (id == null){
            return HigherResponse.getResponseFaild("服务器错误");
        }
        if (flag){
            boolean b = bookMapper.changeToUp(id);
            if (b){
                return HigherResponse.getResponseSuccess("上架成功！");
            }else {
                return HigherResponse.getResponseFaild("读取失败！");
            }
        }else {
            boolean b = bookMapper.changeToDown(id);
            if (b){
                return HigherResponse.getResponseSuccess("下架成功！");
            }else {
                return HigherResponse.getResponseFaild("读取失败！");
            }
        }
    }

    @Override
    public HigherResponse delete(Integer id) {
        if (id == null || id == 0){
            return HigherResponse.getResponseFaild("服务器错误");
        }
        boolean flag = bookMapper.delete(id);
        if (flag){
            return HigherResponse.getResponseSuccess("删除成功！");
        }else {
            return HigherResponse.getResponseFaild("删除失败");
        }
    }

}
