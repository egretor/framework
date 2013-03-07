package unknown.framework.module.database;

import unknown.framework.module.pojo.FieldMap;

/**
 * 类型转换类
 */
public abstract class AbstractTypeConverter {
	public abstract Object java(FieldMap fieldMap, Object value);

	public abstract Object database(Object value);
}
