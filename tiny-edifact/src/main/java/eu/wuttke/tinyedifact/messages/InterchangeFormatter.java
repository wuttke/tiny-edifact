package eu.wuttke.tinyedifact.messages;

import java.util.LinkedList;
import java.util.List;

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

	private List<DataSegment> collectSegments(Interchange i) {
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

	private void collectSegments(List<Message> messages, List<DataSegment> segments) {
		for (Message message : messages) {
			segments.addAll(message.getSegments());
		}
	}
}
