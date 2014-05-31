package netty.test;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeClientHandler4 extends SimpleChannelHandler{
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		//the decoder put UnixTime obj,so get just what we need
		UnixTime cur = (UnixTime)e.getMessage();
		System.out.println(cur);
		e.getChannel().close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Channel c = e.getChannel();
		c.close();
	}
}