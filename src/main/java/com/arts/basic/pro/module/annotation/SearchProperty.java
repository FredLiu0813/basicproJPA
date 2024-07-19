package com.arts.basic.pro.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 查询组织参数
 * lwf
 *
 */

@Retention(RetentionPolicy.RUNTIME)
//@Target((ElementType.FIELD))
public @interface SearchProperty {

	/**
	 * 查询类型
	 * 
	 * @return
	 */
	String searchType() default "";

	/**
	 * 用于特殊的key
	 * @return
	 */
	String key() default "";

	/**
	 * 是否是主键
	 * @return
	 */
	boolean id() default false;

	/**
	 * 加密的key
	 * @return
	 */
	String className() default "";

}
