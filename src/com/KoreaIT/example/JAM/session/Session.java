package com.KoreaIT.example.JAM.session;

import com.KoreaIT.example.JAM.dto.Member;

public class Session {
	public static int loginedMemberId;
	public static Member loginedMember;
	public static String memberName;
	
	static {
		loginedMemberId = -1;
		
	}
	public static void login(Member member) {
		loginedMemberId= member.id;
		loginedMember = member;
		
	}
	public static void logout() {
		loginedMemberId = -1;
		loginedMember = null;
		
	}
	public static boolean isLogined() {
		return loginedMemberId != -1;
	}
	public static void memberName(Member member) {
		memberName = member.name;
	}
	
	public static Object isMemberName() {
		
		return memberName;
	}
}
