package netty.test.hello;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelSink;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ServerChannel;
import org.jboss.netty.channel.ServerChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class HelloWorldServer {
	public static void main(String[] args) {
		ServerBootstrap serverBootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),Executors.newCachedThreadPool()));
		// 
		serverBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline(){
				ChannelPipeline pipeline = Channels.pipeline();
				//pipeline.addLast("decoder", new StringDecoderHandler());
				//pipeline.addLast("encoder", new StringEncoderHandler());
				pipeline.addLast("handler", new HelloWorldServerHandler());
				pipeline.addLast("serverBuffer", new ServerBufferHandler());
				return pipeline;
			}
		});
		
		serverBootstrap.bind(new InetSocketAddress(2014));
	}

}
