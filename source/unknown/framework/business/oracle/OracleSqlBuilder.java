package unknown.framework.business.oracle;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.BreakIterator;
import java.util.Date;
import java.util.List;

import oracle.sql.CLOB;
import oracle.sql.TIMESTAMP;

import org.apache.naming.java.javaURLContextFactory;

import unknown.framework.business.database.AbstractSqlBuilder;
import unknown.framework.business.database.Convention;
import unknown.framework.module.pojo.AbstractDatabasePojo;

public class OracleSqlBuilder extends AbstractSqlBuilder {
	/**
	 * 合计别名
	 */
	protected final static String TABLE_COUNT_ALIAS = "count__alias";

	@Override
	public String rowCount(String sql) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select count(*) from (");
		stringBuilder.append(sql);
		stringBuilder.append(") as ");
		stringBuilder.append(OracleSqlBuilder.TABLE_COUNT_ALIAS);

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String query(String tableName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from ");
		stringBuilder.append(tableName);

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String queryByUuid(String tableName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(AbstractDatabasePojo.UUID);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String insertImplement(AbstractDatabasePojo value) {
		String result = null;

		Convention convention = new Convention();
		List<Method> properties = convention.filterGetMethod(value);

		if (properties.size() > 0) {
			StringBuilder fieldStringBuilder = new StringBuilder();
			StringBuilder parameterStringBuilder = new StringBuilder();
			int index = 0;
			for (int i = 0; i < properties.size(); i++) {
				Method property = properties.get(i);
				String propertyName = convention.decodeGetMethodName(property
						.getName());
				String fieldName = convention.decodeName(propertyName);

				if (index > 0) {
					fieldStringBuilder.append(", ");
					parameterStringBuilder.append(", ");
				}
				fieldStringBuilder.append(fieldName);
				parameterStringBuilder.append("?");
				index++;
			}

			String tableName = convention.decodeName(value.getClass()
					.getSimpleName());

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("insert into ");
			stringBuilder.append(tableName);
			stringBuilder.append(" (");
			stringBuilder.append(fieldStringBuilder.toString());
			stringBuilder.append(" ) values (");
			stringBuilder.append(parameterStringBuilder.toString());
			stringBuilder.append(" )");

			result = stringBuilder.toString();
		}

		return result;
	}

	@Override
	public String updateImplement(AbstractDatabasePojo value) {
		String result = null;

		Convention convention = new Convention();
		List<Method> properties = convention.filterGetMethod(value);

		if (properties.size() > 0) {
			StringBuilder fieldStringBuilder = new StringBuilder();
			int index = 0;
			for (int i = 0; i < properties.size(); i++) {
				Method property = properties.get(i);
				String propertyName = convention.decodeGetMethodName(property
						.getName());
				String fieldName = convention.decodeName(propertyName);

				if (!AbstractDatabasePojo.UUID.equalsIgnoreCase(fieldName)) {
					if (index > 0) {
						fieldStringBuilder.append(", ");
					}
					fieldStringBuilder.append(fieldName);
					fieldStringBuilder.append(" = ?");
					index++;
				}
			}

			String tableName = convention.decodeName(value.getClass()
					.getSimpleName());

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("update ");
			stringBuilder.append(tableName);
			stringBuilder.append(" set ");
			stringBuilder.append(fieldStringBuilder.toString());
			stringBuilder.append(" where ");
			stringBuilder.append(AbstractDatabasePojo.UUID);
			stringBuilder.append(" = ?");

			result = stringBuilder.toString();
		}

		return result;
	}

	@Override
	public String delete(String tableName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(AbstractDatabasePojo.UUID);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public Object Encode(Class<?> classType, Object value) {
		Object result = null;

		if (value != null) {
			if (classType.equals(Boolean.class)
					|| classType.equals(boolean.class)) {
				BigDecimal sourceValue = (BigDecimal) value;
				if (sourceValue.intValue() == 0) {
					result = false;
				} else {
					result = true;
				}
			} else if (classType.equals(java.util.Date.class)) {
				oracle.sql.TIMESTAMP sourceTimestampValue = (TIMESTAMP) value;
				try {
					result = sourceTimestampValue.dateValue();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				if (value.getClass().equals(oracle.sql.CLOB.class)) {
					CLOB sourceClobValue = (CLOB) value;
					try {
						result = sourceClobValue.stringValue();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					result = value;
				}
			}
		}

		return result;
	}

	@Override
	public Object Decode(Object value) {
		Object result = null;

		if (value != null) {
			Class<?> classType = value.getClass();

			int count = 1;
			int times = 0;
			while (times < count) {
				if (classType.equals(java.lang.String.class)) {
					break;
				}
				if (classType.equals(java.lang.Integer.class)) {
					break;
				}
				if (classType.equals(java.lang.Long.class)) {
					break;
				}
				if (classType.equals(java.lang.Double.class)) {
					break;
				}
				if (classType.equals(java.lang.Boolean.class)) {
					break;
				}
				if (classType.equals(java.util.Date.class)) {
					break;
				}
				if (classType.equals(int.class)) {
					break;
				}
				if (classType.equals(long.class)) {
					break;
				}
				if (classType.equals(double.class)) {
					break;
				}
				times++;
			}

			if (value.getClass().equals(java.util.Date.class)) {
				Date sourceValue = (Date) value;
				result = new Timestamp(sourceValue.getTime());
			} else {
				result = value;
			}
		}

		return result;
	}
}