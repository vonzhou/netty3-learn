// P556

package nio.thingkinginjava;

import java.nio.ByteBuffer;

public class GetData {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		// atomatically set to zeros
		int i = 0;
		while(i++ < bb.limit())
			if(bb.get() !=0 )
				System.out.println("Nonzero");
		System.out.println("i = " + i);
		bb.rewind();
		
		//store and read char array
		bb.asCharBuffer().put("vonzhou");
		char c;
		while((c = bb.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		bb.rewind();
		
		// store and read short integer
		bb.asShortBuffer().put((short)65540);
		System.out.println(bb.getShort());
		bb.rewind();
		
		//store and read int
		bb.asIntBuffer().put(944979475);
		System.out.println(bb.getInt());
		bb.rewind();
		
		// long
		bb.asLongBuffer().put(879797897);
		System.out.println(bb.getLong());
		bb.rewind();
		
		// a float
		bb.asFloatBuffer().put(36456365);
		System.out.println(bb.getFloat());
		bb.rewind();
		
		// double
		bb.asDoubleBuffer().put(36456365);
		System.out.println(bb.getDouble());
		bb.rewind();
		
		
	}

}
