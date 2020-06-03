package cn.edu.llhc.zhs.service;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Type;

public interface TypeService {

    HigherResponse<Object> pageQueryType(Integer pageNum, Integer pageSize);

    HigherResponse<Object> addType(Type type);

    HigherResponse<String> selectNameForId(Integer id);

    HigherResponse<Object> selectChildType(Integer num);

    HigherResponse<Object> deleteType(Integer id);

    HigherResponse<Object> hottest();

}
