package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.KoreaIT.example.JAM.Dao.ArticleDao;

public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(Connection conn) {
		this.articleDao = new ArticleDao(conn);
	}

	public int InWrite(String title, String body) {
		
		return articleDao.InWrite(title,body);
	}

	public List<Map<String, Object>> ExList() {
		
		return articleDao.ExList();
	}

	public Map<String, Object> Exdetail(int id) {
		
		return articleDao.Exdetail(id);
	}

	public int ExDelete(int id) {
		
		return articleDao.ExDelete(id);
	}

	public void Deletelog(int id) {
		
		articleDao.Deletelog(id);
		
	}

	public int ExModify(int id) {
		
		return articleDao.ExModify(id);
	}

	public void Modifylog(String title, String body, int id) {
		articleDao.Modifylog(title,body,id);
		
	}

	

}
