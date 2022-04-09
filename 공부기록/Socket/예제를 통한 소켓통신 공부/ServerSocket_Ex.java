package ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocketServer {

	ServerSocket serverSocket; // 소켓 생성
	Socket socket;
	BufferedReader br; // 전역으로 빼줌

	public MySocketServer() throws Exception {

		serverSocket = new ServerSocket(15000); // 서버 소켓 생성
		//1. ServerSocket은 어떤 Exception이 발생하는가? 
		/*
			public ServerSocket(int port) throws IOException 
			Parameters 
				A port number, or 0 for any available port. 
			Throws 
				IOException 
					If any kind of I/O error occurs. 
				SecurityException 
					If the application is not allowed to listen on the given port. 
			docstore.mik.ua/orelly/java/fclass/ch15_15.htm
		*/
		//2.임의의 포트번호를 넣어도 되는 것인가? 5555이거나 9999이거나 아무 상관이 없는가?
		/*
			It is up to you to specify the listening port for your server side application because the client side has to know to which port it should try connecting to.
			
			As ports range 1-1024 is reserved for system services (this is just by convention, not a rule) you can pick any port from (1024, 65535]. 
			
			If the port you have selected is occupied by another application, it will just throw an exception. 
			If you do not specify the port number, it will be randomly generated.
		*/
		socket = serverSocket.accept(); // 요청대기
		//3. 요청대기하는 동안 프로그램은 대기상태인가? 즉, 요청이 들어오기 전까지는 아래 line부터는 실행이 멈춰있는가?
		/*
			public Socket accept() throws IOException
				Listens for a connection to be made to this socket and accepts it. 
				The method blocks until a connection is made.
				A new Socket s is created and, if there is a security manager, 
				the security manager's checkAccept method is called with s.getInetAddress().getHostAddress() and s.getPort() 	
				as its arguments to ensure the operation is allowed. 
				This could result in a SecurityException.
		*/
		System.out.println("요청이 들어옴");

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		// socket.getInputStream()을 InputStreamReader()로 감싸고 이를 또 BufferedReader()의 생성자에 넘겨서 BufferedReader객체 생성.
		//4. why?
		/*
			public InputStream getInputStream ()   throws IOException  
				The getInputStream() method of Java Socket class returns an "input stream for the given socket." 
				If you close the returned InputStream, then it will close the linked socket.
			
			public InputStreamReader(InputStream in)
				Creates an InputStreamReader that uses the default **charset**.

				**A charset** in the Java platform therefore defines a mapping between sequences of sixteen-bit UTF-16 code units (that is, sequences of chars) and sequences of bytes.
				A character encoding tells the computer how to interpret raw zeroes and ones into real characters.
				It usually does this by pairing numbers with characters. Words and sentences in text are created from characters and these characters are grouped into a character set.
			
			public BufferedReader(Reader in)
				Creates a buffering character-input stream that uses a default-sized input buffer.
		*/
		String msg = "";

		while ((msg = br.readLine()) != null) {
			System.out.println("sender :" + msg);
			//BufferedReader가 읽어온 Line하나씩 println
		}

		br.close();
		//close BufferdReader
		socket.close();
		//close client socket
		serverSocket.close();
		//close server socket

	}

	public static void main(String[] args) {
		try {
			new MySocketServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


출처: https://saludar.tistory.com/108 [ah-ryeong]