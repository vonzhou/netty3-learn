package netty.test.obj;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ClassResolvers;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;

public class ObjectServer {
	public static void main(String[] args) {
		
		ServerBootstrap server = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		server.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(
						new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader()))
						//new MyObjDecoder()   // cannot work with my own decoder
						,new ObjectServerHandler());
			}
		});
		
		server.bind(new InetSocketAddress(2014));
		
	}

}
