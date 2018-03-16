package eu.wuttke.tinyedifact.exchange;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.messages.Message;
import eu.wuttke.tinyedifact.serialization.EdifactSeparators;
import eu.wuttke.tinyedifact.serialization.EdifactSerializer;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class MessageFormatter {
	
	public String formatMessage(Message message,
			EdifactSeparators separators, 
			boolean includeNewlines) {
		EdifactSerializer serializer = new EdifactSerializer();
		StringBuilder sb = new StringBuilder();

		List<DataSegment> segments = new LinkedList<DataSegment>();
		collectSegments(message, segments);
		
		for (DataSegment segment : segments) {
			sb.append(serializer.serializeSegment(segment, separators));
			if (includeNewlines)
				sb.append("\n");
		}
		
		return sb.toString();
	}

	public void collectSegments(Message message, List<DataSegment> segments) {
		if (message.getMessageHeaderSegment() != null)
			segments.add(message.getMessageHeaderSegment());
		segments.addAll(message.getSegments());
		if (message.getMessageTrailerSegment() != null)
			segments.add(message.getMessageTrailerSegment());
	}

}
