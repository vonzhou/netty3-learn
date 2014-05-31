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

public class TimeServerHandler2 extends SimpleChannelHandler{
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		TimeServer.allChannels.add(e.getChannel());
	}
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		UnixTime time = new UnixTime((int) (System.currentTimeMillis() / 1000));
		ChannelFuture f = e.getChannel().write(time);
		f.addListener(ChannelFutureListener.CLOSE);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
		Channel c = e.getChannel();
		c.close();
	}
}



