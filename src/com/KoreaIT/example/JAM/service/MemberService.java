package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.example.JAM.Member;
import com.KoreaIT.example.JAM.Dao.MemberDao;

public class MemberService {
	
	private MemberDao memberDao;

	public MemberService(Connection conn){
		this.memberDao = new MemberDao(conn);
	}
	
	public boolean isLoginIdDup(String loginId) {
		
		return memberDao.isLoginIdDup(loginId);
	}

	public int Idjoin(String loginId, String loginPw, String name) {
		
		return memberDao.Idjoin(loginId,loginPw, name);
	}

	public Member getMemeberByLoginId(String loginId) {
		Map<String,Object> memberMap = memberDao.getMemeberByLoginId(loginId);
		
		Member member= new Member(memberMap);
		
		return member;
	}
	


}
