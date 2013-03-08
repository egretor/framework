package unknown.website.manage.database;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.Instance;
import unknown.website.manage.business.ManageUserBusiness;

/**
 * Oracle数据库SQL语句
 */
public class OracleSql {
	/**
	 * 初始化
	 * 
	 * @param instance
	 *            数据库实例
	 */
	public final static void initialize(Instance instance) {
		AbstractSqlBuilder sqlBuilder = instance.getSqlBuilder();
		
		// (((? is not null) and (field = ?)) or (? is null))
		
		// ManageUserBusiness
		sqlBuilder.setSql(ManageUserBusiness.class.getName(),
				ManageUserBusiness.QUERY_BY_ACCOUNT,
				"select * from MANAGE_USER where lower(ACCOUNT) = lower(?)");
	}
}
