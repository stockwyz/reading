package com.edu.reading.common.result;

/**
 * @author zht
 */
public enum ResultCodeEnum implements IResultCode {

    /**
     * common code
     **/
    SUCCESS(200, "处理成功"),
    EMPTY(201, "无数据"),
    XXXX(600, "自定义错误"),
    ERROR(500, "服务器内部错误"),
    ;

    private int code;

    private String msg;

    ResultCodeEnum(int code, String codeMsg) {
        this.code = code;
        this.msg = codeMsg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getCodeMsg() {
        return msg;
    }
}
