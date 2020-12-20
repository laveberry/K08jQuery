package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleDAO {
	
	Connection con; 
	PreparedStatement psmt;
	ResultSet rs;
	
	//기본생성자에서 DBCP(커넥션풀)을 통해 DB연결
	public OracleDAO() {
		try {
			Context ctx = new InitialContext();
			//변경부분
			DataSource source = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			con = source.getConnection();
			
			System.out.println("DBCP연결성공");
		}
		catch (Exception e) {
			System.out.println("DBCP연결실패");
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			//연결을 해제하는것이 아니고 풀에 다시 반납한다.
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(con!=null) con.close();
		}
		catch (Exception e) {
			System.out.println("자원반납시 예외발생");
			e.printStackTrace();
		}
	}
	
	
	//그룹함수 count()를 통해 회원의 존재유무만 판단한다.
	public boolean isMember(String id, String pass) {
		
		String sql = "SELECT COUNT(*) FROM member "
				+ " WHERE id=? AND pass=?";
		int isMember = 0;
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			rs.next();
			isMember = rs.getInt(1);
			System.out.println("affected:"+isMember);
			
			//회원이 아닌경우
			if(isMember==0) return false;
		}
		catch (Exception e) {
			//예외가 발생한다면 확인이 불가능하므로 무조건 false를 반환한다.
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
