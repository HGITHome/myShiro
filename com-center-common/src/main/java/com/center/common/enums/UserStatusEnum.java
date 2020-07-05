package com.center.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserStatusEnum
 * @Description
 * @Author yutang
 * @Date 2020/6/26 16:00
 * @Version V1.0
 **/
public enum UserStatusEnum {

    /**
     * 启用
     */
    ENABLE(1,"启用"),

    /**
     * 禁用
     */
    DISENABLE(2,"禁用");

    /**
     * value值
     */
    private Integer value;

    /**
     * 说明
     */
    private String desc;

    UserStatusEnum(Integer value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static final Map<Integer,UserStatusEnum> MAP = new HashMap<Integer, UserStatusEnum>();

    /**
     * 初始化Map集合
     */
    static {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()){
            MAP.put(userStatusEnum.getValue(),userStatusEnum);
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Map<Integer, UserStatusEnum> getMAP() {
        return MAP;
    }
}
