package cn.edu.llhc.zhs.service;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Book;

public interface BookService {

    HigherResponse<Object> pageQueryBook(Integer pageNum, Integer pageSize);

    HigherResponse<Object> pageQueryBookDesc(Integer pageNum, Integer pageSize);

    HigherResponse<Object> pageQyeryBookByTypr(Integer typeId,Integer pageNum, Integer pageSize);

    HigherResponse<Object> pageQuerySearch(String noa,Integer pageNum, Integer pageSize);

    HigherResponse<Book> findBookById(Integer id);

    HigherResponse<Boolean> uploadBook(Book book);

    HigherResponse<Object> pageQyeryBookByOwner(Integer owner,Integer pageNum, Integer pageSize);

    HigherResponse<Object> pageQueryBookAdmin(Integer pageNum, Integer pageSize);

    HigherResponse changeStatus(Integer id,boolean flag);

    HigherResponse delete(Integer id);


}
