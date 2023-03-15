package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Article;
import com.KoreaIT.example.JAM.Dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int InWrite(String title, String body) {
		
		return articleDao.InWrite(title,body);
	}

	public List<Article> ExList() {
		
		List<Map<String, Object>> articleListMap = articleDao.ExList();
				
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public Article ExDetail(int id) {
		
		Map<String,Object> articleMap = articleDao.ExDetail(id);
		
		Article article = new Article(articleMap);
		
		return article;
	}

	public void Deletelog(int id) {
		
		articleDao.Deletelog(id);
		
	}

	public int getArticleCount(int id) {
		
		return articleDao.getArticleCount(id);
	}

	public void Modifylog(String title, String body, int id) {
		articleDao.Modifylog(title,body,id);
		
	}

	

}
