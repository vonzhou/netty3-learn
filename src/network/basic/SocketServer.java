package network.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer{
	public static void main(String[] args) throws Exception{
		
		ServerSocket serverSocket = new ServerSocket(2014);
		while(true){
			//�������׽��֣���������ӣ��򷵻������׽��֣������������
			Socket cliSocket = serverSocket.accept();
			System.out.println("Connection from " + cliSocket);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(cliSocket.getInputStream()));
		String request = bufferedReader.readLine();
		System.out.println("Client say:" + request);
		
		// ��socket���������������ظ�client��
		PrintWriter writer = new PrintWriter(
				new OutputStreamWriter(cliSocket.getOutputStream()));
		writer.write("Hello client, I am Server vonzhou.");
		writer.flush();
		
		writer.close();
		bufferedReader.close();
		cliSocket.close();
		}
		
	}
	

}
