package unknown.framework.module.database;

/**
 * 数据库实例类
 */
public class Instance {
	/**
	 * 驱动类
	 */
	private String driver;
	/**
	 * 链接URL
	 */
	private String url;
	/**
	 * 用户
	 */
	private String user;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 标识符大写
	 */
	private boolean identifierCapital;
	/**
	 * 类型转换器
	 */
	private AbstractTypeConverter typeConverter;
	/**
	 * SQL语句生成器
	 */
	private AbstractSqlBuilder sqlBuilder;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIdentifierCapital() {
		return identifierCapital;
	}

	public void setIdentifierCapital(boolean identifierCapital) {
		this.identifierCapital = identifierCapital;
	}

	public AbstractTypeConverter getTypeConverter() {
		return typeConverter;
	}

	public void setTypeConverter(AbstractTypeConverter typeConverter) {
		this.typeConverter = typeConverter;
	}

	public AbstractSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(AbstractSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}
}
