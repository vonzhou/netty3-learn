package netty.test;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class TimeServer {
	public static ChannelGroup allChannels = new DefaultChannelGroup("time-server");
	
	public static void main(String[] args) {
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		
		ServerBootstrap bootstrap = new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new TimeServerHandler2(),
										 new TimeEncoder());
			}
		});
		
		bootstrap.setOption("reuseAddr", true);
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		
		Channel channel = bootstrap.bind(new InetSocketAddress(8080));
		
		allChannels.add(channel);
		//waitForShutdownCommand();  this is a imaginary logic:for instance 
		//when there is accepted connection we close this server ;
		if(allChannels.size() >=2){
			ChannelGroupFuture f = allChannels.close();
			f.awaitUninterruptibly();
			factory.releaseExternalResources();
		}
	}
}





