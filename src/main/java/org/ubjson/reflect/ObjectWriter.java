package org.ubjson.reflect;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

import org.ubjson.io.UBJOutputStream;

/*
 * TODO: For method caching, might speed up access time if the Method arg we store
 * is the tuple of the method AND the shortened name so we don't have to process
 * each time.
 * 
 * TODO: This and IReflectWriter could probably be merged in some way...
 */
public class ObjectWriter implements IObjectWriter {
	private IReflectWriter<Field> fieldWriter;

	@Override
	public void writeObject(UBJOutputStream out, Object obj)
			throws IllegalArgumentException, IOException {
		writeObject(out, obj, AccessType.FIELDS);
	}

	@Override
	public void writeObject(UBJOutputStream out, Object obj, AccessType type)
			throws IllegalArgumentException, IOException {
		if (out == null)
			throw new IllegalArgumentException("out cannot be null");
		if (obj == null)
			throw new IllegalArgumentException("obj cannot be null");
		if (type == null)
			throw new IllegalArgumentException("type cannot be null");

		Class<?> clazz = obj.getClass();

		// Base-line sanity check.
		if (!Serializable.class.isAssignableFrom(clazz))
			throw new IllegalArgumentException(
					"Root object 'obj' does not implement Serializable; unable to reflect into the first level of the instance to retrieve values for processing.");

		if (type == AccessType.FIELDS) {
			if (fieldWriter == null)
				fieldWriter = new FieldReflectWriter();

			fieldWriter.dispatchWrite(out, null, obj);
		} else if (type == AccessType.METHODS) {
			// TODO: implement
		}
	}
}