package unknown.website;

import unknown.framework.module.database.Instance;
import unknown.website.manage.database.OracleSql;

/**
 * 网站
 */
public final class Website {
	/**
	 * 数据库实例名称
	 */
	public final static String INSTANCE_NAME = "WebsiteInstance";

	/**
	 * 数据库实例
	 */
	private static Instance INSTANCE;

	public static Instance getInstance() {
		return INSTANCE;
	}

	public static void setInstance(Instance instance) {
		int index = 0;
		boolean once = true;

		while (once) {
			once = false;
			index = instance.getUrl().toLowerCase().indexOf("jdbc:oracle");
			if (index == 0) {
				OracleSql.initialize(instance);
				break;
			}
		}

		Website.INSTANCE = instance;
	}
}
