package server;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Request {
	BufferedReader reader=null;
	PrintWriter pw=null;
	Socket socket=null;
	BookDAO dao = new BookDAO();
	public Request(){}
	
	public Request(BufferedReader reader,PrintWriter pw,Socket socket){
		super();
		this.reader = reader;
		this.pw = pw;
		this.socket = socket;
	}
	
	// ���� ��� ����
	public ArrayList<BookDTO> bookSearchAllInput() {
		ArrayList<BookDTO> list = new ArrayList<>();
		list = dao.selectBookAll(list);
		return list;
	} // bookSearchAllInput()
	
	// ���� ���� �˻�
	public ArrayList<BookDTO> bookSearchTitleInput(String title) {
		ArrayList<BookDTO> list = new ArrayList<>();
		dao.selectBookTitle(list, title);
		return list;
	} // bookSearchTitleInput()
	
	// ���� ���� 
	public void bookInsertInput(int num){
			try {
				// get title
				String title = reader.readLine();
				// get company
				String company = reader.readLine();
				// get name
				String name = reader.readLine();
				// get cost
				int cost = Integer.parseInt(reader.readLine());
				// make dto
				BookDTO dto = new BookDTO(num,title,company,name,cost);
				// insert
				int succ = (dao.insertBook(dto));
				// print success to client
				pw.println(succ);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("bookInsertInput() Exception");
			}
	} // bookInsertInput
	
	//���� ����
	public void bookDeleteInput(int num) {
		try {
			// delete
			int succ = (dao.deleteBook(num));
			// print success to client
			pw.println(succ);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("bookDeleteInput() Exception");
		}
	} // bookDeleteInput
	
	//���� ����
	public void bookUpdateInput(int num) {
		try {
			// get title
			String title = reader.readLine();
			// get company
			String company = reader.readLine();
			// get name
			String name = reader.readLine();
			// get cost
			int cost = Integer.parseInt(reader.readLine());
			// make dto
			BookDTO dto = new BookDTO(num,title,company,name,cost);
			// update
			int succ = (dao.updateBook(dto));
			// print success to client
			pw.println(succ);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("bookUpdateInput() Exception");
		}
	}
	// bookUpdateInput
	
} // class