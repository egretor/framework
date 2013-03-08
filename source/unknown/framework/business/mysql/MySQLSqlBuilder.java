package unknown.framework.business.mysql;

import java.util.List;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.FieldMap;
import unknown.framework.module.database.TableMap;
import unknown.framework.module.pojo.AbstractDatabasePojo;

/**
 * MySQL数据库SQL语句生成器
 */
public class MySQLSqlBuilder extends AbstractSqlBuilder {
	@Override
	public String getCountRowSql(String sql) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select count(*) from (");
		stringBuilder.append(sql);
		stringBuilder.append(")");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String getQuerySql(String tableName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select * from ");
		stringBuilder.append(tableName);

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String getQueryByUuidSql(String tableName, String uuidName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select * from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(uuidName);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String getInsertSqlImplement(AbstractDatabasePojo value,
			TableMap tableMap) {
		String result = null;

		StringBuilder fieldStringBuilder = new StringBuilder();
		StringBuilder parameterStringBuilder = new StringBuilder();

		List<FieldMap> fieldMaps = tableMap.getFieldMaps();
		for (int i = 0; i < fieldMaps.size(); i++) {
			FieldMap fieldMap = fieldMaps.get(i);
			String fieldName = fieldMap.getName();

			if (i > 0) {
				fieldStringBuilder.append(", ");
				parameterStringBuilder.append(", ");
			}
			fieldStringBuilder.append(fieldName);
			parameterStringBuilder.append("?");
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

		return result;
	}

	@Override
	public String getUpdateSqlImplement(AbstractDatabasePojo value,
			String uuidName, TableMap tableMap) {
		String result = null;

		StringBuilder fieldStringBuilder = new StringBuilder();

		List<FieldMap> fieldMaps = tableMap.getOthers();
		for (int i = 0; i < fieldMaps.size(); i++) {
			FieldMap fieldMap = fieldMaps.get(i);
			String fieldName = fieldMap.getName();

			if (i > 0) {
				fieldStringBuilder.append(", ");
			}
			fieldStringBuilder.append(fieldName);
			fieldStringBuilder.append(" = ?");
		}

		String tableName = tableMap.getName();

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("update ");
		stringBuilder.append(tableName);
		stringBuilder.append(" set ");
		stringBuilder.append(fieldStringBuilder.toString());
		stringBuilder.append(" where ");
		stringBuilder.append(uuidName);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}

	@Override
	public String getDeleteSql(String tableName, String uuidName) {
		String result = null;

		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("delete from ");
		stringBuilder.append(tableName);
		stringBuilder.append(" where ");
		stringBuilder.append(uuidName);
		stringBuilder.append(" = ?");

		result = stringBuilder.toString();

		return result;
	}
}
