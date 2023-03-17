package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Dao.ArticleDao;
import com.KoreaIT.example.JAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int InWrite(String title, String body, int loginedMemberId,String memberName) {
		
		return articleDao.InWrite(title,body, loginedMemberId,memberName);
	}

	public List<Article> getArticles(String searchKeyword) {
		
		List<Map<String, Object>> articleListMap = articleDao.getArticles(searchKeyword);
				
		List<Article> articles = new ArrayList<>();
		
		for(Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public Article getArticle(int id) {
		
		Map<String,Object> articleMap = articleDao.getArticle(id);
		
		if (articleMap.isEmpty()) {
			return null;
		}
		return new Article(articleMap);
	}

	public int getArticleCount(int id) {
		
		return articleDao.getArticleCount(id);
	}
	
	public void Deletelog(int id) {
		
		articleDao.Deletelog(id);
		
	}


	public void Modifylog(String title, String body, int id) {
		articleDao.Modifylog(title,body,id);
		
	}

	public void increaseCount(int id) {
		articleDao.increaseCount(id);
		
	}



}
