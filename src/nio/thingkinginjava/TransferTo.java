package nio.thingkinginjava;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferTo {
	public static void main(String[] args) throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("data.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("data2.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0;
		long count = fromChannel.size();
		
		fromChannel.transferTo(position, count, toChannel);
	}
}
