package nio.thingkinginjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class GetChannel {
	
	public static void main(String[] args) throws IOException  {
		FileChannel fc = new FileOutputStream("data.txt").getChannel();
		fc.write(ByteBuffer.wrap("aaafafa".getBytes()));  
        fc.close();  
        fc = new RandomAccessFile("data.txt", "rw").getChannel();  
        fc.position(fc.size());  
        fc.write(ByteBuffer.wrap("bbbb".getBytes()));  
        fc.close();  
          
        fc = new FileInputStream("data.txt").getChannel();  
        ByteBuffer buff = ByteBuffer.allocate(1000);  
        fc.read(buff);  
        buff.flip();  
        while (buff.hasRemaining()) {  
            System.out.print((char)buff.get());  
        }  
	}

}




