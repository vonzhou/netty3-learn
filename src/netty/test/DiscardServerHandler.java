package netty.test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class DiscardServerHandler extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Channel channel = e.getChannel();
		channel.write(e.getMessage());
	}
	
	/*
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ChannelBuffer cb = (ChannelBuffer)e.getMessage();
		while(cb.readable()){
			System.out.println((char)cb.readByte());
			System.out.flush();
		}
	}
	//当有消息的时候，这个方法就会调用；
	*/
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Channel c = e.getChannel();
		c.close();
	}

}


