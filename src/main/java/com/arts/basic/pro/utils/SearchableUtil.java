package com.arts.basic.pro.utils;

import com.arts.basic.pro.common.Searchable;
import com.arts.basic.pro.module.annotation.SearchProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class SearchableUtil {

	public static <T> Searchable getSearchableParam(T param) {
		Searchable searchable = new Searchable();
		Map<String, Object> params = new TreeMap<String, Object>();
		try {
			Field[] fields = param.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.getName().equals("serialVersionUID") || field.getName().equals("operatorUuid")) {
					continue;
				}
				if (!ObjectUtils.isEmpty(field.get(param))) {
					SearchProperty search = field.getAnnotation(SearchProperty.class);
					if (search != null) {
						if (search.id()) {
							params.put(search.searchType() + search.key(),
									UuidUtil.getId(field.get(param).toString(), search.className()).toString());
						} else {
							String value = field.get(param).toString();
							if (!ObjectUtils.isEmpty(search.key())) {
								if (!value.equals("[]")) {
									params.put(search.searchType() + search.key(), value);
								}
							} else {
								if (!value.equals("[]")) {
									params.put(search.searchType() + field.getName(), value);
								}
							}
						}

					}
				}
			}
			searchable.setParams(params);
			return searchable;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return searchable;
	}
}
