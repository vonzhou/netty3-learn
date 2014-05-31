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
		
		// 60s ��ʱ
		socket.setSoTimeout(60000);
		//���׽��ֵõ��������������PrintWriter����
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);  // autoFlush is true
		//
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		//�����ն˶�ȡ�������������Ӧ���׽���
		writer.println(reader.readLine());
		//ˢ���������ʹ���䷢������Server�����յ�����
		writer.flush();
		
		// ׼����ȡsocket����������Server������
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
