package eu.wuttke.tinyedifact.structure;

import java.util.List;

public class Interchange {

	private DataSegment interchangeHeader;
	private List<MessageGroup> messageGroups;
	private List<Message> messages;
	private DataSegment interchangeTrailer;
	
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

	public DataSegment getInterchangeHeader() {
		return interchangeHeader;
	}

	public void setInterchangeHeader(DataSegment interchangeHeader) {
		this.interchangeHeader = interchangeHeader;
	}

	public DataSegment getInterchangeTrailer() {
		return interchangeTrailer;
	}

	public void setInterchangeTrailer(DataSegment interchangeTrailer) {
		this.interchangeTrailer = interchangeTrailer;
	}
	
}
