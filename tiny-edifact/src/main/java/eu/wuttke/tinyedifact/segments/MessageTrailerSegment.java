package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class MessageTrailerSegment extends DataSegment {

	// https://www.stylusstudio.com/edifact/40100/UNT_.htm
	// UNT+000008+00001'
	public static final String CODE = "UNT";

	public MessageTrailerSegment(int numberOfSegments, String messageReference) {
		setCode(CODE);
		getElements().add(new SimpleSegmentElement(Integer.toString(numberOfSegments)));
		getElements().add(new SimpleSegmentElement(messageReference));
	}
	
}
