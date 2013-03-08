package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 模块的Action
 */
public class ManageModuleAction extends AbstractManage {
	/**
	 * 模块编号
	 */
	@Type(value = Types.UUID)
	private String moduleId;
	/**
	 * Action编号
	 */
	@Type(value = Types.UUID)
	private String actionId;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}
