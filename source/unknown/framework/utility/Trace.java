package unknown.framework.utility;

import org.apache.log4j.Logger;

import unknown.framework.business.database.AbstractDatabase;

/**
 * 跟踪类
 */
public class Trace {
	/**
	 * 框架日志器
	 * 
	 * @return 框架日志器
	 */
	public final static Logger getFramework() {
		return Logger.getLogger(Trace.class);
	}

	/**
	 * 数据库日志器
	 * 
	 * @return 数据库日志器
	 */
	public final static Logger getDatabase() {
		return Logger.getLogger(AbstractDatabase.class);
	}
}
