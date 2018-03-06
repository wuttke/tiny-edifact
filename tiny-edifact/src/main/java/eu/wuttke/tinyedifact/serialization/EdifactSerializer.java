package eu.wuttke.tinyedifact.serialization;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.Interchange;
import eu.wuttke.tinyedifact.structure.Message;
import eu.wuttke.tinyedifact.structure.MessageGroup;
import eu.wuttke.tinyedifact.structure.SegmentElement;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class EdifactSerializer {

	public String serializeInterchange(Interchange i, 
			EdifactSeparators separators, 
			boolean includeNewlines) {
		StringBuilder sb = new StringBuilder();
		List<DataSegment> segments = collectSegments(i);
		for (DataSegment segment : segments) {
			sb.append(serializeSegment(segment, separators));
			if (includeNewlines)
				sb.append("\n");
		}
		return sb.toString();
	}

	public String serializeSegment(DataSegment segment, EdifactSeparators separators) {
		StringBuilder sb = new StringBuilder();
		sb.append(segment.getCode());
		if (segment.getValue() != null) {
			sb.append(separators.getComponentDataElementSeparator());
			sb.append(segment.getValue());
		}
		
		for (SegmentElement element : segment.getElements()) {
			sb.append(separators.getDataElementSeparator());
			serializeElement(element, separators, sb);
		}
		
		sb.append(separators.getSegmentTerminator());
		return sb.toString();
	}

	public void serializeElement(SegmentElement element, EdifactSeparators separators, StringBuilder sb) {
		if (element instanceof SimpleSegmentElement) {
			SimpleSegmentElement sse = (SimpleSegmentElement)element;
			if (sse.getValue() != null)
				sb.append(escapeValue(sse.getValue(), separators));
		} else if (element instanceof CompositeSegmentElement) {
			CompositeSegmentElement cse = (CompositeSegmentElement)element;
			for (int i = 0; i < cse.getValues().size(); i++) {
				if (i > 0)
					sb.append(separators.getComponentDataElementSeparator());
				String value = cse.getValues().get(i);
				if (value != null)
					sb.append(escapeValue(value, separators));
			}
		}
	}

	private String escapeValue(String value, EdifactSeparators separators) {
		value = value.replace(
				Character.toString(separators.getReleaseCharacter()), 
				Character.toString(separators.getReleaseCharacter()) + separators.getReleaseCharacter());
		value = value.replace(
				Character.toString(separators.getComponentDataElementSeparator()), 
				Character.toString(separators.getReleaseCharacter()) + separators.getComponentDataElementSeparator());
		value = value.replace(
				Character.toString(separators.getDataElementSeparator()), 
				Character.toString(separators.getReleaseCharacter()) + separators.getDataElementSeparator());
		value = value.replace(
				Character.toString(separators.getSegmentTerminator()), 
				Character.toString(separators.getReleaseCharacter()) + separators.getSegmentTerminator());
		return value;
	}

	private List<DataSegment> collectSegments(Interchange i) {
		List<DataSegment> segments = new LinkedList<DataSegment>();
		if (i.getMessageGroups() != null) {
			for (MessageGroup mg : i.getMessageGroups()) {
				collectSegments(mg.getMessages(), segments);
			}
		} else {
			collectSegments(i.getMessages(), segments);
		}
		return segments;
	}

	private void collectSegments(List<Message> messages, List<DataSegment> segments) {
		for (Message message : messages)
			segments.addAll(message.getSegments());
	}
	
}
