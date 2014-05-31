package nio.thingkinginjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class GetChannel2 {
	
	public static void main(String[] args) throws IOException  {
		RandomAccessFile aFile = new RandomAccessFile("data.txt", "rw");
		FileChannel fc =  aFile.getChannel();  
		
        ByteBuffer buff = ByteBuffer.allocate(48);  
        int bytesRead = fc.read(buff);
        
        while(bytesRead != -1){
        	// make buffer ready for read
        	buff.flip();
        	while(buff.hasRemaining()){
        		System.out.print((char)buff.get());// get a char one time
        	}
        	buff.clear();// make it ready for writing
        	bytesRead = fc.read(buff);
        }
        aFile.close();
	}

}




