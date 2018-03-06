package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class InterchangeTrailerSegment 
extends DataSegment {
	
	public InterchangeTrailerSegment(int interchangeControlCount, 
			String interchangeControlReference) {
		setCode("UNZ");
		getElements().add(new SimpleSegmentElement(Integer.toString(interchangeControlCount)));
		getElements().add(new SimpleSegmentElement(interchangeControlReference));
	}
	
}
