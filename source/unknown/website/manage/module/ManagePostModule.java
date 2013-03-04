package unknown.website.manage.module;

/**
 * 岗位的模块
 */
public class ManagePostModule extends AbstractManageTable {
	private String postId;
	private String moduleId;

	/**
	 * 岗位编号
	 * 
	 * @return 岗位编号
	 */
	public String fgetPostId() {
		return postId;
	}

	public void fsetPostId(String postId) {
		this.postId = postId;
	}

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
