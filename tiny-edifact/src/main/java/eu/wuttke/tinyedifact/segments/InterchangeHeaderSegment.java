package eu.wuttke.tinyedifact.segments;

import java.util.Date;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;
import eu.wuttke.tinyedifact.util.DateUtil;

public class InterchangeHeaderSegment 
extends DataSegment {
	
	public static final String CODE = "UNB";
	
	public InterchangeHeaderSegment() {
	}
	
	public InterchangeHeaderSegment(String senderIdentification,
			String recipientIdentification,
			Date dateTimePreparation,
			String controlReference,
			String recipientReference,
			String applicationReference) {
		setCode(CODE);
		getElements().add(new CompositeSegmentElement("UNOC", "3"));
		getElements().add(new CompositeSegmentElement(senderIdentification));
		getElements().add(new CompositeSegmentElement(recipientIdentification));
		getElements().add(new CompositeSegmentElement(
				DateUtil.formatShortDate(dateTimePreparation),
				DateUtil.formatShortTime(dateTimePreparation)));
		getElements().add(new SimpleSegmentElement(controlReference));
		getElements().add(new SimpleSegmentElement(recipientReference));
		if (applicationReference != null)
			getElements().add(new SimpleSegmentElement(applicationReference));
	}
	
}
