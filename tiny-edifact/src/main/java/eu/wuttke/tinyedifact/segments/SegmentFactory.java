package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.DataSegment;

public class SegmentFactory {
	
	public static DataSegment createDataSegment(String code) {
		try {
			SegmentTypeRegistry registry = SegmentTypeRegistry.getInstance();
			DataSegment segment;
			Class<? extends DataSegment> clazz = registry.getSegmentTypeClass(code);
			if (clazz == null)
				segment = new DataSegment();
			else
				segment = clazz.newInstance();
			segment.setCode(code);
			return segment;
		} catch (InstantiationException ie) {
			throw new RuntimeException(ie);
		} catch (IllegalAccessException ia) {
			throw new RuntimeException(ia);
		}
	}
	
}
