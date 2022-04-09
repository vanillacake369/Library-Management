package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.BookDAO;
import server.BookDTO;

public class Menu extends Thread{
	private Socket socket;
	
	public Menu(){}
	
	public Menu(Socket socket){
		super();
		this.socket = socket;
	}
	
	public void printMenu(){
		System.out.println("============도서 조회 서비스============");
		System.out.println("1. 도서 목록 보기 : A");
		System.out.println("2. 도서 제목 검색 : S");
		System.out.println("3. 도서 정보 등록 : I");
		System.out.println("4. 도서 정보 삭제 : D");
		System.out.println("5. 도서 정보 수정 : U");
		System.out.println("6. 도서 관리 종료 : E");
		System.out.println("===================================");
		System.out.print("메뉴를 입력하세요> ");
	}
	
	@Override
	public void run() {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
				while(true) {
					// print menu
					printMenu();
					
					// select option
					Scanner scn = new Scanner(System.in);
					String option = scn.nextLine();
					
					// 도서 보기 : a
					if (option.equalsIgnoreCase("a")){
						// pass option to server
						pw.println(option);
						System.out.println("도서 목록 보기 화면입니다.");
						ArrayList<BookDTO> result = (ArrayList<BookDTO>)ois.readObject();
						for (BookDTO dto : result) {
							System.out.print(dto.getNum() + "\t");
							System.out.print(dto.getTitle() + "\t");
							System.out.print(dto.getCompany() + "\t");
							System.out.print(dto.getName() + "\t");
							System.out.print(dto.getCost() + "\n");
						}
						
					// 도서 검색 : a
					} else if(option.equalsIgnoreCase("s")) {
						// pass option to server
						pw.println(option);
						System.out.println("\n도서 제목 검색 화면입니다.");
						System.out.print("검색할 도서의 제목을 입력하세요> ");
						String searchTitle = scn.nextLine();
						pw.println(searchTitle);
						ArrayList<BookDTO> result = (ArrayList<BookDTO>)ois.readObject();
						for (BookDTO dto : result) {
							System.out.print(dto.getNum() + "\t");
							System.out.print(dto.getTitle() + "\t");
							System.out.print(dto.getCompany() + "\t");
							System.out.print(dto.getName() + "\t");
							System.out.print(dto.getCost() + "\n");
						}
						
					// 도서 등록
					} else if(option.equalsIgnoreCase("i")) {
						pw.println(option);
						System.out.println("\n도서 정보 등록 화면입니다.");
						
						System.out.println("등록할 도서의 번호를 입력하세요> ");
						int num = Integer.parseInt(scn.nextLine());
						System.out.println("등록 도서 번호 : "+num);
					
						pw.println(num);				
						
						boolean present = Boolean.parseBoolean(reader.readLine());
						
						if(present == true){
							System.out.println(num + "번 도서는 등록되어 있습니다");
							System.out.println("다시 시도해주세요");
						} else {
							System.out.println("제목을 입력하세요> ");
							String title = scn.nextLine();
							pw.println(title);
							System.out.println("출판사를 입력하세요> ");
							String company = scn.nextLine();
							pw.println(company);
							System.out.println("저자를 입력하세요> " );
							String name = scn.nextLine();
							pw.println(name);
							System.out.println("단가를 입력하세요> ");
							String cost = scn.nextLine();
							pw.println(cost);
							
							int succ = Integer.parseInt(reader.readLine());
	
							if(succ > 0) {
								System.out.println(num + "번 도서 정보가 등록되었습니다.");
							} else {
								System.out.println(num + "번 도서 정보가 등록 실패하였습니다.");
							}
						}
						
					// 도서 수정
					} else if(option.equalsIgnoreCase("u")){
						pw.println(option);
						System.out.println("\n도서 정보 수정 화면입니다.");
						
						System.out.println("수정할 도서의 번호를 입력하세요> ");
						int num = Integer.parseInt(scn.nextLine());
						System.out.println("등록 도서 번호 : "+num);
					
						pw.println(num);				
						
						boolean present = Boolean.parseBoolean(reader.readLine());
						
						if(present == true){
							System.out.print("수정할 도서의 제목을 입력하세요> ");
							pw.println(scn.nextLine());
							System.out.print("수정할 도서의 출판사를 입력하세요> ");
							pw.println(scn.nextLine());
							System.out.print("수정할 도서의 저자를 입력하세요> " );
							pw.println(scn.nextLine());
							System.out.print("수정할 도서의 단가를 입력하세요> ");
							pw.println(scn.nextLine());
							
							int succ = Integer.parseInt(reader.readLine());
	
							if(succ > 0) {
								System.out.println(num + "번 도서 정보가 수정되었습니다.");
							} else {
								System.out.println(num + "번 도서 정보 수정에 실패하였습니다.");
							}
						}else {
							System.out.println(num+"번 도서는 등록되어있지 않습니다.");
						}
					// 도서 삭제
					} else if(option.equalsIgnoreCase("d")){
						pw.println(option);
						System.out.println("\n도서 정보 삭제 화면입니다.");
						System.out.print("삭제할 도서의 번호를 입력하세요> ");
						int num = Integer.parseInt(scn.nextLine());
						pw.println(num);				
						boolean present = Boolean.parseBoolean(reader.readLine());
						if(present == true){
							int succ = Integer.parseInt(reader.readLine());
							
							if(succ > 0) {
								System.out.println(num + "번 도서 정보가 삭제되었습니다.");
							} else {
								System.out.println(num + "번 도서 정보 삭제에 실패하였습니다.");
							}
						}else {
							System.out.println(num+"번 도서는 등록되어있지 않습니다.");
						}
					// 프로그램 종료
					} else if(option.equalsIgnoreCase("e")) {					
							pw.println(option);
							System.out.println("도서 관리 프로그램을 종료합니다.");
							scn.close();
							socket.close();
							System.exit(0);
					} 
					else {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					} // if - else
				} // while loop
			}
			catch(IOException e) {
				e.printStackTrace();
			} // try - catch
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// try - catch
	} // run	
} // class
