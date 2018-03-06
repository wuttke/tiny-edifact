package eu.wuttke.tinyedifact.structure;

import java.util.LinkedList;
import java.util.List;

public class Message {

	private List<DataSegment> segments = new LinkedList<DataSegment>();

	public List<DataSegment> getSegments() {
		return segments;
	}

	public void setSegments(List<DataSegment> segments) {
		this.segments = segments;
	}
	
}
