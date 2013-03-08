package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 岗位的模块
 */
public class ManagePostModule extends AbstractManage {
	/**
	 * 岗位编号
	 */
	@Type(value = Types.UUID)
	private String postId;
	/**
	 * 模块编号
	 */
	@Type(value = Types.UUID)
	private String moduleId;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
