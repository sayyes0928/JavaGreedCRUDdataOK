package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDAO {
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;

	Scanner sc = new Scanner(System.in);
	public UserDTO userDTO = new UserDTO();

	static final String JD = "com.mysql.jdbc.Driver";
	static final String DBURL = "jdbc:mysql://localhost/mydb";
	static final String dbID = "root";
	static final String dbPW = "root";

	public UserDAO() { // SQL 연결, 생성자이기 때문에 객체 생성시 호출
		try {
			Class.forName(JD);
			System.out.println("연결성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		}
	}

	public void connect() { // DB와 연결하는 메소드
		try {
			conn = DriverManager.getConnection(DBURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() { // 연결된 DB를 닫아주는 메소드
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

	}

	public void insert(String name, String number, String age, String phone) {
		connect();
		try {
			String sqlJoin;
			sqlJoin = "insert into userDB values(?,?,?,?)";
			pstmt = conn.prepareStatement(sqlJoin);
			pstmt.setString(1, name);
			pstmt.setString(2, number);
			pstmt.setString(3, age);
			pstmt.setString(4, phone);
			pstmt.executeUpdate(); // database 에 valuse 를 update! //

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disconnect();
		}
	}

	public void delete(String number) {
		connect();
		try {
			String sqlDel;
			sqlDel = "delete from userDB where number ='" + number + "'";
			pstmt = conn.prepareStatement(sqlDel);
			pstmt.executeUpdate(); // database 에 valuse 를 update! //
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	void update(String name, String number, String age, String phone) {
		connect();
		try {
//			String sql2;
////			sql2 = "update user set " + list[input.updateNum] + " = '" + input.change + "' where user_id= '"
////					+ userDTO.getId() + "'";
//			pstmt = conn.prepareStatement(sql2);
//			pstmt.executeUpdate(); // database 에 valuse 를 update! //

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정실패. 입력하신 내용을 확인해 주세요");
		} finally {
			disconnect();
		}
	}

	public void search(String number) { // 로그인 메소드
		connect();
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select * from userDB where number = '" + number + "'"; // 내가 입력해준id 테이블값의 전체의
			ResultSet rs = stmt.executeQuery(sql); // 값을저장해준 모든정보를 결과 객체 rs에 담음
			if (rs.next()) {
				// 로그인 된 정보를 객체에 담아 SET 수정 파트에서 사용
				userDTO.setName(rs.getString(1));
				userDTO.setNumber(rs.getString(2));
				userDTO.setAge(rs.getString(3));
				userDTO.setPhone(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("호출실패");
		} finally {
			disconnect();
		}
	}
}
