package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 岗位的Url
 */
public class ManagePostUrl extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "岗位编号")
	private String postId;
	@Type(value = Types.Uuid)
	@Remark(value = "Url编号")
	private String urlId;

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
	 * Url编号
	 * 
	 * @return Url编号
	 */
	public String getUrlId() {
		return urlId;
	}

	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
}
