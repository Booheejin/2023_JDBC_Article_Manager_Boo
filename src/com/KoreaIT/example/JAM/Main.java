package com.KoreaIT.example.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in); //우선 처음으로 만들어 준다.
		
		List<Article> articles = new ArrayList<>();
		
		int lastArticlesId = 0;
		
		while(true) {
			
			System.out.printf("명령어) ");
		    String cmd = sc.nextLine().trim();
		    if(cmd.equals("exit")) {
		    	
		    	break;
		    }
		    else if(cmd.equals("article write")) {
				int id = lastArticlesId + 1;
				lastArticlesId++;
				
				System.out.println("== 게시물 작성 ==");
				
		    	System.out.printf("제목: ");
			    String title = sc.nextLine().trim();
			    System.out.printf("내용: ");
			    String body = sc.nextLine().trim();
			    
			    Article article = new Article(id,title,body);
			    
			    System.out.println(article);
			    
			    articles.add(article);
			    System.out.println(articles);
			    
			    System.out.printf("%d번글이 생성되었습니다.\n",id);
			  
			}
		    else if (cmd.equals("article list")) {
		    	if(articles.size() == 0) {
		    		System.out.println("게시물이 없습니다.");
		    	}
		    	
		    	System.out.println("번호	|	제목");
		    	for(int i = 0; i < articles.size(); i++ ) {
		    		Article article = articles.get(i);
		    		
		    		System.out.printf("%d	|	%s\n", article.id,article.title);
		    	}
		    }
		    else {
		    	System.out.println("존재하지 않는 명령어 입니다.");
		    }
		}
		
		System.out.println("== 프로그램 종료 ==");
	}
}

