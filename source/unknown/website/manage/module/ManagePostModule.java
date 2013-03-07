package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 岗位的模块
 */
public class ManagePostModule extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "岗位编号")
	private String postId;
	@Type(value = Types.Uuid)
	@Remark(value = "模块编号")
	private String moduleId;

	/**
	 * 岗位编号
	 * 
	 * @return 岗位编号
	 */
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

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
}
