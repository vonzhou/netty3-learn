package netty.test.obj;

import java.io.Serializable;

public class Command implements Serializable {
	private static final long serializationUID = 975230543295205234L;  // ??
	private String actionName ;
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	

}
