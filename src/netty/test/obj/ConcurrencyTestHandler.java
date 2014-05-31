package netty.test.obj;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ConcurrencyTestHandler extends SimpleChannelHandler {
	private static int count = 0;
	@Override
	public void channelConnected(ChannelHandlerContext ctx, final ChannelStateEvent e)
			throws Exception {
		for(int i = 0; i < 100000; i ++){
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					sendObj(e.getChannel());
				}
			});
			System.out.println("Thread count: " + i);
			t.run();
		}
	}
	
	private void sendObj(Channel channel){
		count ++;
		Command cmd = new Command();
		cmd.setActionName("Hello action");
		System.out.println("Write :" + count);
		channel.write(cmd);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
	}

}
