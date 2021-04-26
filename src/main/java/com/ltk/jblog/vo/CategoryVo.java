package com.ltk.jblog.vo;

import java.util.Date;

public class CategoryVo {
	private int cateNo;
	private int userNo;
	private String cateName;
	private String description;
	private int postSize;
	private Date regDate;

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getPostSize() {
		return postSize;
	}

	public void setPostSize(int postSize) {
		this.postSize = postSize;
	}

	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", userNo=" + userNo + ", cateName=" + cateName + ", description="
				+ description + ", postSize=" + postSize + ", regDate=" + regDate + "]";
	}
	
	
}
