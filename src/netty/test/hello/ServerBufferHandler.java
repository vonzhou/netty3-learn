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
		//�������Կͻ��˵���Ϣ�������Լ��Ĺ��������������۶Է�����η��͵ģ�Ҫ��� ���ĸ���
		ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
		while(buffer.readableBytes() >= 5){
			// ���������� �жϿɶ��ֽ�����������������ȡ��
			ChannelBuffer tmp = buffer.readBytes(5);
			System.out.println(tmp.toString(Charset.defaultCharset()));
		}
		// ����ʣ�µ�����
		System.out.println(buffer.toString(Charset.defaultCharset()));
	}

}
