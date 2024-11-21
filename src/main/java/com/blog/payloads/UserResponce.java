package com.blog.payloads;

import java.util.List;

public class UserResponce {

	private List<UserDto> content;
	private int pageNo;
	private int pageSize;
	private int totalEle;
	private int totalpages;
	private boolean lastpage;
	public UserResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserResponce(List<UserDto> content, int pageNo, int pageSize, int totalEle, int totalpages,
			boolean lastpage) {
		super();
		this.content = content;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalEle = totalEle;
		this.totalpages = totalpages;
		this.lastpage = lastpage;
	}
	public List<UserDto> getContent() {
		return content;
	}
	public void setContent(List<UserDto> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalEle() {
		return totalEle;
	}
	public void setTotalEle(int totalEle) {
		this.totalEle = totalEle;
	}
	public int getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(long l) {
		this.totalpages = (int) l;
	}
	public boolean isLastpage() {
		return lastpage;
	}
	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}

	
	
}
