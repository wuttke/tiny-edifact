package eu.wuttke.tinyedifact.messages;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.segments.MessageHeaderSegment;
import eu.wuttke.tinyedifact.segments.MessageTrailerSegment;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class Message {

	private MessageHeaderSegment messageHeaderSegment;
	private MessageTrailerSegment messageTrailerSegment;
	private List<DataSegment> segments = new LinkedList<DataSegment>();

	public List<DataSegment> getSegments() {
		return segments;
	}

	public void setSegments(List<DataSegment> segments) {
		this.segments = segments;
	}

	public MessageHeaderSegment getMessageHeaderSegment() {
		return messageHeaderSegment;
	}

	public void setMessageHeaderSegment(MessageHeaderSegment messageHeaderSegment) {
		this.messageHeaderSegment = messageHeaderSegment;
	}

	public MessageTrailerSegment getMessageTrailerSegment() {
		return messageTrailerSegment;
	}

	public void setMessageTrailerSegment(MessageTrailerSegment messageTrailerSegment) {
		this.messageTrailerSegment = messageTrailerSegment;
	}
	
}
