package com.arts.basic.pro.module.annotation;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class RegisterSqlFunction extends MySQLDialect {

	public RegisterSqlFunction() {
		super();
//		registerFunction("GROUP_CONCAT", new StandardSQLFunction("GROUP_CONCAT"));
//		registerFunction("ANY_VALUE", new StandardSQLFunction("ANY_VALUE"));
//		registerFunction("DATE_FORMAT", new StandardSQLFunction("DATE_FORMAT"));
//		registerFunction("convert", new SQLFunctionTemplate(StandardBasicTypes.STRING, "CONVERT(?1 using GBK)"));
		registerKeyword("using");
		registerKeyword("USING");
		registerKeyword("GBK");
		registerKeyword("gbk");
	}
}
