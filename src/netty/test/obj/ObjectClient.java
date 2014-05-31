package netty.test.obj;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectClient {
	public static void main(String[] args) {
		ClientBootstrap client = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		client.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
						new ObjectEncoder()
						//new MyObjEncoder() // cannot work with my own encoder
						//,new ObjectClientHandler()
						,new ConcurrencyTestHandler()
						);
			}
		});
		
		client.connect(new InetSocketAddress("127.0.0.1", 2014));
	}
	

}
