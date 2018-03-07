package eu.wuttke.tinyedifact.segments;

import java.util.HashMap;
import java.util.Map;

import eu.wuttke.tinyedifact.structure.DataSegment;

public class SegmentTypeRegistry {

	public Class<? extends DataSegment> getSegmentTypeClass(String code) {
		return registry.get(code);
	}
	
	public boolean haveSpecificSegment(String code) {
		return registry.containsKey(code);
	}
	
	public void registerSegmentClass(String code, Class<? extends DataSegment> clazz) {
		registry.put(code, clazz);
	}

	private SegmentTypeRegistry() {
		registerSegmentClass(InterchangeHeaderSegment.CODE, InterchangeHeaderSegment.class);
		registerSegmentClass(InterchangeTrailerSegment.CODE, InterchangeTrailerSegment.class);
		registerSegmentClass(MessageGroupHeaderSegment.CODE, MessageGroupHeaderSegment.class);
		registerSegmentClass(MessageGroupTrailerSegment.CODE, MessageGroupTrailerSegment.class);
		registerSegmentClass(MessageHeaderSegment.CODE, MessageHeaderSegment.class);
		registerSegmentClass(MessageTrailerSegment.CODE, MessageTrailerSegment.class);
	}
	
	private Map<String, Class<? extends DataSegment>> registry = 
			new HashMap<String, Class<? extends DataSegment>>();

	public synchronized static SegmentTypeRegistry getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SegmentTypeRegistry();
		return INSTANCE;
	}
	
	private static SegmentTypeRegistry INSTANCE;
	
}
