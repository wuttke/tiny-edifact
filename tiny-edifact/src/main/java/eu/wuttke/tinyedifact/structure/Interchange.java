package eu.wuttke.tinyedifact.structure;

import java.util.List;

public class Interchange {

	private List<MessageGroup> messageGroups;
	private List<Message> messages;
	
	public List<MessageGroup> getMessageGroups() {
		return messageGroups;
	}
	
	public void setMessageGroups(List<MessageGroup> messageGroups) {
		this.messageGroups = messageGroups;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
