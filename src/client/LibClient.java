package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class LibClient{
	//Server Info
	private static final String SEVER_IP = "127.0.0.1";
	private static final int PORT = 7779;
	
	//Main
	public static void main(String[] args) {
		try{
			//connection
			Socket socket = new Socket(SEVER_IP, PORT);
			boolean access;
			if ( access = socket.isConnected()) {
				System.out.println("���ӿ� �����Ͽ����ϴ�");
				Thread thread = new Menu(socket);
				thread.start();
			} else {
				System.out.println("���ӿ� �����Ͽ����ϴ�");	
			}
		}catch(Exception e){
			System.err.print("Connection Error");
			e.printStackTrace();
		}//try-catch
	}//main	
}//class
	