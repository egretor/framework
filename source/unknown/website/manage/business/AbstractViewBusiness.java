package unknown.website.manage.business;

import unknown.framework.business.database.AbstractView;
import unknown.framework.module.database.Instance;
import unknown.website.Website;
import unknown.website.manage.module.AbstractManage;

/**
 * 管理模块视图业务
 */
public abstract class AbstractViewBusiness<T extends AbstractManage> extends AbstractView<T> {
	@Override
	public Instance getInstance() {
		return Website.getInstance();
	}
}
