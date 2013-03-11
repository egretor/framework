package unknown.website.manage.application;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.json.annotations.JSON;

import unknown.website.AbstractAction;
import unknown.website.manage.business.ManageUserBusiness;
import unknown.website.manage.module.ManageUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationManager;
import com.opensymphony.xwork2.config.RuntimeConfiguration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.inject.Factory;

public class ManageUserAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ManageUser> manageUsers;

	private String test;

	public String list() {

		Map<String, String> abc = new HashMap<String, String>();
		abc.put("abc", "123");
		abc.put("abc", "456");

		ManageUserBusiness bs = new ManageUserBusiness();

		ManageUser user = bs.query("E2C773DE1E1B4554B524DAA66B20360B");
		/*
		 * user.setUuid(bs.getUuid()); user.setInsertUserId(user.getUuid());
		 * user.setInsertTime(now); user.setUpdateUserId(user.getUuid());
		 * user.setUpdateTime(now); user.setRemark("test");
		 * 
		 * user.setName("root"); user.setAccount(user.getUuid());
		 * user.setPassword("password"); user.setPrerogative(true);
		 * 
		 * //bs.insert(user);
		 */

		user.setAccount("testsdflsk");
		this.test = bs.update(user).getType().toString();

		this.manageUsers = bs.query();

		Dispatcher dispatcher = Dispatcher.getInstance();
		ConfigurationManager configurationManager = dispatcher
				.getConfigurationManager();
		Configuration configuration = configurationManager.getConfiguration();
		RuntimeConfiguration runtimeConfiguration = configuration
				.getRuntimeConfiguration();
		Map<String, Map<String, ActionConfig>> map = runtimeConfiguration
				.getActionConfigs();

		Iterator<Entry<String, Map<String, ActionConfig>>> iterator = map
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, ActionConfig>> entry = iterator.next();
			Map<String, ActionConfig> map2 = entry.getValue();
			Iterator<Entry<String, ActionConfig>> iterator2 = map2.entrySet()
					.iterator();
			while (iterator2.hasNext()) {
				Entry<String, ActionConfig> entry2 = iterator2.next();
				ActionConfig actionConfig = entry2.getValue();
				this.test += " ";
				this.test += actionConfig.getClassName();
			}
		}

		return "success";
	}

	@JSON(serialize = false)
	public List<ManageUser> getManageUsers() {
		return manageUsers;
	}

	public void setManageUsers(List<ManageUser> manageUsers) {
		this.manageUsers = manageUsers;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}
