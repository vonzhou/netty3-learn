package socksproxy;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//这里参考了 code4craft 的代码
public class SocksProxy {

    public void run() {

        // Configure the bootstrap.
        Executor boss = Executors.newCachedThreadPool();
        Executor worker = Executors.newCachedThreadPool();
        ServerBootstrap serverBoot = new ServerBootstrap(
                new NioServerSocketChannelFactory(boss, worker));

        // initialize the client socket used by proxy
        ClientSocketChannelFactory cf =
                new NioClientSocketChannelFactory(boss, worker);

        serverBoot.setPipelineFactory(new SocksProxyPipelineFactory(cf));

        // Start up the server.
        serverBoot.bind(new InetSocketAddress(1080)); 
        // socks protocol port number is 1080
    }

    public static void main(String[] args) {
        new SocksProxy().run();
    }
}