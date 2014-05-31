package netty.test.hello;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class HelloWorldServerHandler extends SimpleChannelHandler {
	//protected static Logger logger = LoggerFactory.getLogger(HelloWorldServerHandler.class);
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		System.out.println("connected , this is server.");
		String msg = "Hello , this is server.";
		ChannelBuffer buffer = ChannelBuffers.buffer(msg.length());
		buffer.writeBytes(msg.getBytes());
		e.getChannel().write(buffer);
	}
	
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		//logger.warn("unexpected exception downstream", e.getCause());
		e.getChannel().close();
	}

}
