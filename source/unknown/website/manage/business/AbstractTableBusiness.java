package unknown.website.manage.business;

import unknown.framework.business.database.AbstractTable;
import unknown.framework.module.database.Instance;
import unknown.website.Website;
import unknown.website.manage.module.AbstractManage;

/**
 * 管理模块表业务
 */
public abstract class AbstractTableBusiness<T extends AbstractManage> extends AbstractTable<T> {

	@Override
	public Instance getInstance() {
		return Website.getInstance();
	}
}
