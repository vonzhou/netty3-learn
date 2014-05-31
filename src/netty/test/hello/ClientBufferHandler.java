package netty.test.hello;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientBufferHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// 分段发送消息
		sendMsgByFrame(e);
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
	}
	
	private void sendMsgByFrame(ChannelStateEvent e){
		String one = "hello, i am vonzhou";
		String two = "to be happy";
		String three = "to be graceful!";
		e.getChannel().write(transfer2Buffer(one));
		e.getChannel().write(transfer2Buffer(two));
		e.getChannel().write(transfer2Buffer(three));
		
	}
	
	private ChannelBuffer transfer2Buffer(String str){
		ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
		buffer.writeBytes(str.getBytes());
		return buffer;
	}

}


