package eu.wuttke.tinyedifact.exchange;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.messages.Interchange;
import eu.wuttke.tinyedifact.messages.Message;
import eu.wuttke.tinyedifact.messages.MessageGroup;
import eu.wuttke.tinyedifact.serialization.EdifactSeparators;
import eu.wuttke.tinyedifact.serialization.EdifactSerializer;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class InterchangeFormatter {

	public String formatInterchange(Interchange i, 
			EdifactSeparators separators, 
			boolean includeNewlines) {
		EdifactSerializer serializer = new EdifactSerializer();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UNA");
		sb.append(separators.generateServiceStringAdvice());
		if (includeNewlines)
			sb.append("\n");
		
		List<DataSegment> segments = collectSegments(i);
		for (DataSegment segment : segments) {
			sb.append(serializer.serializeSegment(segment, separators));
			if (includeNewlines)
				sb.append("\n");
		}
		return sb.toString();
	}
	
	public void trimInterchange(Interchange i) {
		List<DataSegment> segments = collectSegments(i);
		for (DataSegment segment : segments)
			segment.trimEmptyTrailingElementsAndValues();
	}

	protected List<DataSegment> collectSegments(Interchange i) {
		List<DataSegment> segments = new LinkedList<DataSegment>();
		if (i.getInterchangeHeader() != null)
			segments.add(i.getInterchangeHeader());
		if (i.getMessageGroups() != null) {
			for (MessageGroup mg : i.getMessageGroups()) {
				if (mg.getFunctionalGroupHeader() != null)
					segments.add(mg.getFunctionalGroupHeader());
				collectSegments(mg.getMessages(), segments);
				if (mg.getFunctionalGroupTrailer() != null)
					segments.add(mg.getFunctionalGroupTrailer());
			}
		} else {
			collectSegments(i.getMessages(), segments);
		}
		if (i.getInterchangeTrailer() != null)
			segments.add(i.getInterchangeTrailer());
		return segments;
	}

	protected void collectSegments(List<Message> messages, List<DataSegment> segments) {
		MessageFormatter messageFormatter = new MessageFormatter();
		for (Message message : messages) {
			messageFormatter.collectSegments(message, segments);
		}
	}
}
