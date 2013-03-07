package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 模块的Action
 */
public class ManageModuleAction extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "模块编号")
	private String moduleId;
	@Type(value = Types.Uuid)
	@Remark(value = "Action编号")
	private String actionId;

	/**
	 * 模块编号
	 * 
	 * @return 模块编号
	 */
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * Action编号
	 * 
	 * @return Action编号
	 */
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}
