package unknown.framework.business.mysql;

import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.pojo.FieldMap;

public class MySQLTypeConverter extends AbstractTypeConverter {

	@Override
	public Object java(FieldMap fieldMap, Object value) {
		return value;
	}

	@Override
	public Object database(Object value) {
		return value;
	}

}
