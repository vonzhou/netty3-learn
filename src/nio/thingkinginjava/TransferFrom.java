package nio.thingkinginjava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TransferFrom {
	public static void main(String[] args) throws Exception {
		if(args.length != 2){
			System.out.println("arguments should be: sourcefile destfile");
			System.exit(1);
		}
		FileChannel 
			in = new FileInputStream(args[0]).getChannel(),
			out = new FileOutputStream(args[1]).getChannel();
		
		in.transferTo(0, in.size(), out);
	}

}
