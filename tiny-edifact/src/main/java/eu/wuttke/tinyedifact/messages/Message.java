package eu.wuttke.tinyedifact.messages;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.structure.DataSegment;

public class Message {

	private List<DataSegment> segments = new LinkedList<DataSegment>();

	public List<DataSegment> getSegments() {
		return segments;
	}

	public void setSegments(List<DataSegment> segments) {
		this.segments = segments;
	}
	
}
