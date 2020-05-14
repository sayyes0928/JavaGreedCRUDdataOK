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

	public UserDAO() { // SQL ����, �������̱� ������ ��ü ������ ȣ��
		try {
			Class.forName(JD);
			System.out.println("���Ἲ��");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����̹� �ε� ����");
		}
	}

	public void connect() { // DB�� �����ϴ� �޼ҵ�
		try {
			conn = DriverManager.getConnection(DBURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() { // ����� DB�� �ݾ��ִ� �޼ҵ�
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
			pstmt.executeUpdate(); // database �� valuse �� update! //

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
			pstmt.executeUpdate(); // database �� valuse �� update! //
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
//			pstmt.executeUpdate(); // database �� valuse �� update! //

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��������. �Է��Ͻ� ������ Ȯ���� �ּ���");
		} finally {
			disconnect();
		}
	}

	public void search(String number) { // �α��� �޼ҵ�
		connect();
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select * from userDB where number = '" + number + "'"; // ���� �Է�����id ���̺��� ��ü��
			ResultSet rs = stmt.executeQuery(sql); // ������������ ��������� ��� ��ü rs�� ����
			if (rs.next()) {
				// �α��� �� ������ ��ü�� ��� SET ���� ��Ʈ���� ���
				userDTO.setName(rs.getString(1));
				userDTO.setNumber(rs.getString(2));
				userDTO.setAge(rs.getString(3));
				userDTO.setPhone(rs.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȣ�����");
		} finally {
			disconnect();
		}
	}
}
