package socksproxy;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.ClientSocketChannelFactory;
import org.jboss.netty.handler.codec.socks.*;

public final class SocksServerHandler extends SimpleChannelUpstreamHandler {
	private static final String name = "SOCKS_SERVER_HANDLER";

	public static String getName() {
		return name;
	}

    private final ClientSocketChannelFactory cf;

    public SocksServerHandler(ClientSocketChannelFactory cf) {
        this.cf = cf;
    }

    @Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        SocksRequest socksRequest = (SocksRequest) e.getMessage();
        // here is the socks protocol specified logic
        switch (socksRequest.getSocksRequestType()) {
		case INIT:
            //add a command decoder
            ctx.getPipeline().addFirst(SocksCmdRequestDecoder.getName(), new SocksCmdRequestDecoder());
            //no auth just for easy
            ctx.getChannel().write(new SocksInitResponse(SocksMessage.AuthScheme.NO_AUTH));
            break;
		case AUTH:
            ctx.getPipeline().addFirst(SocksCmdRequestDecoder.getName(), new SocksCmdRequestDecoder());
            //just indicate success 
            ctx.getChannel().write(new SocksAuthResponse(SocksMessage.AuthStatus.SUCCESS));
            break;
		case CMD:
            SocksCmdRequest req = (SocksCmdRequest) socksRequest;
            if (req.getCmdType() == SocksMessage.CmdType.CONNECT) {
                //add the handler to deal with connection
                ctx.getPipeline().addLast(SocksServerConnectHandler.getName(), new SocksServerConnectHandler(cf));
                ctx.getPipeline().remove(this);
            } else {
                ctx.getChannel().close();
            }
            break;
		case UNKNOWN:
            break;
		}
		super.messageReceived(ctx, e);
	}

}
