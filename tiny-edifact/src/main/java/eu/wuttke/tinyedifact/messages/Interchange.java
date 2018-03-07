package eu.wuttke.tinyedifact.messages;

import java.util.List;

import eu.wuttke.tinyedifact.segments.InterchangeHeaderSegment;
import eu.wuttke.tinyedifact.segments.InterchangeTrailerSegment;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class Interchange {

	private InterchangeHeaderSegment interchangeHeader;
	private List<MessageGroup> messageGroups;
	private List<Message> messages;
	private InterchangeTrailerSegment interchangeTrailer;
	
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

	public void setInterchangeHeader(InterchangeHeaderSegment interchangeHeader) {
		this.interchangeHeader = interchangeHeader;
	}

	public DataSegment getInterchangeTrailer() {
		return interchangeTrailer;
	}

	public void setInterchangeTrailer(InterchangeTrailerSegment interchangeTrailer) {
		this.interchangeTrailer = interchangeTrailer;
	}
	
}
