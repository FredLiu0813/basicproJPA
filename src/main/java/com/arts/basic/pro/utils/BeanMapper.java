package com.arts.basic.pro.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = null;

	private BeanMapper() {
		dozer = new DozerBeanMapper();
	}

	private static class SingletonBeanMapper {
		private static final BeanMapper INSTANCE = new BeanMapper();
	}

	public static BeanMapper getBeanMapper() {
		return SingletonBeanMapper.INSTANCE;
	}

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public <T> T map(Object source, Class<T> destinationClass) {
		if(source == null) {
			return null;
		}
		return dozer.map(source, destinationClass);
	}

	/**
	 * 拷贝model
	 * @param dto param
	 * @param model target
	 * @param flg true忽略null，false为拷贝null
	 */
	public void copyToModel(final Object dto, final Object model, boolean flg) {
		BeanUtil.copyProperties(dto, model, CopyOptions.create().setIgnoreNullValue(flg).setIgnoreError(true));
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		sourceList.forEach(sourceObject -> {
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		});
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}
}
