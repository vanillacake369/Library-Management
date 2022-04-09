package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.tools.DocumentationTool;


public class GetOption extends Thread{
	
	private Socket socket;
	private BufferedReader reader;
	
	public GetOption(){}
	
	public GetOption(Socket socket){
		super();
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				String option = reader.readLine();
				Request request = new Request(reader,writer,socket);
				BookDAO dao = new BookDAO();
				
				//get option from client
				// option : a
				if (option.equalsIgnoreCase("a")){
					ArrayList<BookDTO> answer = request.bookSearchAllInput();
					oos.writeObject(answer);
				
				// option : s
				} else if(option.equalsIgnoreCase("s")) {
					String title = reader.readLine();
					ArrayList<BookDTO> answer = request.bookSearchTitleInput(title);
					oos.writeObject(answer);
					
				// option : i
				} else if(option.equalsIgnoreCase("i")){
					int num = Integer.parseInt(reader.readLine());
					ResultSet rs = dao.checkNum(num);	
					try{
						if(rs.next() == true){
							writer.println("true");
						} else {
							writer.println("false");
							request.bookInsertInput(num);
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				// option : d
				} else if(option.equalsIgnoreCase("d")){
					int num = Integer.parseInt(reader.readLine());
					ResultSet rs = dao.checkNum(num);	
					try{
						if(rs.next() == true){
							writer.println("true");
							request.bookDeleteInput(num);
						} else {
							writer.println("false");
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				
				// option : u
				} else if(option.equalsIgnoreCase("u")){
					int num = Integer.parseInt(reader.readLine());
					ResultSet rs = dao.checkNum(num);	
					try{
						if(rs.next() == true){
							writer.println("true");
							request.bookUpdateInput(num);
						} else {
							writer.println("false");
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				// option : e
				}else if(option.equalsIgnoreCase("e")){
					System.out.println("Client Left The Program");
					break;
				} else {
					System.out.println("Client has chosen the wrong option");
				} // if else
			}catch(IOException e) {
				e.printStackTrace();
			}
		}//while loop
	}//showMenu	
} // class
