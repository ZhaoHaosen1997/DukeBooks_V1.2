package cn.edu.llhc.zhs.dao;

import cn.edu.llhc.zhs.domain.Book;

import java.util.List;

public interface BookMapper {

    List<Book> findAllBook();

    List<Book> findAllBookDesc();

    List<Book> findBookByType(Integer typeId);

    List<Book> findBookByNameOrAuthor(String noa);

    Book findBookById(Integer id);

    boolean uploadBook(Book book);

    List<Book> findBookByOwner(Integer id);

    List<Book> findAllBookAdmin();

    //将图书上架
    boolean changeToUp(Integer id);

    //将图书下架
    boolean changeToDown(Integer id);

    //读者删除自己上传的图书
    boolean delete(Integer id);
}
