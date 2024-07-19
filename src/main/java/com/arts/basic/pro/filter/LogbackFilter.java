package com.arts.basic.pro.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class LogbackFilter extends Filter<ILoggingEvent> {
	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (event.getLoggerName().contains("hibernate.loader.Loader")) {
			return FilterReply.DENY;
		}
		else {
			return FilterReply.ACCEPT;
		}
//		return FilterReply.ACCEPT;
	}
}
