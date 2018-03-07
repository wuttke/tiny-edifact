package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class MessageHeaderSegment extends DataSegment {

	// https://www.stylusstudio.com/edifact/40100/UNH_.htm
	// UNH+00001+KOTR:01:001:KV'
	public static final String CODE = "UNH";

	public MessageHeaderSegment(String messageReference,
			String messageType, String versionNumber,
			String messageReleaseNumber, String controllingAgency) {
		setCode(CODE);
		getElements().add(new SimpleSegmentElement(messageReference));
		getElements().add(new CompositeSegmentElement(
				messageType, versionNumber, messageReleaseNumber, controllingAgency));
	}
	
	public String getMessageType() {
		return getValue(1, 0);
	}
	
}
