package com.KoreaIT.example.JAM.controller;


import java.sql.Connection;
import java.util.Scanner;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.service.MemberService;

public class MemberController {
	
	private MemberService memberService;
	private Scanner sc;
	
	public MemberController(Connection conn, Scanner sc) {
		this.memberService = new MemberService(conn);
		this.sc = sc;
	}

	public void showJoin() {
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
			System.out.printf("이름 ");
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

	public void showlogin() {
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
			
			boolean isLoginIdDup = memberService.isLoginIdDup(loginId);
			
			if(isLoginIdDup == false) {
				System.out.printf("%s는 존재하지 않는 아이디 입니다.\n",loginId);
				continue;
			}
			Member member = memberService.getMemeberByLoginId(loginId);
			
			if(member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			
			System.out.printf("%s번 회원님 환영합니다!!\n",loginId);
			
			break;
			
		}
		
	}

}
