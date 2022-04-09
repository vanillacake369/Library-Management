package ch15; 

import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.io.PrintWriter; 
import java.net.Socket; 
import java.net.UnknownHostException; 


public class MySocketClient { 

	Socket socket; 
	BufferedWriter bw; 
	BufferedReader br; 
	
	public MySocketClient() throws Exception {
		socket = new Socket("192.168.0.47", 15000); // 서버소켓의 accept() 함수를 호출 
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
		//소켓에 대한 bufferedWriter(UNI출력) 생성
		br = new BufferedReader(new InputStreamReader(System.in)); 
		//System.in(키보드)에 대한 bufferedReader(UNI입력) 생성
		
		String msg = ""; 
		while ((msg=br.readLine()) != null) { 
		//br.readLine으로 읽은 Line(->msg)이 null이 아니라면
			bw.write(msg +"\n"); 
			//소켓에 "msg+\n" write
			bw.flush(); 
			//writer버퍼 비우기
		} 
		
		bw.close(); 
		br.close(); 
		socket.close(); 
	} 
	public static void main(String[] args) { 
		try { 
			new MySocketClient(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		} 
	} 
}

출처: https://saludar.tistory.com/108 [ah-ryeong]