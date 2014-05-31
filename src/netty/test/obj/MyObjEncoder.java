package netty.test.obj;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public class MyObjEncoder implements ChannelDownstreamHandler {
	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e)
			throws Exception {
		System.out.println("My obj encoder, handleDownstream");
		if(e instanceof MessageEvent){
			MessageEvent me = (MessageEvent)e;
			Object obj = (Object)me.getMessage();
			// 如果不是我们想要的Command对象 就直接往下流传递，不必进行后续处理
			if(!(obj instanceof Command)){
				ctx.sendDownstream(e);
				return;
			}
			
			//接下来几步是序列化， 将这个对象写入到字节数据组流中
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream objStream = new ObjectOutputStream(outputStream);
			objStream.writeObject(obj);
			objStream.flush();
			objStream.close();
			
			// byte array -> ChannelBuffer 
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			buffer.writeBytes(outputStream.toByteArray());
			e.getChannel().write(buffer);
		}
		else{
			// 其他事件自动流转，如bind , connect
			ctx.sendDownstream(e);
		}
	}
}
