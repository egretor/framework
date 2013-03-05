package unknown.framework.module.database;

import java.util.HashMap;
import java.util.Map;

import unknown.framework.business.database.AbstractSqlBuilder;

/**
 * 数据库实例
 */
public class Instance {
	private String name;
	private String url;
	private String user;
	private String password;
	private boolean capitalSQL;
	private AbstractSqlBuilder sqlBuilder;
	private Map<String, String> sqls = new HashMap<String, String>();

	/**
	 * 名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 连接Url
	 * 
	 * @return 连接Url
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 用户
	 * 
	 * @return 用户
	 */
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 密码
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * SQL大写
	 * 
	 * @return 是否
	 */
	public boolean isCapitalSQL() {
		return capitalSQL;
	}

	public void setCapitalSQL(boolean capitalSQL) {
		this.capitalSQL = capitalSQL;
	}

	/**
	 * SQL生成器
	 * 
	 * @return SQL生成器
	 */
	public AbstractSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(AbstractSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}

	/**
	 * 获取SQL
	 * 
	 * @param key
	 *            键
	 * @return SQL
	 */
	public String getSql(String key) {
		String result = null;

		if (this.sqls.containsKey(key)) {
			result = this.sqls.get(key);
		}

		return result;
	}

	/**
	 * 赋值SQL
	 * 
	 * @param key
	 *            键
	 * @param sql
	 *            SQL
	 */
	public void setSql(String key, String sql) {
		if (this.sqls.containsKey(key)) {
			this.sqls.remove(key);
		}
		this.sqls.put(key, sql);
	}
}
