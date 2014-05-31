package network.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class PlainNioEchoServer{
	public static void serv(int port) throws IOException{
		//ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Listening for connections on port: " + port);
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket ss = serverChannel.socket();
		InetSocketAddress addr = new InetSocketAddress(port);
		ss.bind(addr);
		
		serverChannel.configureBlocking(false); // non-blocking
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true){
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// after blocking of select() ,there have some evnets 
			Set readyKeys = selector.selectedKeys();
			Iterator it = readyKeys.iterator();
			while(it.hasNext()){
				SelectionKey key = (SelectionKey)it.next();
				it.remove();
				try {
					if(key.isAcceptable()){
						ServerSocketChannel server = (ServerSocketChannel)key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client);
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, 
								ByteBuffer.allocate(100));
						
					}
					if(key.isReadable()){
						SocketChannel client = (SocketChannel)key.channel();
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						client.read(buffer);
						// 当有客户端可读可写的时候就，操纵附属在这个选择器上的ByteBuffer。
						// 与每个连接套接字关联的？？
					}
					if(key.isWritable()){
						SocketChannel client = (SocketChannel)key.channel();
						ByteBuffer buffer = (ByteBuffer)key.attachment();
						buffer.flip();
						client.write(buffer);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
					key.cancel();// 取消这个注册的相应选择键
					try {
						key.channel().close();
					} catch (Exception e) {	}
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		serv(2014);
	}
	

}
