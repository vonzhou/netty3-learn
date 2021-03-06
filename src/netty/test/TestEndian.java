package netty.test;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.nio.ByteOrder;

public class TestEndian {

    public static void testEndianness() {
        ChannelBuffer buffer;
        int value = 12;

        //ByteOrder.BIG_ENDIAN
        buffer = ChannelBuffers.buffer(ByteOrder.BIG_ENDIAN, 4);
        buffer.writeInt(value);
        for (int i = 0; i < 4; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }

        //ByteOrder.LITTLE_ENDIAN
        buffer = ChannelBuffers.buffer(ByteOrder.LITTLE_ENDIAN, 4);
        buffer.writeInt(value);
        for (int i = 0; i < 4; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }

        //ByteOrder.nativeOrder()
        buffer = ChannelBuffers.buffer(ByteOrder.nativeOrder(), 4);
        buffer.writeInt(value);
        for (int i = 0; i < 4; i++) {
            byte b = buffer.readByte();
            System.out.println(b);
        }
    }
    
    public static void main(String[] args) {
    	testEndianness();
	}

}
