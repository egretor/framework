package unknown.website.manage.module;

import java.text.SimpleDateFormat;
import java.util.Date;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;
import unknown.framework.module.pojo.AbstractDatabasePojo;

/**
 * 管理模块表
 */
public abstract class AbstractManage extends AbstractDatabasePojo {
	/**
	 * 新增操作用户编号
	 */
	@Type(value = Types.UUID)
	protected String insertUserId;
	/**
	 * 新增操作时间
	 */
	@Type(value = Types.DATE)
	protected Date insertTime;
	/**
	 * 更新操作用户编号
	 */
	@Type(value = Types.UUID)
	protected String updateUserId;
	/**
	 * 更新操作时间
	 */
	@Type(value = Types.DATE)
	protected Date updateTime;
	/**
	 * 备注
	 */
	@Type(value = Types.TEXT)
	protected String remark;
	/**
	 * 名称
	 */
	@Type(value = Types.STRING)
	protected String name;

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 新增操作时间
	 * 
	 * @return 新增操作时间
	 */
	public String getInsertTimeText() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(this.getInsertTime());
	}

	/**
	 * 修改操作时间
	 * 
	 * @return 修改操作时间
	 */
	public String getUpdateTimeText() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(this.getUpdateTime());
	}
}
