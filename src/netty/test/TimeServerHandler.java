package netty.test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeServerHandler extends SimpleChannelHandler{
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Channel ch = e.getChannel();
		ChannelBuffer time = ChannelBuffers.buffer(4); //sizeof int
		time.writeInt((int)(System.currentTimeMillis()/1000L + 2208988800L));
		
		ChannelFuture cf = ch.write(time);
		cf.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				Channel ch = future.getChannel();
				ch.close();
			}
		});
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Channel c = e.getChannel();
		c.close();
	}
}



