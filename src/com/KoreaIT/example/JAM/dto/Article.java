package com.KoreaIT.example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Article {
	public int id;
	public LocalDateTime regDate;
	public LocalDateTime updateDate;
	public String title;
	public String body;
	public int memberId;
	public String memberName;
	public int count;
	
	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.memberId = (int) articleMap.get("memberId");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberName = (String) articleMap.get("memberName");
		this.count = (int) articleMap.get("count");
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ",updateDate=" + updateDate + ",title=" + title + ", body=" + body + "]";
	}
	// 객체에 내용물을 보기위한 소스!
	
}
