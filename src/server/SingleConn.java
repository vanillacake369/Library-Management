package server;
import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConn {	
	// SingleTon Class : 특정 역할 하나만 수행하는 클래스
	// 연결 객체 정의
	private static Connection conn;
	
	// 초기화 블럭(static 블럭) : 가장 먼저 메모리에 로딩 → 실행
	static {
		String url = "jdbc:mariadb://127.0.0.1:3306/tblbook";
		String user = "jihoon";
		String password = "1026";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn==null)
				System.out.println("DB 접속 실패");
		} catch (Exception e) {
			System.out.println("DB Connection Error");
			e.printStackTrace();
		}
	}

	//getConn() 정의
	public static Connection getConn() {
		return conn;
	}
} // class