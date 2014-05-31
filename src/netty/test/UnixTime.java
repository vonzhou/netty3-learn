package netty.test;

import java.util.Date;

public class UnixTime {
	private int value;
	public UnixTime(int value){
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value = value;
	}
	@Override
	public String toString() {
		return new Date(value * 1000L).toString();
	}
}
