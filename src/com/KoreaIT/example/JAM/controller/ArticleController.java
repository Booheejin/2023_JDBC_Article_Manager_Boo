package com.KoreaIT.example.JAM.controller;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.example.JAM.dto.Article;
import com.KoreaIT.example.JAM.service.ArticleService;
import com.KoreaIT.example.JAM.session.Session;
import com.KoreaIT.example.JAM.util.Util;

public class ArticleController {
	private ArticleService articleService;
	private Scanner sc;
	
	public ArticleController(Scanner sc, Connection conn) {
		this.articleService = new ArticleService(conn);
		this.sc = sc;
		
	}

	public void showWrite() {
		
		if (Session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		System.out.println("== 게시물 작성 ==");
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		// write에서 member.name을 입력하는 방법으로 풀이
		int id = articleService.InWrite(title,body,Session.loginedMemberId,Session.loginMemberName);	
//		int id = DBUtil.insert(conn, sql);
			
		System.out.printf("%d번 글이 생성되었습니다\n", id);
		
	}

	public void showList(String cmd) {
		
		String searchKeyword = cmd.substring("article list".length()).trim();
		
		System.out.println("== 게시물 목록 ==");
		
		List<Article> articles = articleService.getArticles(searchKeyword);
//		List<Map<String, Object>> articleListMap = articleService.ExList();
//		List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);
		
		if (articles.size() == 0) {
			System.out.println("게시물이 없습니다");
			return;
		}
		
		if (searchKeyword.length() >0) {
			System.out.println("검색어 : " + searchKeyword);
		}
		
		
		System.out.println("번호	|	제목	|	작성자명	|	조회수");  
		
		for (Article article : articles) {
			System.out.printf("%d	|	%s	|	 %s		|	%d\n", article.id, article.title,article.memberName,article.count);
		}
		
	}
	

	
	public void showDetail(String cmd) {
		
		int id = Integer.parseInt(cmd.split(" ")[2]);
		
		articleService.increaseCount(id); // 조회수 늘리는 함수
	
		Article article = articleService.getArticle(id);
//		Map<String,Object> articleMap = articleService.Exdetail(id);
//		Map<String,Object> articleMap = DBUtil.selectRow(conn, sql);
		
		if(article == null) {
			System.out.printf("%d번 게시글은 존재하지 않습니다\n",id);
			return;
		}
		
		
		System.out.printf("== %d 게시물 상세보기 ==\n", id);
		
		System.out.printf("번호 : %d\n",article.id);
		System.out.printf("작성날짜 : %s\n",Util.DatetimeFormat(article.regDate));
		System.out.printf("수정날짜 : %s\n",Util.DatetimeFormat(article.updateDate));
		System.out.printf("조회수 : %d\n",article.count);
		System.out.printf("작성자 : %s\n",article.memberName);
		System.out.printf("제목 : %s\n",article.title);
		System.out.printf("내용 : %s\n",article.body);
		
		
	}

	public void showDelete(String cmd) {
		
		if (Session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmd.split(" ")[2]);

		
//		int articlesCount = articleService.getArticleCount(id);
//		int articlesCount = DBUtil.selectRowIntValue(conn, sql);
		
		Article article = articleService.getArticle(id);

		if(article == null) {
			System.out.printf("%d번 글은 존재하지 않습니다\n", id);
			return;
		}
		
		if (Session.loginedMemberId != article.memberId ){
			System.out.println("삭제할수 없는 정보입니다.");
			return;
		}

		System.out.println("== 게시물 삭제 ==");
		
		
		articleService.Deletelog(id);
		
		System.out.printf("%d번 글이 삭제되었습니다\n", id);
		
	}

	public void showModify(String cmd) {
		
		if (Session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmd.split(" ")[2]);

//		int articlesCount = articleService.getArticleCount(id);
//		int articlesCount = DBUtil.selectRowIntValue(conn, sql);
		
		Article article = articleService.getArticle(id);

		if(article == null) {
			System.out.printf("%d번 글은 존재하지 않습니다\n", id);
			return;
		}
		
		if (Session.loginedMemberId != article.memberId){
			System.out.println("수정할수 없는 정보입니다.");
			return;
		}

		System.out.println("== 게시물 수정 ==");

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();

		articleService.Modifylog(title,body,id);
		
		System.out.printf("%d번 글이 수정되었습니다\n", id);
		
		
	}
}
