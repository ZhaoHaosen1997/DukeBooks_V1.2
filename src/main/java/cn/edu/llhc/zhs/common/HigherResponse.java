package cn.edu.llhc.zhs.common;

import cn.edu.llhc.zhs.utils.StatusUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 封装高复用的响应对象
 * @param <T>
 */
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class HigherResponse<T> {
    //泛型

    private HigherResponse(Integer status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    private HigherResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private HigherResponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    private HigherResponse(Integer status) {
        this.status = status;
    }

    private HigherResponse() {
    }

    private Integer status;//是否成功，1成功，0失败
    private T data;//如果成功，传进来的数据
    private String msg;//弹出的消息

    /**
     * 判断是否成功
     * @return
     */
    @JsonIgnore
    public boolean isResponseSuccess(){
        return this.status == StatusUtil.SUCCESSSTATUS;
    }

    /**
     * 成功的返回方法
     * @return
     */
    public static HigherResponse getResponseSuccess(){
        return new HigherResponse(StatusUtil.SUCCESSSTATUS);
    }

    public static HigherResponse getResponseSuccess(String msg){
        return new HigherResponse(StatusUtil.SUCCESSSTATUS,msg);
    }

    public static <T>HigherResponse getResponseSuccess(T data){
        return new HigherResponse(StatusUtil.SUCCESSSTATUS,data);
    }

    public static <T>HigherResponse getResponseSuccess(T data,String msg){
        return new HigherResponse(StatusUtil.SUCCESSSTATUS,data,msg);
    }


    /**
     * 判断是否失败
     * @return
     */
    @JsonIgnore
    public boolean isResponseFailed() {

        return this.status == StatusUtil.FAILEDSTATUS;
    }
    /**
     * 失败的返回方法
     * @return
     */
    public static HigherResponse getResponseFailed(){
        return new HigherResponse(StatusUtil.FAILEDSTATUS);
    }

    public static HigherResponse getResponseFaild(String msg){
        return new HigherResponse(StatusUtil.FAILEDSTATUS,msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
