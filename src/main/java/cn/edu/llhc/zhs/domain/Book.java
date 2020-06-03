package cn.edu.llhc.zhs.domain;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    private Integer book_id;
    private String book_name;
    private Integer book_type_id;
    private String book_author;
    private String book_pub;
    private Date shelf_time;
    private Integer price;
    private String book_description;
    private Integer book_owner_id;
    private String book_picture;
    private String ebook_url;
    private Integer is_delete;

    public Book() {
    }

    public Book(Integer book_id, String book_name, Integer book_type_id, String book_author, String book_pub, Date shelf_time, Integer price, String book_description, Integer book_owner_id, String book_picture, String ebook_url, Integer is_delete) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_type_id = book_type_id;
        this.book_author = book_author;
        this.book_pub = book_pub;
        this.shelf_time = shelf_time;
        this.price = price;
        this.book_description = book_description;
        this.book_owner_id = book_owner_id;
        this.book_picture = book_picture;
        this.ebook_url = ebook_url;
        this.is_delete = is_delete;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Integer getBook_type_id() {
        return book_type_id;
    }

    public void setBook_type_id(Integer book_type_id) {
        this.book_type_id = book_type_id;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_pub() {
        return book_pub;
    }

    public void setBook_pub(String book_pub) {
        this.book_pub = book_pub;
    }

    public Date getShelf_time() {
        return shelf_time;
    }

    public void setShelf_time(Date shelf_time) {
        this.shelf_time = shelf_time;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    public Integer getBook_owner_id() {
        return book_owner_id;
    }

    public void setBook_owner_id(Integer book_owner_id) {
        this.book_owner_id = book_owner_id;
    }

    public String getBook_picture() {
        return book_picture;
    }

    public void setBook_picture(String book_picture) {
        this.book_picture = book_picture;
    }

    public String getEbook_url() {
        return ebook_url;
    }

    public void setEbook_url(String ebook_url) {
        this.ebook_url = ebook_url;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", book_type_id=" + book_type_id +
                ", book_author='" + book_author + '\'' +
                ", book_pub='" + book_pub + '\'' +
                ", shelf_time=" + shelf_time +
                ", price=" + price +
                ", book_description='" + book_description + '\'' +
                ", book_owner_id=" + book_owner_id +
                ", book_picture='" + book_picture + '\'' +
                ", ebook_url='" + ebook_url + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }
}
