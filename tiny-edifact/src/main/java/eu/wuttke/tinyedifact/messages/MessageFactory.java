package eu.wuttke.tinyedifact.messages;

public class MessageFactory {
	
	public static Message createMessage(String code) {
		if (code == null || code.length() == 0)
			return new Message();
		
		try {
			MessageTypeRegistry registry = MessageTypeRegistry.getInstance();
			Class<? extends Message> clazz = registry.getMessageClass(code);
			if (clazz == null)
				return new Message();
			else
				return clazz.newInstance();
		} catch (InstantiationException ie) {
			throw new RuntimeException(ie);
		} catch (IllegalAccessException ia) {
			throw new RuntimeException(ia);
		}
	}

}
