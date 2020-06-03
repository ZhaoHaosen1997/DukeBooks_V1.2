package cn.edu.llhc.zhs.service;

import cn.edu.llhc.zhs.common.HigherResponse;

public interface MessageService {

    HigherResponse<Object> pageQueryMessageByBookId(Integer bookId,Integer pageNum, Integer pageSize);

    HigherResponse<Object> pageQueryMessageByMessageId(Integer MessageId,Integer pageNum, Integer pageSize);

    HigherResponse reply(Integer stuId,String mescontent,Integer reply);

    HigherResponse replyBooks(Integer stuId,String mescontent,Integer bookId);

    HigherResponse delete(Integer id);

    HigherResponse<Object> selectByStuId(Integer stuId);
}
