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
			//监听该套接字，如果有连接，则返回连接套接字，服务这个连接
			Socket cliSocket = serverSocket.accept();
			System.out.println("Connection from " + cliSocket);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(cliSocket.getInputStream()));
		String request = bufferedReader.readLine();
		System.out.println("Client say:" + request);
		
		// 由socket构建输出流，而后回复client端
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
