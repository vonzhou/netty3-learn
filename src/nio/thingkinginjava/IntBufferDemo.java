package nio.thingkinginjava;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb.asIntBuffer();
		ib.put(new int[]{12,345,546,87,3,});
		System.out.println(ib.get(3));
		
		ib.put(3,54);
		ib.flip();
		while(ib.hasRemaining()){
			System.out.println(ib.get());
		}
	}

}
