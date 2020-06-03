package cn.edu.llhc.zhs.dao;

import cn.edu.llhc.zhs.domain.Message;

import java.util.List;

public interface MessageMapper {

    //根据图书id找评论
    List<Message> findMessageByBookId(Integer bookId);

    List<Message> pageQueryMessageByMessageId(Integer messageId);

    boolean reply(Message message);

    boolean replyBooks(Message message);

    boolean delete(Integer id);

    List<Message> findMessageByStuId(Integer stuId);
}
