package unknown.framework.module.pojo;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 数据库实体抽象类
 */
public abstract class AbstractDatabasePojo {
	public final static String UUID = "uuid";

	@Type(value = Types.Uuid, major = true)
	@Remark(value = "编号")
	protected String uuid;

	/**
	 * 编号
	 * 
	 * @return 编号
	 */
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
