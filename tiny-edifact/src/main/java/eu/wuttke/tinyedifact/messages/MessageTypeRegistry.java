package eu.wuttke.tinyedifact.messages;

import java.util.HashMap;
import java.util.Map;

public class MessageTypeRegistry {
	
	public Class<? extends Message> getMessageClass(String code) {
		return registry.get(code);
	}
		
	public boolean haveSpecificMessage(String code) {
		return registry.containsKey(code);
	}

	public void registerMessageClass(String code, Class<? extends Message> clazz) {
		registry.put(code, clazz);
	}

	private Map<String, Class<? extends Message>> registry = 
			new HashMap<String, Class<? extends Message>>();

	public synchronized static MessageTypeRegistry getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MessageTypeRegistry();
		return INSTANCE;
	}
	
	private static MessageTypeRegistry INSTANCE;

}
