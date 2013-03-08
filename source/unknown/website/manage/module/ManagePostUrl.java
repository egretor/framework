package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 岗位的URL
 */
public class ManagePostUrl extends AbstractManage {
	/**
	 * 岗位编号
	 */
	@Type(value = Types.UUID)
	private String postId;
	/**
	 * URL编号
	 */
	@Type(value = Types.UUID)
	private String urlId;

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
