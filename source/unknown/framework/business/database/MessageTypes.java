package unknown.framework.business.database;

/**
 * 消息类型
 */
public enum MessageTypes {
	/**
	 * 失败
	 */
	FAIL,
	/**
	 * 成功
	 */
	SUCCESS,
	/**
	 * 存在相同数据
	 */
	HAS_SAME,
	/**
	 * 存在引用数据
	 */
	HAS_REFERENCE
}
