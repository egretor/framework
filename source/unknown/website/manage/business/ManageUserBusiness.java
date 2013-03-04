package unknown.website.manage.business;

import java.util.List;

import unknown.website.manage.module.ManageUser;

public class ManageUserBusiness extends AbstractMySQLTable<ManageUser> {

	@Override
	public List<ManageUser> Unique(ManageUser value) {
		return null;
	}

	@Override
	public boolean deleteReference(ManageUser value) {
		return false;
	}

	@Override
	public boolean hasReference(ManageUser value) {
		return false;
	}

	@Override
	public ManageUser initializePojo() {
		return new ManageUser();
	}
}
