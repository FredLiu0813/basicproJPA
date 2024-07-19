package com.arts.basic.pro.utils.enums;

/**
 * @author
 * @date 2022/8/18
 * @apiNote
 */
public enum SysActionTypeEnum {

    CREATE("新建", 1), SETSTATUS("修改状态", 2),
    OPEN("启用", 3), DEL("禁用", 4),
    UPDATE("修改", 5), LOGIN("登录", 6),
    SETRECOMMAND("修改推荐", 7), RECOMMAND("推荐", 8), NORECOMMAND("取消推荐", 9),
    UPGRADETAG("升级系统标签", 10);

    private String value;
    private int code;

    SysActionTypeEnum(String value, int code) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
