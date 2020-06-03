package cn.edu.llhc.zhs.dao;

import cn.edu.llhc.zhs.domain.Type;

import java.util.List;

public interface TypeMapper {

    List<Type> findAllType();

    Boolean addType(Type type);

    String selectNameForId(Integer id);

    List<String> selectChildType(Integer num);

    Boolean deleteType(Integer id);

    List<Object> hottest();
}
