package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 用户的岗位
 */
public class ManageUserPost extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "用户编号")
	private String userId;
	@Type(value = Types.Uuid)
	@Remark(value = "岗位编号")
	private String postId;

	/**
	 * 用户编号
	 * 
	 * @return 用户编号
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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
}
