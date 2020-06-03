package cn.edu.llhc.zhs.domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer mesid;
    private Integer stu_id;
    private String mescontent;
    private Integer reply;
    private Integer book_id;
    private Date time;
    public Message() {
    }

    public Message(Integer mesid, Integer stu_id, String mescontent, Integer reply, Integer book_id) {
        this.mesid = mesid;
        this.stu_id = stu_id;
        this.mescontent = mescontent;
        this.reply = reply;
        this.book_id = book_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getMesid() {
        return mesid;
    }

    public void setMesid(Integer mesid) {
        this.mesid = mesid;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public String getMescontent() {
        return mescontent;
    }

    public void setMescontent(String mescontent) {
        this.mescontent = mescontent;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mesid=" + mesid +
                ", stu_id=" + stu_id +
                ", mescontent='" + mescontent + '\'' +
                ", reply=" + reply +
                ", book_id=" + book_id +
                '}';
    }
}
