package eu.wuttke.tinyedifact.segments;

import java.util.Date;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;
import eu.wuttke.tinyedifact.util.DateUtil;

public class MessageGroupHeaderSegment extends DataSegment {

	public static final String CODE = "UNG";

	// https://www.stylusstudio.com/edifact/40100/UNG_.htm
	public MessageGroupHeaderSegment(String groupIdent, String senderIdentification,
			String recipientIdentification, Date dateTimePreparation, 
			String groupReference) {
		setCode(CODE);
		
		if (groupIdent != null)
			getElements().add(new SimpleSegmentElement(groupIdent));
		else
			getElements().add(new SimpleSegmentElement(null));
		
		// Sender
		if (senderIdentification != null)
			getElements().add(new CompositeSegmentElement(senderIdentification));
		else
			getElements().add(new CompositeSegmentElement());
		
		// Recipient
		if (recipientIdentification != null)
			getElements().add(new CompositeSegmentElement(recipientIdentification));
		else
			getElements().add(new CompositeSegmentElement());
		
		// Preparation Date/Time
		if (dateTimePreparation != null) {
			getElements().add(new CompositeSegmentElement(
					DateUtil.formatShortDate(dateTimePreparation),
					DateUtil.formatShortTime(dateTimePreparation)));
		} else
			getElements().add(new SimpleSegmentElement(null));
		
		getElements().add(new SimpleSegmentElement(groupReference));
	}

}
