package com.study.weblog.common.enums;

import com.study.weblog.common.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResponseCodeEnum
 * @Description 异常枚举类
 * @Author zhang
 * @Date 2023/11/4
 * @Version 1.0
 **/
//@Data //'@Data' is only supported on a class type
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements BaseExceptionInterface {
    SYSTEM_ERROR("10000", "系统出错喽！工程师正在抢修中！"),
    PRODUCT_NOT_FOUND("20000", "产品不存在"),
    PARAM_NOT_VALID("10001","参数错误");

    //错误吗
    private String errorCode;
    //错误信息
    private String errorMessage;
//    private ResponseCodeEnum(String errorCode,String errorMessage){
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//    }

//    public String getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(String errorCode) {
//        this.errorCode = errorCode;
//    }
//
//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
//    public static String getErrorMesage(String errorCode){
//        for(ResponseCodeEnum  responseCodeEnum: ResponseCodeEnum.values()){
//            if(responseCodeEnum.errorCode.equalsIgnoreCase(errorCode)){
//                return responseCodeEnum.errorMessage;
//            }
//        }
//        return null;
//    }

}
