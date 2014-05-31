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
			// �������������Ҫ��Command���� ��ֱ�����������ݣ����ؽ��к�������
			if(!(obj instanceof Command)){
				ctx.sendDownstream(e);
				return;
			}
			
			//���������������л��� ���������д�뵽�ֽ�����������
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
			// �����¼��Զ���ת����bind , connect
			ctx.sendDownstream(e);
		}
	}
}
