package com.arts.basic.pro.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页数据
 *
 * @author liuwf
 */
@Data
public class PageData implements Serializable {

	@Serial
	private static final long serialVersionUID = 8718971634262304569L;

	public static PageData convert(final Page<?> page) {
		final PageData pageData = new PageData();
		pageData.setFirst(page.isFirst());
		pageData.setLast(page.isLast());
		pageData.setNumber(page.getNumber());
		pageData.setTotalPages(page.getTotalPages());
		pageData.setNumberOfElements(page.getNumberOfElements());
		pageData.setSize(page.getSize());
		pageData.setTotalElements(page.getTotalElements());
		return pageData;
	}

	/**
	 * 总条数
	 */
	@ApiModelProperty(value = "总条数", position = 1)
	private long totalElements;

	/**
	 * 查询结果条数
	 */
	@ApiModelProperty(value = "返回条数", position = 2)
	private int numberOfElements;

	/**
	 * 总页数
	 */
	@ApiModelProperty(value = "总页数", position = 3)
	private int totalPages;

	/**
	 * 当前页码（从0开始）
	 */
	@ApiModelProperty(value = "当前页码（从0开始）", position = 4)
	private int number;

	/**
	 * 是否第一页
	 */
	@ApiModelProperty(value = "是否第一页", position = 5)
	private boolean first;

	/**
	 * 是否最后页
	 */
	//    @ApiModelProperty(value = "是否最后页", position = 6)
	private boolean last;

	/**
	 * 页大小
	 */
	@ApiModelProperty(value = "页SIZE", position = 7)
	private int size;

	@ApiModelProperty(value = "本页开始行（相对于总记录数，从1开始计数）", position = 8)
	public long getFromNumber() {
		if (this.numberOfElements == 0) {
			return ((long) this.number * this.size);
		} else {
			return ((long) this.number * this.size) + 1;
		}
	}

	@ApiModelProperty(value = "本页结束行", position = 9)
	public long getToNumber() {
		return ((long) this.number * this.size) + this.numberOfElements;
	}
}
