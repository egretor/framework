package unknown.framework.module.database;

import java.util.HashMap;
import java.util.Map;

import unknown.framework.module.pojo.AbstractDatabasePojo;

/**
 * SQL语句生成器抽象类
 */
public abstract class AbstractSqlBuilder {

	/**
	 * 新增SQL语句名称
	 */
	protected final static String INSERT_SQL_NAME = "@INSERT";

	/**
	 * 修改SQL语句名称
	 */
	protected final static String UPDATE_SQL_NAME = "@UPDATE";

	/**
	 * SQL集合
	 */
	protected static Map<String, Map<String, String>> SQLS = new HashMap<String, Map<String, String>>();

	/**
	 * 获取SQL语句
	 * 
	 * @param className
	 *            类名称
	 * @param sqlName
	 *            SQL语句名称
	 * @return SQL语句
	 */
	public String getSql(String className, String sqlName) {
		String result = null;

		Map<String, String> sqls = new HashMap<String, String>();

		boolean hasClass = AbstractSqlBuilder.SQLS.containsKey(className);
		if (hasClass) {
			sqls = AbstractSqlBuilder.SQLS.get(className);
		}

		boolean hasSql = sqls.containsKey(sqlName);
		if (hasSql) {
			result = sqls.get(sqlName);
		}

		return result;
	}

	/**
	 * 赋值SQL语句
	 * 
	 * @param className
	 *            类名称
	 * @param sqlName
	 *            SQL语句名称
	 * @param sql
	 *            SQL语句
	 */
	public void setSql(String className, String sqlName, String sql) {
		Map<String, String> sqls = new HashMap<String, String>();

		boolean hasClass = AbstractSqlBuilder.SQLS.containsKey(className);
		if (hasClass) {
			sqls = AbstractSqlBuilder.SQLS.get(className);
		}
		sqls.put(sqlName, sql);
		AbstractSqlBuilder.SQLS.put(className, sqls);
	}

	/**
	 * 获取合计行数SQL语句
	 * 
	 * @param sql
	 *            原始SQL语句
	 * @return 合计行数SQL语句
	 */
	public abstract String getCountRowSql(String sql);

	/**
	 * 获取查询SQL语句
	 * 
	 * @param tableName
	 *            表名称
	 * @return 查询SQL语句
	 */
	public abstract String getQuerySql(String tableName);

	/**
	 * 获取根据代理主键查询SQL语句
	 * 
	 * @param tableName
	 *            表名称
	 * @param uuidName
	 *            代理主键名称
	 * @return 根据代理主键查询SQL语句
	 */
	public abstract String getQueryByUuidSql(String tableName, String uuidName);

	/**
	 * 获取新增SQL语句实现
	 * 
	 * @param value
	 *            实体对象
	 * @param tableMap
	 *            表映射
	 * @return 新增SQL语句
	 */
	protected abstract String getInsertSqlImplement(AbstractDatabasePojo value,
			TableMap tableMap);

	/**
	 * 获取新增SQL语句
	 * 
	 * @param value
	 *            实体对象
	 * @param tableMap
	 *            表映射
	 * @return 新增SQL语句
	 */
	public String getInsertSql(AbstractDatabasePojo value, TableMap tableMap) {
		String result = null;

		String className = value.getClass().getName();
		String sqlName = AbstractSqlBuilder.INSERT_SQL_NAME;

		result = this.getSql(className, sqlName);
		if (result == null) {
			result = this.getInsertSqlImplement(value, tableMap);
			this.setSql(className, sqlName, result);
		}

		return result;
	}

	/**
	 * 获取更新SQL语句实现
	 * 
	 * @param value
	 *            实体对象
	 * @param uuidName
	 *            代理主键名称
	 * @param tableMap
	 *            表映射
	 * @return 更新SQL语句
	 */
	protected abstract String getUpdateSqlImplement(AbstractDatabasePojo value,
			String uuidName, TableMap tableMap);

	/**
	 * 获取更新SQL语句
	 * 
	 * @param value
	 *            实体对象
	 * @param uuidName
	 *            代理主键名称
	 * @param tableMap
	 *            表映射
	 * @return 更新SQL语句
	 */
	public String getUpdateSql(AbstractDatabasePojo value, String uuidName,
			TableMap tableMap) {
		String result = null;

		String className = value.getClass().getName();
		String sqlName = AbstractSqlBuilder.UPDATE_SQL_NAME;

		result = this.getSql(className, sqlName);
		if (result == null) {
			result = this.getUpdateSqlImplement(value, uuidName, tableMap);
			this.setSql(className, sqlName, result);
		}

		return result;
	}

	/**
	 * 获取删除SQL语句
	 * 
	 * @param tableName
	 *            表名称
	 * @param uuidName
	 *            代理主键名称
	 * @return 删除SQL语句
	 */
	public abstract String getDeleteSql(String tableName, String uuidName);
}
