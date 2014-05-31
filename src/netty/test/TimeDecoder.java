package netty.test;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class TimeDecoder extends FrameDecoder{

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		//here the para buffer is the cumulative buffer to get the data
		//from the ChannelBuffer
		System.out.println("decoder1-----");
		if(buffer.readableBytes() < 4)
			return null;
		return buffer.readBytes(4);
	} 

}
