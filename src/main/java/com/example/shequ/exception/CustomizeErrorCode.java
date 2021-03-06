package com.example.shequ.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"您找的问题不在了,换一个试试吧~"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复。"),
    NO_LOGIN(2003,"请先登录~"),
    SYS_ERROR(2004,"服务器冒烟了，请稍等一会~"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在。"),
    COMMENT_NOT_FOUND(2006,"您回复的评论不在了,换一个试试吧~"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
