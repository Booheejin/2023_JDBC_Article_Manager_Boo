package com.KoreaIT.example.JAM.controller;


import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.dto.Member;
import com.KoreaIT.example.JAM.service.MemberService;
import com.KoreaIT.example.JAM.session.Session;
import com.KoreaIT.example.JAM.util.Util;

public class MemberController {
	
	private MemberService memberService;
	private Scanner sc;
	
	public MemberController(Connection conn, Scanner sc) {
		this.memberService = new MemberService(conn);
		this.sc = sc;
	}

	public void doJoin() {
		

		if (Session.isLogined()){
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		String loginPw = null;
		String loginPwChk = null;
		String name = null;
		
		System.out.println("== 회원 가입 ==");
		
		
		while(true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			
			if(loginId.length() == 0) {
				System.out.println("아이디를 입력해 주세요");
				continue;
			}
			
			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);
//			boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
			
			if(isLoginIdDup) {
				System.out.printf("%s는 사용중인 아이디 입니다.\n",loginId);
				continue;
			}
			
			System.out.printf("%s는 사용가능한 아이디 입니다.\n",loginId);
			break;
		}
		while(true) {
			
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요.");
				continue;
			}
			
			boolean loginPwCheck =true;
			
			while(true) {
				System.out.printf("비밀번호 확인 : ");
				loginPwChk = sc.nextLine().trim();
				
				if(loginPwChk.length() == 0) {
					System.out.println("비밀번호를 입력해 주세요.");
					continue;
				}
				
				if(loginPw.equals(loginPwChk) == false) {
					System.out.println("비밀번호가 일치하지 않습니다.");
					loginPwCheck = false;
				}
				break;
			}
			if (loginPwCheck) {
				break;
			}
		}
		while(true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if(name.length() == 0) {
				System.out.println("이름을 입력해 주세요");
				continue;
			}
			break;
		}
		
		int id = memberService.Idjoin(loginId , loginPw, name);
//		int id = DBUtil.insert(conn, sql);
		
		System.out.printf("%d번 회원님 가입성공!!\n",id);
		
	}

	public void dologin() {
		
		if (Session.isLogined()){
			System.out.println("로그아웃 후 이용해주세요");
			return;
		}
		
		String loginId = null;
		String loginPw = null;
		System.out.println("== 로그인 ==");
		
		while(true) {
			
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine().trim();
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			
			if(loginId.length() == 0) {
				System.out.println("아이디를 입력해 주세요");
				continue;
			}
			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요");
				continue;
			}
			
			
			Member member = memberService.getMemeberByLoginId(loginId);
			
			if(member == null) {
				System.out.printf("%s는 존재하지 않는 아이디 입니다.\n",loginId);
				continue;
			}
			
			if(member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			Session.login(member);
			System.out.printf("%s 회원님 환영합니다!!\n",member.name);
			
			break;
			
		}
		
	}

	public void dologout() {
		
		if (Session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		Session.logout();
		System.out.println("로그아웃 되었습니다.");
	}

	public void showPorfile() {
		
		if (Session.isLogined() == false){
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		
		System.out.println("== 마이 페이지 ==");
		System.out.printf("로그인 아이디: %s\n",Session.loginedMember.loginId);
		System.out.printf("가입일자: %s\n",Util.DatetimeFormat(Session.loginedMember.regDate));
		System.out.printf("이름: %s\n",Session.loginedMember.name);
		
	}

}
