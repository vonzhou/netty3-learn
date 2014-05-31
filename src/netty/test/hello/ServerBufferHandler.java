package netty.test.hello;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerBufferHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		//接收来自客户端的消息，根据自己的规则来解析，不论对方是如何发送的，要理解 流的概念
		ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
		while(buffer.readableBytes() >= 5){
			// 条件控制是 判断可读字节数，满足条件后再取出
			ChannelBuffer tmp = buffer.readBytes(5);
			System.out.println(tmp.toString(Charset.defaultCharset()));
		}
		// 读出剩下的内容
		System.out.println(buffer.toString(Charset.defaultCharset()));
	}

}
