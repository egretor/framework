package unknown.website;

import unknown.framework.business.mysql.MySQLSqlBuilder;
import unknown.framework.business.oracle.OracleSqlBuilder;
import unknown.framework.module.database.Instance;

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
		ManageInstance.instance.setCapitalSQL(true);
		ManageInstance.instance.setSqlBuilder(new OracleSqlBuilder());
	}

	private static void initializeMySQL() {
		ManageInstance.instance.setCapitalSQL(false);
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
