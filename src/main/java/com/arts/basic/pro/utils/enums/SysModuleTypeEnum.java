package com.arts.basic.pro.utils.enums;

/**
 * @author
 * @date 2022/8/18
 * @apiNote
 */
public enum SysModuleTypeEnum {

    LOGIN("登录", 1), BANNER("banner", 2), TOPIC("圆桌话题", 3), KNOWLEDGE("知识文档", 4),
    TOPICDICUSS("圆桌话题评论", 5), KNOWLEDGEDICUSS("知识文档评论", 6), KMBP("KMBP用户", 7),
    KNOWLEDGE_WORK_CAT("工作内容", 8), TAG("标签", 9), KNOWLEDGE_FILE_CAT("知识文档分类", 10),
    TOPIC_CAT("圆桌话题分类", 11), KNOWLEDGE_ARCHIVE("知识文档归档目录", 12),
    KNOWLEDGE_LIFECYCLE("知识文档生命周期阶段", 13), KNOWLEDGE_INDUSTRY_CATEGORY("知识文档行业分类", 14),
    ROLE("角色管理", 15), ADMIN("管理员管理", 16);

    private String value;
    private int code;

    SysModuleTypeEnum(String value, int code) {
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
