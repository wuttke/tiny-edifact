package eu.wuttke.tinyedifact.serialization;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SegmentElement;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class EdifactSerializer {

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
	
}
