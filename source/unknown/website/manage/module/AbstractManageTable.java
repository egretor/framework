package unknown.website.manage.module;

import java.text.SimpleDateFormat;
import java.util.Date;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;
import unknown.framework.module.pojo.TablePojo;

/**
 * 管理模块表
 */
public abstract class AbstractManageTable extends TablePojo {
	@Type(value = Types.Uuid)
	@Remark(value = "新增操作用户编号")
	protected String insertUserId;
	@Type(value = Types.Date)
	@Remark(value = "新增操作时间")
	protected Date insertTime;
	@Type(value = Types.Uuid)
	@Remark(value = "修改操作用户编号")
	protected String updateUserId;
	@Type(value = Types.Date)
	@Remark(value = "修改操作时间")
	protected Date updateTime;
	@Type(value = Types.Text)
	@Remark(value = "备注")
	protected String remark;
	@Type(value = Types.String)
	@Remark(value = "名称")
	protected String name;

	/**
	 * 执行新增操作用户编号
	 * 
	 * @return 执行新增操作用户编号
	 */
	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}

	/**
	 * 新增时间
	 * 
	 * @return 新增时间
	 */
	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * 执行更新操作用户编号
	 * 
	 * @return 执行更新操作用户编号
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 备注
	 * 
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInsertTimeText() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return simpleDateFormat.format(this.getInsertTime());
	}
}
