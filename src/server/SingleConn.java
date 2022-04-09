package server;
import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConn {	
	// SingleTon Class : Ư�� ���� �ϳ��� �����ϴ� Ŭ����
	// ���� ��ü ����
	private static Connection conn;
	
	// �ʱ�ȭ ��(static ��) : ���� ���� �޸𸮿� �ε� �� ����
	static {
		String url = "jdbc:mariadb://127.0.0.1:3306/tblbook";
		String user = "jihoon";
		String password = "1026";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if (conn==null)
				System.out.println("DB ���� ����");
		} catch (Exception e) {
			System.out.println("DB Connection Error");
			e.printStackTrace();
		}
	}

	//getConn() ����
	public static Connection getConn() {
		return conn;
	}
} // class