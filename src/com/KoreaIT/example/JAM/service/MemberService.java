package com.KoreaIT.example.JAM.service;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.example.JAM.Dao.MemberDao;
import com.KoreaIT.example.JAM.dto.Member;

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
		
		if(memberMap.isEmpty()) {
			return null;
		}
		
		return new Member(memberMap);
	}
	


}
