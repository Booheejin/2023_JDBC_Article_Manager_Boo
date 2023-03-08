package com.KoreaIT.example.JAM;

public class Article {
	public int id;
	String regDate;
	String updateDate;
	public String title;
	public String body;
	
	public Article(int id,String title,String body){
		this.id = id;
		this.title = title;
		this.body = body;
	}
	public Article(int id,String regDate,String updateDate,String title,String body){
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	// 객체에 내용물을 보기위한 소스!
	
}
