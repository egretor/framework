package unknown.framework.module.pojo;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 数据库实体抽象类
 */
public abstract class AbstractDatabasePojo {
	public final static String UUID = "UUID";

	/**
	 * 代理主键
	 */
	@Type(value = Types.UUID, major = true)
	protected String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
