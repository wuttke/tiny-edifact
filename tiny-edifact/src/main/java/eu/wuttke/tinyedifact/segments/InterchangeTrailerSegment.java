package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class InterchangeTrailerSegment 
extends DataSegment {
	
	public static final String CODE = "UNZ";

	public InterchangeTrailerSegment() {
	}
	
	public InterchangeTrailerSegment(int interchangeControlCount, 
			String interchangeControlReference) {
		setCode(CODE);
		getElements().add(new SimpleSegmentElement(Integer.toString(interchangeControlCount)));
		getElements().add(new SimpleSegmentElement(interchangeControlReference));
	}
	
}
