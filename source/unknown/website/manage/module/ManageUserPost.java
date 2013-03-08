package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 用户的岗位
 */
public class ManageUserPost extends AbstractManage {
	/**
	 * 用户编号
	 */
	@Type(value = Types.UUID)
	private String userId;
	/**
	 * 岗位编号
	 */
	@Type(value = Types.UUID)
	private String postId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
}
