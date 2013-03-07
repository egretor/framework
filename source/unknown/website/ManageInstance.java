package unknown.website;

import unknown.framework.business.mysql.MySQLSqlBuilder;
import unknown.framework.business.mysql.MySQLTypeConverter;
import unknown.framework.business.oracle.OracleSqlBuilder;
import unknown.framework.business.oracle.OracleTypeConverter;
import unknown.framework.module.database.Instance;
import unknown.website.manage.business.ManageUserBusiness;

/**
 * 管理实例
 */
public final class ManageInstance {
	public final static String NAME = "ManageInstanceName";
	public final static String MYSQL = "ManageInstance.MySQL";
	public final static String ORACLE = "ManageInstance.Oracle";

	private static Instance instance;

	/**
	 * 实例
	 * 
	 * @return 实例
	 */
	public static Instance getInstance() {
		return instance;
	}

	public static void setInstance(Instance instance) {
		ManageInstance.instance = instance;
	}

	private static void initializeOracle() {
		ManageInstance.instance.setIdentifierCapital(true);
		ManageInstance.instance.setTypeConverter(new OracleTypeConverter());
		ManageInstance.instance.setSqlBuilder(new OracleSqlBuilder());

		ManageInstance.instance.setSql(ManageUserBusiness.QUERY_BY_ACCOUNT,
				"select * from MANAGE_USER where ACCOUNT = ?");
	}

	private static void initializeMySQL() {
		ManageInstance.instance.setIdentifierCapital(false);
		ManageInstance.instance.setTypeConverter(new MySQLTypeConverter());
		ManageInstance.instance.setSqlBuilder(new MySQLSqlBuilder());
	}

	public static void initialize(String name, Instance oracle, Instance mySQL) {
		if (name.equals(ManageInstance.ORACLE)) {
			ManageInstance.setInstance(oracle);
			ManageInstance.initializeOracle();
		}
		if (name.equals(ManageInstance.MYSQL)) {
			ManageInstance.setInstance(mySQL);
			ManageInstance.initializeMySQL();
		}
	}
}
