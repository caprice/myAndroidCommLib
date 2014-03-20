package com.gm.driverGuard.entity;

import com.datastax.driver.core.Row;

/**
 * convert the data store's row to object
 * @author DZ5YVJ
 *
 * @param <T>
 */
public interface ObjectConverter<T> {
	T convert(Row row);
}
