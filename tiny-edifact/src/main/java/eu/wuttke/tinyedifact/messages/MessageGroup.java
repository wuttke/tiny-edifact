package eu.wuttke.tinyedifact.messages;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.segments.MessageGroupHeaderSegment;
import eu.wuttke.tinyedifact.segments.MessageGroupTrailerSegment;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class MessageGroup {

	private MessageGroupHeaderSegment functionalGroupHeader;
	private List<Message> messages = new LinkedList<Message>();
	private MessageGroupTrailerSegment functionalGroupTrailer;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public DataSegment getFunctionalGroupHeader() {
		return functionalGroupHeader;
	}

	public void setFunctionalGroupHeader(MessageGroupHeaderSegment functionalGroupHeader) {
		this.functionalGroupHeader = functionalGroupHeader;
	}

	public DataSegment getFunctionalGroupTrailer() {
		return functionalGroupTrailer;
	}

	public void setFunctionalGroupTrailer(MessageGroupTrailerSegment functionalGroupTrailer) {
		this.functionalGroupTrailer = functionalGroupTrailer;
	}
	
}
