package server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//DAO : Data Access Object
public class BookDAO {
	private Connection conn = SingleConn.getConn();
	private PreparedStatement ps;
	private ResultSet rs;
	
	// 도서 번호 조회
	protected ResultSet checkNum(int num) {
		String sql = "SELECT * FROM tblBook WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("checkNum() Exception!!!");
		}
		return rs;
	} // checkNum()
	
	// 모든 도서 ArrayList에 추가
	protected ArrayList<BookDTO> selectBookAll(ArrayList<BookDTO> list) {
		String sql = "SELECT * FROM tblBook ORDER BY num ASC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String company = rs.getString("company");
				String name = rs.getString("name");
				int cost = rs.getInt("cost");
				
				BookDTO dto = new BookDTO(num, title, company, name, cost);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectBookAll() Exception!!!");
		}
		return list;
	} // selectBookAll()
	
	// 제목 검색 메서드
	protected void selectBookTitle(ArrayList<BookDTO> list, String searchTitle) {
		String sql = "SELECT * FROM tblBook WHERE UPPER(title) LIKE UPPER(?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + searchTitle + "%");
			rs = ps.executeQuery();
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String company = rs.getString("company");
				String name = rs.getString("name");
				int cost = rs.getInt("cost");
				
				BookDTO dto = new BookDTO(num, title, company, name, cost);
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("selectBookTitle() Exception!!!");
		}
	} // selectBookTitle()
	
	// 도서 정보 등록
	protected int insertBook(BookDTO dto) {
		int succ = 0;
		String sql = "INSERT INTO tblBook VALUES(?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getNum());
			ps.setString(2, dto.getTitle());
			ps.setString(3, dto.getCompany());
			ps.setString(4, dto.getName());
			ps.setInt(5, dto.getCost());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insertBook() Exception!!!");
		}
		return succ;
	} // insertBook()
	
	// 도서 정보 삭제 메서드
	protected int deleteBook(int num) {
		int succ = 0;
		String sql = "DELETE FROM tblBook WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			succ = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("deleteBook() Exception!!!");
		}
		return succ;
		
	} // deleteBook()
	
	// 도서 정보 수정 메서드
	protected int updateBook(BookDTO dto) {
		int succ = 0;
		String sql = "UPDATE tblBook SET title = ?, company = ?, ";
		sql += "name = ?, cost = ? WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getCompany());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getCost());
			ps.setInt(5, dto.getNum());
			succ = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("updateBook() Exception!!!");
		}
		return succ;
	} // updateBook()
	
	// DB Close
	protected void dbClose() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			System.out.println("DB 접속 종료 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dbClose() Exception!!!");
		}
	}
} // class