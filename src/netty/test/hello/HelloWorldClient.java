package netty.test.hello;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class HelloWorldClient {
	public static void main(String[] args) {
		ClientBootstrap clientBootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		// 
		clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline(){
				ChannelPipeline pipeline = Channels.pipeline();
				//pipeline.addLast("decoder", new StringDecoderHandler());
				//pipeline.addLast("encoder", new StringEncoderHandler());
				//pipeline.addLast("handler", new HelloWorldClientHandler());
				pipeline.addLast("clientBuffer", new ClientBufferHandler());
				return pipeline;
			}
		});
		
		ChannelFuture future = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 2014));
		future.awaitUninterruptibly();
		if(!future.isSuccess()){
			future.getCause().printStackTrace();
		}
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		future.getChannel().getFactory().releaseExternalResources();
	}

}
