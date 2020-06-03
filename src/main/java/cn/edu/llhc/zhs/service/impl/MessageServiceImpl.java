package cn.edu.llhc.zhs.service.impl;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.dao.MessageMapper;
import cn.edu.llhc.zhs.domain.Message;
import cn.edu.llhc.zhs.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public HigherResponse<Object> pageQueryMessageByBookId(Integer bookId,Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Message> messages = messageMapper.findMessageByBookId(bookId);
        PageInfo<Message> pageInfo = new PageInfo<>(messages);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> pageQueryMessageByMessageId(Integer MessageId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public HigherResponse reply(Integer stuId, String mescontent, Integer reply) {
        if (stuId == null){
            return HigherResponse.getResponseFaild("用户id传入失败");
        }
        if (mescontent == null || mescontent == ""){
            return HigherResponse.getResponseFaild("用户id传入失败");
        }
        if (reply == null){
            return HigherResponse.getResponseFaild("传入被回复者id失败");
        }
        Date time = new Date();
        Message message = new Message();
        message.setStu_id(stuId);
        message.setMescontent(mescontent);
        message.setTime(time);
        message.setReply(reply);
        boolean flag = messageMapper.reply(message);
        if (flag){
            return HigherResponse.getResponseSuccess("回复成功！");
        }else {
            return HigherResponse.getResponseFaild("未知错误");
        }
    }

    @Override
    public HigherResponse replyBooks(Integer stuId, String mescontent, Integer bookId) {
        if (stuId == null){
            return HigherResponse.getResponseFaild("用户id传入失败");
        }
        if (mescontent == null || mescontent == ""){
            return HigherResponse.getResponseFaild("用户id传入失败");
        }
        if (bookId == null){
            return HigherResponse.getResponseFaild("传入书籍id失败");
        }
        Date time = new Date();
        Message message = new Message();
        message.setStu_id(stuId);
        message.setMescontent(mescontent);
        message.setTime(time);
        message.setBook_id(bookId);
        boolean flag = messageMapper.replyBooks(message);
        if (flag){
            return HigherResponse.getResponseSuccess("回复成功！");
        }else {
            return HigherResponse.getResponseFaild("未知错误");
        }
    }

    @Override
    public HigherResponse delete(Integer id) {
        if (id == null || id == 0){
            return HigherResponse.getResponseFaild("服务器错误");
        }
        boolean flag = messageMapper.delete(id);
        if (flag){
            return HigherResponse.getResponseSuccess("删除成功");
        }else {
            return HigherResponse.getResponseFaild("删除失败");
        }
    }

    @Override
    public HigherResponse<Object> selectByStuId(Integer stuId) {
        if (stuId == null){
            return HigherResponse.getResponseFaild("服务器错误");
        }
        List<Message> messages = messageMapper.findMessageByStuId(stuId);
        if (messages == null){
            return HigherResponse.getResponseFaild("没有找到评论数据");
        }else {
            return HigherResponse.getResponseSuccess(messages);
        }
    }
}
