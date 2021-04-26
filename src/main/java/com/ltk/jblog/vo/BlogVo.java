package com.ltk.jblog.vo;

public class BlogVo {
	private int userNo;
	private String blogTitle;
	private String logoFile;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	@Override
	public String toString() {
		return "BlogVo [userNo=" + userNo + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
}
