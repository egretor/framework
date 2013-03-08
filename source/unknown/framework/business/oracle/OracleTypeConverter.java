package unknown.framework.business.oracle;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

import unknown.framework.module.annotation.Types;
import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.FieldMap;
import unknown.framework.utility.Trace;

/**
 * Oracle数据库类型转换器
 */
public class OracleTypeConverter extends AbstractTypeConverter {

	@Override
	public Object getJava(FieldMap fieldMap, Object value) {
		Object result = value;

		if (value != null) {
			Types type = fieldMap.getType();
			switch (type) {
			case BOOLEAN:
				BigDecimal bigDecimalValue = (BigDecimal) value;
				if (bigDecimalValue.intValue() == 0) {
					result = false;
				} else {
					result = true;
				}
				break;
			case DATE:
				TIMESTAMP timestampValue = (TIMESTAMP) value;
				try {
					result = timestampValue.dateValue();
				} catch (SQLException e) {
					Trace.getFramework().error(e);
				}
				break;
			case TEXT:
				CLOB clobValue = (CLOB) value;
				try {
					result = clobValue.stringValue();
				} catch (SQLException e) {
					Trace.getFramework().error(e);
				}
				break;
			default:
				break;
			}
		}

		return result;
	}

	@Override
	public Object getDatabase(Object value) {
		Object result = value;

		if (value != null) {
			Class<?> classType = value.getClass();
			boolean once = true;
			while (once) {
				once = false;

				if (classType.equals(java.util.Date.class)) {
					java.util.Date dateValue = (java.util.Date) value;
					result = new Timestamp(dateValue.getTime());
					break;
				}
			}
		}

		return result;
	}

}
