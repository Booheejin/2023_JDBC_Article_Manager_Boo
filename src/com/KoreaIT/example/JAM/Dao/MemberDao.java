package com.KoreaIT.example.JAM.Dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.example.JAM.util.DBUtil;
import com.KoreaIT.example.JAM.util.SecSql;

public class MemberDao {
	
	private Connection conn;
	
	public MemberDao(Connection conn) {
		this.conn =conn;
	}
	
	public boolean isLoginIdDup(String loginId) {
		
		SecSql sql = new SecSql();
		
		sql.append("SELECT COUNT(*) > 0");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		return DBUtil.selectRowBooleanValue(conn, sql);
	}

	public int Idjoin(String loginId, String loginPw, String name) {
		
		SecSql sql = new SecSql();
		
		sql.append("INSERT INTO member");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?",loginId);
		sql.append(", loginPw = ?",loginPw);
		sql.append(", name = ?", name);
		
		return DBUtil.insert(conn, sql);
	}

	public Map<String, Object> getMemeberByLoginId(String loginId) {
		SecSql sql = new SecSql();

		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);
		
		return DBUtil.selectRow(conn, sql);
	}

}
