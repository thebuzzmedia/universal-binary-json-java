package org.ubjson.model;

import static org.ubjson.io.IMarkerType.FLOAT;

import java.io.IOException;

import org.ubjson.io.DataFormatException;
import org.ubjson.io.UBJOutputStream;
import org.ubjson.io.parser.UBJInputStreamParser;

public class FloatValue extends AbstractValue<Float> {
	public FloatValue() {
		// default
	}
	
	public FloatValue(Float f) {
		value = f;
	}

	public FloatValue(UBJInputStreamParser in) throws IOException,
			DataFormatException {
		deserialize(in);
	}

	@Override
	public byte getType() {
		return FLOAT;
	}

	@Override
	public void serialize(UBJOutputStream out) throws IOException {
		out.writeFloat(value);
	}

	@Override
	public void deserialize(UBJInputStreamParser in) throws IOException,
			DataFormatException {
		value = in.readFloat();
	}
}