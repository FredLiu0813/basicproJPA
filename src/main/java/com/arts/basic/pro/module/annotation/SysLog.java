package com.arts.basic.pro.module.annotation;

import com.arts.basic.pro.utils.enums.SysActionTypeEnum;
import com.arts.basic.pro.utils.enums.SysModuleTypeEnum;

import java.lang.annotation.*;

/**
 * @author
 * @date 2024/4/18
 * @apiNote
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface SysLog {

    String tableName() default "";

    SysActionTypeEnum actionType() default SysActionTypeEnum.CREATE;

    SysModuleTypeEnum moduleType() default SysModuleTypeEnum.LOGIN;

    boolean flg() default false;
}
