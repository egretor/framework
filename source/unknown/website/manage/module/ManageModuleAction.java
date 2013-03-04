package unknown.website.manage.module;

/**
 * 模块的Action
 */
public class ManageModuleAction extends AbstractManageTable {
	private String moduleId;
	private String actionId;

	/**
	 * 模块编号
	 * 
	 * @return 模块编号
	 */
	public String fgetModuleId() {
		return moduleId;
	}

	public void fsetModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * Action编号
	 * 
	 * @return Action编号
	 */
	public String fgetActionId() {
		return actionId;
	}

	public void fsetActionId(String actionId) {
		this.actionId = actionId;
	}

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
