package com.edu.reading.common.result.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * rest接口返回分页类
 * 
 * @param <T> data type
 * @author zht
 */
@Getter
@Setter
public class RestPageResult<T> implements Serializable {

	private static final long serialVersionUID = -8048577763828650575L;

	private int code;
	private String message;
	private PageEntity data;
//	private long totalCount;
//	private int pageSize;
//	private int pageNo;
//	private List<T> data;
	
    public RestPageResult() {
    }

    public RestPageResult(int code, String message, List<T> list, int pageNo, int pageSize, long totalCount) {
        this.code = code;
        this.setMessage(message);
        this.data = new PageEntity();
        this.data.setPageNo(pageNo);
        this.data.setPageSize(pageSize);
        this.data.setTotalCount(totalCount);
        this.data.setData(list);
//        this.data = data;
//        this.pageNo = pageNo;
//        this.pageSize = pageSize;
//        this.totalCount = totalCount;
    }
    
    @Getter
    @Setter
    class PageEntity {
    	private long totalCount;
    	private int pageSize;
    	private int pageNo;
    	private List<T> data;
    	public PageEntity() {}
		public long getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(long totalCount) {
			this.totalCount = totalCount;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		public List<T> getData() {
			return data;
		}
		public void setData(List<T> data) {
			this.data = data;
		}
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PageEntity getData() {
		return data;
	}

	public void setData(PageEntity data) {
		this.data = data;
	}
}
