package server;

import java.io.*;
import java.net.*;

public class LibServer{
	private static final int PORT = 7779;
	
	public static void main(String[] args) {
		ServerSocket serversocket = null;
		Socket socket = null;
		try{
			// connection
			serversocket = new ServerSocket(PORT);
			while(true){
				if((socket = serversocket.accept())!= null) {
					String ip = socket.getInetAddress().getHostAddress();
					int port = socket.getLocalPort();
					System.out.println("===================================");
					System.out.println("Connection Succeed");
					System.out.println("Client IP : "+ip);
					System.out.println("Port num : "+port);
					System.out.println("===================================");
					
					GetOption getOp = new GetOption(socket);
					getOp.start();
				}//if
			}//while loop
		}catch(Exception e){
			System.err.println("Connection Failed");
			e.printStackTrace();
		}// try-catch
	}// main
}// class