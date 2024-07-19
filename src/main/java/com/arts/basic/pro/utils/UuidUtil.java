package com.arts.basic.pro.utils;

import cn.hutool.core.codec.Hashids;
import io.micrometer.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UuidUtil {

	public static Long getId(String uuid, Class<?> classDTO) {
		return getId(uuid, classDTO.getSimpleName());
	}

	public static Long getId(String uuid, String className) {
		if (StringUtils.isNotBlank(uuid)) {
			if (Constant.DEV) {
				return Long.decode(uuid);
			}
			Hashids hashids = new Hashids(String.format(Constant.PRIVATE_KEY, className).toCharArray(), null, Constant.MIN_HASH_LENGTH);
			long[] numbers = hashids.decode(uuid);
			if (numbers.length == 0) {
				return null;
			}
			return numbers[0];
		}
		return null;
	}

	public static List<Long> getIds(List<String> uuids, Class<?> classDTO) {
		return getIds(uuids, classDTO.getSimpleName());
	}

	public static List<Long> getIds(List<String> uuids, String className) {
		List<Long> ids = new ArrayList<>();
		for (String uuid : uuids) {
			Long id = getId(uuid, className);
			if (null != id) {
				ids.add(id);
			}
		}
		return ids;
	}

	public static String getUuid(Long id, Class<?> classDTO) {
		return getUuid(id, classDTO.getSimpleName());
	}

	public static String getUuid(Long id, String className) {
		if (id != null) {
			if (Constant.DEV) {
				return id.toString();
			}
			Hashids hashids = new Hashids(String.format(Constant.PRIVATE_KEY, className).toCharArray(), null, Constant.MIN_HASH_LENGTH);
			return hashids.encode(id);
		}
		return null;
	}

	public static List<String> getUuids(Long[] ids, Class<?> classDTO) {
		return getUuids(ids, classDTO.getSimpleName());
	}

	private static List<String> getUuids(Long[] ids, String className) {
		if (ids != null && ids.length > 0) {
			if (Constant.DEV) {
				List<String> uuids = new ArrayList<>();
				for (Long id : ids) {
					uuids.add(id.toString());
				}
				return uuids;
			}

			List<String> uuids = new ArrayList<>();
			for (Long id : ids) {
				Hashids hashids = new Hashids(String.format(Constant.PRIVATE_KEY, className).toCharArray(), null, Constant.MIN_HASH_LENGTH);
				uuids.add(hashids.encode(id));
			}
			return uuids;
		}
		return null;
	}

}
