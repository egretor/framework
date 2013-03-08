package unknown.framework.business.mysql;

import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.FieldMap;

/**
 * MySQL数据库类型转换器
 */
public class MySQLTypeConverter extends AbstractTypeConverter {

	@Override
	public Object getJava(FieldMap fieldMap, Object value) {
		return value;
	}

	@Override
	public Object getDatabase(Object value) {
		return value;
	}

}
