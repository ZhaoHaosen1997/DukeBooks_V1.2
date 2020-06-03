package cn.edu.llhc.zhs.controller;

import cn.edu.llhc.zhs.common.HigherResponse;
import cn.edu.llhc.zhs.domain.Type;
import cn.edu.llhc.zhs.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage/type")
public class TypeCon {

    @Autowired
    private TypeService typeService;

    //类型分页
    @ResponseBody
    @RequestMapping("/list")
    public HigherResponse<Object> pageCon(@RequestParam(required = true,defaultValue = "1")Integer pageNum, @RequestParam(required = true,defaultValue = "20")Integer pageSize){
        return typeService.pageQueryType(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/add")
    public HigherResponse<Object> addType(@RequestParam(required = true)String type_name,@RequestParam(required = true)Integer father_type){
        Type type = new Type(null,type_name,father_type,(byte)1);
        return typeService.addType(type);
    }
    @ResponseBody
    @RequestMapping("/get_name")
    public HigherResponse<String> selectNameForId(@RequestParam(required = true)Integer id){
        return typeService.selectNameForId(id);
    }

    @ResponseBody
    @RequestMapping("/selectChild")
    public HigherResponse<Object> selectChildType(@RequestParam(required = true)Integer num){
        return typeService.selectChildType(num);
    }

    @ResponseBody
    @RequestMapping("/deleteType")
    public HigherResponse<Object> deleteType(@RequestParam Integer id){
        return typeService.deleteType(id);
    }

    @RequestMapping("/hottest")
    public HigherResponse<Object> hottest(){
        return typeService.hottest();
    }
}
