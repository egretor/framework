package unknown.framework.business.oracle;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.pojo.FieldMap;

public class OracleTypeConverter extends AbstractTypeConverter {

	@Override
	public Object java(FieldMap fieldMap, Object value) {
		Object result = value;

		if (value != null) {
			switch (fieldMap.getType()) {
			case Boolean:
				BigDecimal bigDecimalValue = (BigDecimal) value;
				if (bigDecimalValue.intValue() == 0) {
					result = false;
				} else {
					result = true;
				}
				break;
			case Date:
				TIMESTAMP timestampValue = (TIMESTAMP) value;
				try {
					result = timestampValue.dateValue();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case Text:
				CLOB clobValue = (CLOB) value;
				try {
					result = clobValue.stringValue();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}

		return result;
	}

	@Override
	public Object database(Object value) {
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
