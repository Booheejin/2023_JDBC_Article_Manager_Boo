package com.KoreaIT.example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.KoreaIT.example.JAM.controller.ArticleController;
import com.KoreaIT.example.JAM.controller.MemberController;

public class App {
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("== 프로그램 시작 ==");
		
		Connection conn = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://127.0.0.1:3306/jdbc_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			
			conn = DriverManager.getConnection(url, "root", "");
			
			ArticleController articleController = new ArticleController(sc,conn); 
			MemberController memberController = new MemberController(conn,sc);
			
			while (true) {
				
				System.out.printf("명령어) ");
				String cmd = sc.nextLine().trim();
				
				if(cmd.startsWith("member join")) {
					memberController.doJoin();
				}
				else if(cmd.startsWith("member login")) {
					memberController.dologin();
				}
				else if(cmd.startsWith("member logout")) {
					memberController.dologout();
				}
				else if(cmd.startsWith("member porfile")) {
					memberController.showPorfile();
				}
				else if (cmd.equals("article write")) {
					articleController.showWrite();
				} 
				else if (cmd.startsWith("article list")) {
					articleController.showList(cmd);
				}
				else if (cmd.startsWith("article detail ")) {
					articleController.showDetail(cmd);
				}
				else if (cmd.startsWith("article delete ")) {
					articleController.showDelete(cmd);
				}
				else if (cmd.startsWith("article modify ")) {
					articleController.showModify(cmd);
				}
				else {
					System.out.println("존재하지 않는 명령어 입니다.");

				}
				
				if (cmd.equals("exit")) {
					System.out.println("== 프로그램 종료 ==");
					break;
				}
				
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
	}
}