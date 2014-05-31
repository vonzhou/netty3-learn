package netty.test.obj;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

public class MyObjDecoder implements ChannelUpstreamHandler{
	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("My obj decoder, handleUpstream");
		if(e instanceof MessageEvent){
			MessageEvent mEvent = (MessageEvent)e;
			// maybe other control msg , not deal with it
			if(!(mEvent instanceof ChannelBuffer)){
				ctx.sendUpstream(mEvent);
				return;
			}
			
			// else 
			ChannelBuffer buffer = (ChannelBuffer)mEvent.getMessage();
			ByteArrayInputStream input = new ByteArrayInputStream(buffer.array());
			ObjectInputStream ois = new ObjectInputStream(input);
			Object obj = ois.readObject();
			// here build a obj , and the upstream handlers can directly get it from channelBuffer
			Channels.fireMessageReceived(e.getChannel(), obj);
		}
	}

}
