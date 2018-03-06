package eu.wuttke.tinyedifact.serialization;

import java.util.LinkedList;
import java.util.List;

import eu.wuttke.tinyedifact.structure.CompositeSegmentElement;
import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SegmentElement;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class EdifactDeserializer {
	
	public List<DataSegment> parseSegments(String str) {
		StringBuffer text = new StringBuffer(str);
		EdifactSeparators separators = parseSeparators(text);
		
		EdifactTokenizer tokenizer = new EdifactTokenizer();
		List<EdifactToken> tokens = tokenizer.getTokens(text.toString(), separators);
		List<DataSegment> segments = convertTokensToSegments(tokens);
		return segments;
	}

	/*
	public Interchange parseInterchangeAndSeparators(String str) {
		List<DataSegment> segments = parseSegments(str);
		return convertSegmentsToInterchange(segments);
	}*/

	private List<DataSegment> convertTokensToSegments(List<EdifactToken> tokens) {
		List<DataSegment> segments = new LinkedList<DataSegment>();
        int currentSegment = -1;
        int part = 0, key = 0;
        boolean inSegment = false;
        
        for (EdifactToken token : tokens) {
            // If we're in the middle of a segment, check if we've reached the end
            if (inSegment) {
                if (token.getType() == EdifactTokenType.SegmentTerminator) {
                    inSegment = false;
                    continue;
                }
            // If we're not in a segment, then start a new one now
            } else {
                inSegment = true;
                currentSegment++;
                segments.add(new DataSegment());
                part = 0;
                key = 0;
            }
            
            /**
             * Whenever we reach a data separator, we increment the $part counter to
             * move on to the next part of data, and reset our $key counter for
             * the elements within the part.
             */
            if (token.getType() == EdifactTokenType.DataElementSeparator) {
                part++;
                key = 0;
                continue;
            }
            
            /**
             * Whenever we reach a component separator, we just increment the $key
             * counter for the elements within the current part.
             */
            if (token.getType() == EdifactTokenType.ComponentDataElementSeparator) {
                key++;
                continue;
            }
            
            /**
             * If this isn't the first part, then backfill any missing parts.
             * This is because empty parts are not represented by a token,
             * so we need to simulate them here.
             */
            if (part > 0) {
                for (int i = 0; i < part; i++) {
                	if (segments.get(currentSegment).getElements().size() < i + 1) {
                        segments.get(currentSegment).getElements().add(
                        		new SimpleSegmentElement(null));
                    }
                }
            }
            
            // If this is the first element within the part then just set it as a string.
            if (key == 0) {
                segments.get(currentSegment).getElements().add(
                		new SimpleSegmentElement(token.getValue()));
                continue;
            }
            
            /**
             * For the same as the parts, we need to backfill any empty elements.
             * We also use this code to append the element we are currently processing.
             */
            for (int i = 0; i <= key; i++) {
                String value = (i == key) ? token.getValue() : "";
                
                // If there is an initial element set as a string, we need to convert it into an array before we append to it
                SegmentElement ele = segments.get(currentSegment).getElements().get(part);
                if (ele instanceof SimpleSegmentElement) {
                	ele = new CompositeSegmentElement(((SimpleSegmentElement)ele).getValue());
                	segments.get(currentSegment).getElements().remove(part);
                	segments.get(currentSegment).getElements().add(ele);
                }
                	
                // If this part does not exist, set it now
                CompositeSegmentElement cse = (CompositeSegmentElement)ele;
                if (cse.getValues().size() < i + 1)
                	cse.getValues().add(value);
            }
        }
        
        for (DataSegment seg : segments) {
        	SegmentElement ele = seg.getElements().remove(0);
        	if (ele instanceof SimpleSegmentElement)
        		seg.setCode(((SimpleSegmentElement)ele).getValue());
        	else {
        		CompositeSegmentElement cse = (CompositeSegmentElement)ele;
        		seg.setCode(cse.getValues().get(0));
        		seg.setValue(cse.getValues().get(1));
        	}
        }
		
        return segments;
	}

	private EdifactSeparators parseSeparators(StringBuffer text) {
		if (text.toString().startsWith("UNA") && text.length() > 9) {
			String advice = text.substring(3, 9);
			
			// remove UNA segment
			text.delete(0, 9);
			while (Character.isWhitespace(text.charAt(0)))
				text.deleteCharAt(0);
			
			return EdifactSeparators.parseServiceStringAdvice(advice);
		} else
			return new EdifactSeparators();
	}
	
}
