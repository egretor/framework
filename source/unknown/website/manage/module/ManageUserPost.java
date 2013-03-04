package unknown.website.manage.module;

/**
 * 用户的岗位
 */
public class ManageUserPost extends AbstractManageTable {
	private String userId;
	private String postId;

	/**
	 * 用户编号
	 * 
	 * @return 用户编号
	 */
	public String fgetUserId() {
		return userId;
	}

	public void fsetUserId(String userId) {
		this.userId = userId;
	}

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
