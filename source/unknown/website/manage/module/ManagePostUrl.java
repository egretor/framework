package unknown.website.manage.module;

/**
 * 岗位的Url
 */
public class ManagePostUrl extends AbstractManageTable {
	private String postId;
	private String urlId;

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
	 * Url编号
	 * 
	 * @return Url编号
	 */
	public String fgetUrlId() {
		return urlId;
	}

	public void fsetUrlId(String urlId) {
		this.urlId = urlId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
}
