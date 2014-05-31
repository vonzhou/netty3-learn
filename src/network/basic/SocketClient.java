package network.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		Socket socket =  new Socket("127.0.0.1",2014);
		
		// 60s 超时
		socket.setSoTimeout(60000);
		//由套接字得到输出流，而后构造PrintWriter对象
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);  // autoFlush is true
		//
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//将从终端读取的数据输出到对应的套接字
		writer.println(reader.readLine());
		//刷新输出流，使传输发生，让Server尽快收到数据
		writer.flush();
		
		// 准备读取socket，接收来自Server的数据
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String result = bufferedReader.readLine();
		System.out.println("Msg from Server:" + result);
		
		// close the socket
		writer.close();
		bufferedReader.close();
		socket.close();
	}
}
