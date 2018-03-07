package eu.wuttke.tinyedifact.messages;

import java.util.List;

import eu.wuttke.tinyedifact.structure.DataSegment;

public class MessageGroup {

	private DataSegment functionalGroupHeader;
	private List<Message> messages;
	private DataSegment functionalGroupTrailer;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public DataSegment getFunctionalGroupHeader() {
		return functionalGroupHeader;
	}

	public void setFunctionalGroupHeader(DataSegment functionalGroupHeader) {
		this.functionalGroupHeader = functionalGroupHeader;
	}

	public DataSegment getFunctionalGroupTrailer() {
		return functionalGroupTrailer;
	}

	public void setFunctionalGroupTrailer(DataSegment functionalGroupTrailer) {
		this.functionalGroupTrailer = functionalGroupTrailer;
	}
	
}
