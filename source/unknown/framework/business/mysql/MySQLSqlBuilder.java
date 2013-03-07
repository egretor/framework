package unknown.framework.business.mysql;

import java.util.List;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.pojo.AbstractDatabasePojo;
import unknown.framework.module.pojo.FieldMap;
import unknown.framework.module.pojo.TableMap;

/**
 * MySQL SQL生成器
 */
public class MySQLSqlBuilder extends AbstractSqlBuilder {
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
		stringBuilder.append(MySQLSqlBuilder.TABLE_COUNT_ALIAS);

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
	public String queryByUuid(String tableName, String uuidField) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select * from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(uuidField);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String insertImplement(AbstractDatabasePojo value, TableMap tableMap) {
		String result = null;

		List<FieldMap> fieldMaps = tableMap.getFields();

		if (fieldMaps != null)
			if (fieldMaps.size() > 0) {
				StringBuilder fieldStringBuilder = new StringBuilder();
				StringBuilder parameterStringBuilder = new StringBuilder();
				int index = 0;
				for (int i = 0; i < fieldMaps.size(); i++) {
					FieldMap fieldMap = fieldMaps.get(i);
					String fieldName = fieldMap.getName();

					if (index > 0) {
						fieldStringBuilder.append(", ");
						parameterStringBuilder.append(", ");
					}
					fieldStringBuilder.append(fieldName);
					parameterStringBuilder.append("?");
					index++;
				}

				String tableName = tableMap.getName();

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
	public String updateImplement(AbstractDatabasePojo value, String uuidField, TableMap tableMap) {
		String result = null;

		List<FieldMap> fieldMaps = tableMap.getOthers();

		StringBuilder fieldStringBuilder = new StringBuilder();
		int index = 0;
		for (int i = 0; i < fieldMaps.size(); i++) {
			FieldMap fieldMap = fieldMaps.get(i);
			String fieldName = fieldMap.getName();

				if (index > 0) {
					fieldStringBuilder.append(", ");
				}
				fieldStringBuilder.append(fieldName);
				fieldStringBuilder.append(" = ?");
				index++;
		}

		String tableName = tableMap.getName();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("update ");
		stringBuilder.append(tableName);
		stringBuilder.append(" set ");
		stringBuilder.append(fieldStringBuilder.toString());
		stringBuilder.append(" where ");
		stringBuilder.append(uuidField);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String delete(String tableName, String uuidField) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("delete from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(uuidField);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public Object Encode(Class<?> classType, Object value) {
		Object result = null;

		if (value != null) {
			result = value;
		}

		return result;
	}

	@Override
	public Object Decode(Object value) {
		Object result = null;

		if (value != null) {
			result = value;
		}

		return result;
	}
}
