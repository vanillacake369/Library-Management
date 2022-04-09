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
		System.out.println("============���� ��ȸ ����============");
		System.out.println("1. ���� ��� ���� : A");
		System.out.println("2. ���� ���� �˻� : S");
		System.out.println("3. ���� ���� ��� : I");
		System.out.println("4. ���� ���� ���� : D");
		System.out.println("5. ���� ���� ���� : U");
		System.out.println("6. ���� ���� ���� : E");
		System.out.println("===================================");
		System.out.print("�޴��� �Է��ϼ���> ");
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
					
					// ���� ���� : a
					if (option.equalsIgnoreCase("a")){
						// pass option to server
						pw.println(option);
						System.out.println("���� ��� ���� ȭ���Դϴ�.");
						ArrayList<BookDTO> result = (ArrayList<BookDTO>)ois.readObject();
						for (BookDTO dto : result) {
							System.out.print(dto.getNum() + "\t");
							System.out.print(dto.getTitle() + "\t");
							System.out.print(dto.getCompany() + "\t");
							System.out.print(dto.getName() + "\t");
							System.out.print(dto.getCost() + "\n");
						}
						
					// ���� �˻� : a
					} else if(option.equalsIgnoreCase("s")) {
						// pass option to server
						pw.println(option);
						System.out.println("\n���� ���� �˻� ȭ���Դϴ�.");
						System.out.print("�˻��� ������ ������ �Է��ϼ���> ");
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
						
					// ���� ���
					} else if(option.equalsIgnoreCase("i")) {
						pw.println(option);
						System.out.println("\n���� ���� ��� ȭ���Դϴ�.");
						
						System.out.println("����� ������ ��ȣ�� �Է��ϼ���> ");
						int num = Integer.parseInt(scn.nextLine());
						System.out.println("��� ���� ��ȣ : "+num);
					
						pw.println(num);				
						
						boolean present = Boolean.parseBoolean(reader.readLine());
						
						if(present == true){
							System.out.println(num + "�� ������ ��ϵǾ� �ֽ��ϴ�");
							System.out.println("�ٽ� �õ����ּ���");
						} else {
							System.out.println("������ �Է��ϼ���> ");
							String title = scn.nextLine();
							pw.println(title);
							System.out.println("���ǻ縦 �Է��ϼ���> ");
							String company = scn.nextLine();
							pw.println(company);
							System.out.println("���ڸ� �Է��ϼ���> " );
							String name = scn.nextLine();
							pw.println(name);
							System.out.println("�ܰ��� �Է��ϼ���> ");
							String cost = scn.nextLine();
							pw.println(cost);
							
							int succ = Integer.parseInt(reader.readLine());
	
							if(succ > 0) {
								System.out.println(num + "�� ���� ������ ��ϵǾ����ϴ�.");
							} else {
								System.out.println(num + "�� ���� ������ ��� �����Ͽ����ϴ�.");
							}
						}
						
					// ���� ����
					} else if(option.equalsIgnoreCase("u")){
						pw.println(option);
						System.out.println("\n���� ���� ���� ȭ���Դϴ�.");
						
						System.out.println("������ ������ ��ȣ�� �Է��ϼ���> ");
						int num = Integer.parseInt(scn.nextLine());
						System.out.println("��� ���� ��ȣ : "+num);
					
						pw.println(num);				
						
						boolean present = Boolean.parseBoolean(reader.readLine());
						
						if(present == true){
							System.out.print("������ ������ ������ �Է��ϼ���> ");
							pw.println(scn.nextLine());
							System.out.print("������ ������ ���ǻ縦 �Է��ϼ���> ");
							pw.println(scn.nextLine());
							System.out.print("������ ������ ���ڸ� �Է��ϼ���> " );
							pw.println(scn.nextLine());
							System.out.print("������ ������ �ܰ��� �Է��ϼ���> ");
							pw.println(scn.nextLine());
							
							int succ = Integer.parseInt(reader.readLine());
	
							if(succ > 0) {
								System.out.println(num + "�� ���� ������ �����Ǿ����ϴ�.");
							} else {
								System.out.println(num + "�� ���� ���� ������ �����Ͽ����ϴ�.");
							}
						}else {
							System.out.println(num+"�� ������ ��ϵǾ����� �ʽ��ϴ�.");
						}
					// ���� ����
					} else if(option.equalsIgnoreCase("d")){
						pw.println(option);
						System.out.println("\n���� ���� ���� ȭ���Դϴ�.");
						System.out.print("������ ������ ��ȣ�� �Է��ϼ���> ");
						int num = Integer.parseInt(scn.nextLine());
						pw.println(num);				
						boolean present = Boolean.parseBoolean(reader.readLine());
						if(present == true){
							int succ = Integer.parseInt(reader.readLine());
							
							if(succ > 0) {
								System.out.println(num + "�� ���� ������ �����Ǿ����ϴ�.");
							} else {
								System.out.println(num + "�� ���� ���� ������ �����Ͽ����ϴ�.");
							}
						}else {
							System.out.println(num+"�� ������ ��ϵǾ����� �ʽ��ϴ�.");
						}
					// ���α׷� ����
					} else if(option.equalsIgnoreCase("e")) {					
							pw.println(option);
							System.out.println("���� ���� ���α׷��� �����մϴ�.");
							scn.close();
							socket.close();
							System.exit(0);
					} 
					else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
