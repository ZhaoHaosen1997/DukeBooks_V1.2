package cn.edu.llhc.zhs.controller;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/message")
public class MessageCon {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/byBookId")
    public HigherResponse<Object> pageQueryMessageByBookId(@RequestParam(required = true)Integer bookId,
                                                           @RequestParam(required = true,defaultValue = "1")Integer pageNum,
                                                           @RequestParam(required = true,defaultValue = "20")Integer pageSize){
        return messageService.pageQueryMessageByBookId(bookId,pageNum,pageSize);
    }

    @RequestMapping("/reply")
    public HigherResponse reply(Integer stuId, String mescontent, Integer reply){
        return messageService.reply(stuId, mescontent, reply);
    }

    @RequestMapping("/replyBook")
    public HigherResponse replyBook(Integer stuId, String mescontent, Integer bookId){
        return messageService.replyBooks(stuId, mescontent, bookId);
    }

    @RequestMapping("/delete")
    public HigherResponse delete(Integer id){
        return messageService.delete(id);
    }

    @RequestMapping("/showMe")
    public HigherResponse<Object> selectByStuId(Integer stuId){
        return messageService.selectByStuId(stuId);
    }
}
