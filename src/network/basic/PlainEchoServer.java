package network.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainEchoServer{
	public static void main(String[] args) throws Exception{
		
		ServerSocket serverSocket = new ServerSocket(2014);
		while(true){
			//监听该套接字，如果有连接，则返回连接套接字，服务这个连接
			final Socket cliSocket = serverSocket.accept();
			System.out.println("Connection from " + cliSocket);
			// server this client in a new thread
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(cliSocket.getInputStream()));
						PrintWriter writer = new PrintWriter(
								new OutputStreamWriter(cliSocket.getOutputStream()));
						while(true){
							String request = bufferedReader.readLine();
							//System.out.println(request);
							writer.println(request);   // NB:  println or write() + println()
							writer.flush();
						}
					} catch (IOException e) {
						e.printStackTrace();
						try {
							cliSocket.close();
						} catch (IOException e2) {
							// ignore exception by close()
						}
					}
				} // end class Runnable
			}
			).run();
	
		}
		
	}
	

}
