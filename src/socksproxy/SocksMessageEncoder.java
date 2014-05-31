package socksproxy;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.handler.codec.socks.SocksMessage;

/**
 * Encodes an {@link org.jboss.netty.handler.codec.socks.SocksMessage} into a
 * {@link org.jboss.netty.buffer.ChannelBuffer}.
 * {@link org.jboss.netty.handler.codec.oneone.OneToOneEncoder} implementation.
 * Use this with {@link org.jboss.netty.handler.codec.socks.SocksInitRequest},
 * {@link org.jboss.netty.handler.codec.socks.SocksInitResponse},
 * {@link org.jboss.netty.handler.codec.socks.SocksAuthRequest},
 * {@link org.jboss.netty.handler.codec.socks.SocksAuthResponse},
 * {@link org.jboss.netty.handler.codec.socks.SocksCmdRequest} and
 * {@link org.jboss.netty.handler.codec.socks.SocksCmdResponse}
 */
// this is a downstream handler
@ChannelHandler.Sharable
public class SocksMessageEncoder extends OneToOneEncoder {
	private static final String name = "SOCKS_MESSAGE_ENCODER";
	private static final int DEFAULT_ENCODER_BUFFER_SIZE = 1024;

	public static String getName() {
		return name;
	}

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		ChannelBuffer buffer = null;
		if (msg instanceof SocksMessage) {
			buffer = ChannelBuffers.buffer(DEFAULT_ENCODER_BUFFER_SIZE);
			((SocksMessage) msg).encodeAsByteBuf(buffer);
		} else if (msg instanceof ChannelBuffer) {
			buffer = (ChannelBuffer) msg;
		}
		return buffer;
	}
}
