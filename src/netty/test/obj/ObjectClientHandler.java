package netty.test.obj;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ObjectClientHandler extends SimpleChannelHandler {
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("client connected");
		Command cmd = new Command();
		cmd.setActionName("Hello action");
		e.getChannel().write(cmd);
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
	}

}
