package eu.wuttke.tinyedifact.exchange;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.messages.Interchange;
import eu.wuttke.tinyedifact.messages.Message;
import eu.wuttke.tinyedifact.messages.MessageFactory;
import eu.wuttke.tinyedifact.messages.MessageGroup;
import eu.wuttke.tinyedifact.segments.InterchangeHeaderSegment;
import eu.wuttke.tinyedifact.segments.InterchangeTrailerSegment;
import eu.wuttke.tinyedifact.segments.MessageGroupHeaderSegment;
import eu.wuttke.tinyedifact.segments.MessageGroupTrailerSegment;
import eu.wuttke.tinyedifact.segments.MessageHeaderSegment;
import eu.wuttke.tinyedifact.segments.MessageTrailerSegment;
import eu.wuttke.tinyedifact.segments.SegmentFactory;
import eu.wuttke.tinyedifact.segments.SegmentTypeRegistry;
import eu.wuttke.tinyedifact.serialization.EdifactDeserializer;
import eu.wuttke.tinyedifact.structure.DataSegment;

public class InterchangeParser {

	public Interchange parseInterchange(String text) throws MessageFormatException {
		EdifactDeserializer deserializer = new EdifactDeserializer();
		List<DataSegment> segments = deserializer.parseSegments(text);
		segments = qualifySegments(segments);
		
		Interchange i = new Interchange();
		
		if (!(segments.get(0) instanceof InterchangeHeaderSegment))
			throw new MessageFormatException("expect interchange header");		
		InterchangeHeaderSegment ihs = (InterchangeHeaderSegment)segments.remove(0);
		i.setInterchangeHeader(ihs);
		
		DataSegment lastSegment = segments.get(segments.size() - 1);
		if (!(lastSegment instanceof InterchangeTrailerSegment))
			throw new MessageFormatException("did not find interchange trailer");
		InterchangeTrailerSegment its = (InterchangeTrailerSegment)lastSegment;
		segments.remove(its);
		i.setInterchangeTrailer(its);
		
		MessageGroupHeaderSegment anyHeader = findSegmentByType(segments, MessageGroupHeaderSegment.class);
		if (anyHeader != null) {
			i.setMessageGroups(new LinkedList<MessageGroup>());
			parseMessageGroups(i, segments);
		} else {
			i.setMessages(new LinkedList<Message>());
			parseMessages(i.getMessages(), segments);
		}
		
		return i;
	}

	@SuppressWarnings("unchecked")
	private <T> T findSegmentByType(List<DataSegment> segments,
			Class<T> candidateClass) {
		for (DataSegment segment : segments)
			if (segment.getClass().equals(candidateClass))
				return (T)segment;
		return null;
	}

	private void parseMessages(List<Message> messages, List<DataSegment> segments) 
	throws MessageFormatException {
		Message message = null;
		for (DataSegment segment : segments) {
			if (segment instanceof MessageHeaderSegment) {
				if (message != null)
					throw new MessageFormatException("invalid interchange structure: did not expect another message header");
				MessageHeaderSegment mh = (MessageHeaderSegment)segment;
				message = MessageFactory.createMessage(mh.getCode());
				message.setMessageHeaderSegment(mh);
				messages.add(message);
			} else if (segment instanceof MessageTrailerSegment) {
				if (message == null)
					throw new MessageFormatException("invalid interchange structure: did not expect another message trailer");
				message.setMessageTrailerSegment((MessageTrailerSegment)segment);
				message = null;
			} else {
				if (message == null)
					throw new MessageFormatException("invalid interchange structure: message header missing");
				message.getSegments().add(segment);
			}
		}
		
		if (message != null)
			throw new MessageFormatException("invalid interchange structure: message trailer missing");
	}

	private void parseMessageGroups(Interchange i, List<DataSegment> segments) 
	throws MessageFormatException {
		MessageGroup group = null;
		List<DataSegment> messageSegments = new LinkedList<DataSegment>();
		
		for (DataSegment segment : segments) {
			if (segment instanceof MessageGroupHeaderSegment) {
				if (group != null)
					throw new MessageFormatException("invalid interchange structure: did not expect another group header");
				group = new MessageGroup();
				group.setFunctionalGroupHeader((MessageGroupHeaderSegment)segment);
				i.getMessageGroups().add(group);
			} else if (segment instanceof MessageGroupTrailerSegment) {
				if (group == null)
					throw new MessageFormatException("invalid interchange structure: did not expect another group trailer");
				group.setFunctionalGroupTrailer((MessageGroupTrailerSegment)segment);
				parseMessages(group.getMessages(), messageSegments);
				group = null;
				messageSegments.clear();
			} else {
				if (group == null)
					throw new MessageFormatException("invalid interchange structure: group header missing");
				messageSegments.add(segment);
			}
		}
		
		if (group != null)
			throw new MessageFormatException("invalid interchange structure: group trailer missing");
		if (messageSegments.size() > 0)
			throw new MessageFormatException("invalid interchange structure: remaining segments");
	}

	private List<DataSegment> qualifySegments(List<DataSegment> segments) {
		List<DataSegment> result = new LinkedList<DataSegment>();
		SegmentTypeRegistry r = SegmentTypeRegistry.getInstance();
		for (DataSegment segment : segments) {
			DataSegment seg = segment;
			if (r.haveSpecificSegment(segment.getCode())) {
				seg = SegmentFactory.createDataSegment(segment.getCode());
				seg.setValue(segment.getValue());
				seg.setElements(segment.getElements());
			}
			result.add(seg);
		}
		return result;
	}
	
}
