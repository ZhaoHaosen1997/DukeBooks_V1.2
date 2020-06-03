package cn.edu.llhc.zhs.service.impl;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.dao.TypeMapper;
import cn.edu.llhc.zhs.domain.Type;
import cn.edu.llhc.zhs.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public HigherResponse<Object> pageQueryType(Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //调用查询所有用户的方法
        List<Type> types = typeMapper.findAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        if (null == pageInfo){
            return HigherResponse.getResponseFaild("查询数据失败……");
        }else {
            return HigherResponse.getResponseSuccess(pageInfo);
        }
    }

    @Override
    public HigherResponse<Object> addType(Type type) {
        if (null == type){
            return HigherResponse.getResponseFaild("服务器开小差了，抱歉");
        }else if (null == type.getType_name()){
            return HigherResponse.getResponseFaild("请输入正确的数据");
        }else {
            Boolean flag = typeMapper.addType(type);
            if (false == flag){
                return HigherResponse.getResponseFaild("添加失败，请重试");
            }else {
                return HigherResponse.getResponseSuccess("添加成功");
            }
        }
    }

    @Override
    public HigherResponse<String> selectNameForId(Integer id) {
        if (null == id){
            return HigherResponse.getResponseFaild("服务器开小差了，抱歉");
        }else {
            String name = typeMapper.selectNameForId(id);
            if (null == name){
                return HigherResponse.getResponseFaild("服务器开小差了，抱歉");
            }
            return HigherResponse.getResponseSuccess(name);
        }
    }


    @Override
    public HigherResponse<Object> selectChildType(Integer num) {
        if (null == num){
            return HigherResponse.getResponseFaild("出错了");
        }else if (null == typeMapper.selectChildType(num)){
            return HigherResponse.getResponseFaild("出错了");
        }else {
            return HigherResponse.getResponseSuccess(typeMapper.selectChildType(num));
        }
    }

    @Override
    public HigherResponse<Object> deleteType(Integer id) {
        if (null == id){
            return HigherResponse.getResponseFaild("出错了");
        }else{
            Boolean flag = false;
            try{
                flag = typeMapper.deleteType(id);
            }catch (Exception e){
                return HigherResponse.getResponseFaild("该分类下还有图书，无法删除！");
            }
            if (flag){
                return HigherResponse.getResponseSuccess("删除成功！");
            }else {
                return HigherResponse.getResponseFaild("删除失败");
            }
        }
    }

    @Override
    public HigherResponse<Object> hottest() {
        List<Object> hottest = typeMapper.hottest();
        if (null == hottest){
            return HigherResponse.getResponseFaild("查询失败");
        }else {
            return HigherResponse.getResponseSuccess(hottest,"查询成功！");
        }
    }


}
