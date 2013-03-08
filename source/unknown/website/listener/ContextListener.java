package unknown.website.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.Instance;
import unknown.framework.utility.Trace;
import unknown.website.Website;

/**
 * Application Lifecycle Listener implementation class ContextListener
 * 
 */
@WebListener
public class ContextListener implements ServletContextListener,
		ServletContextAttributeListener {
	/**
	 * 网站目录键
	 */
	private final static String WEBSITE_DIRECTORY_KEY = "WebsiteDirectoryKey";

	/**
	 * 网站目录
	 */
	private static String WEBSITE_DIRECTORY;
	/**
	 * 网站URL
	 */
	private static String WEBSITE_URL;

	public static String getWebSiteDirectory() {
		return WEBSITE_DIRECTORY;
	}

	public static String getWebsiteUrl() {
		return WEBSITE_URL;
	}

	/**
	 * 初始化网站目录
	 * 
	 * @param servletContext
	 */
	private void initializeWebsiteDirectory(ServletContext servletContext) {
		String websiteDirectoryKey = servletContext
				.getInitParameter(ContextListener.WEBSITE_DIRECTORY_KEY);
		ContextListener.WEBSITE_DIRECTORY = servletContext.getRealPath("/");
		System.setProperty(websiteDirectoryKey,
				ContextListener.WEBSITE_DIRECTORY);
	}

	/**
	 * 初始化网站URL
	 * 
	 * @param servletContext
	 */
	private void initializeWebsiteUrl(ServletContext servletContext) {
		ContextListener.WEBSITE_URL = servletContext.getContextPath();
	}

	/**
	 * 初始化数据库实例
	 * 
	 * @param servletContext
	 * @param instanceName
	 *            实例名称
	 * @return
	 */
	private Instance initializeInstance(ServletContext servletContext,
			String instanceName) {
		Instance result = new Instance();

		String driverKey = String.format("%s.Driver", instanceName);
		String urlKey = String.format("%s.Url", instanceName);
		String userKey = String.format("%s.User", instanceName);
		String passwordKey = String.format("%s.Password", instanceName);
		String identifierCapitalKey = String.format("%s.IdentifierCapital",
				instanceName);
		String typeConverterKey = String.format("%s.TypeConverter",
				instanceName);
		String sqlBuilderKey = String.format("%s.SqlBuilder", instanceName);

		String driver = servletContext.getInitParameter(driverKey);
		String url = servletContext.getInitParameter(urlKey);
		String user = servletContext.getInitParameter(userKey);
		String password = servletContext.getInitParameter(passwordKey);
		boolean identifierCapital = false;
		AbstractTypeConverter typeConverter = null;
		AbstractSqlBuilder sqlBuilder = null;

		String identifierCapitalText = servletContext
				.getInitParameter(identifierCapitalKey);
		String typeConverterText = servletContext
				.getInitParameter(typeConverterKey);
		String sqlBuilderText = servletContext.getInitParameter(sqlBuilderKey);

		identifierCapital = Boolean.parseBoolean(identifierCapitalText);
		try {
			typeConverter = (AbstractTypeConverter) Class.forName(
					typeConverterText).newInstance();
			sqlBuilder = (AbstractSqlBuilder) Class.forName(sqlBuilderText)
					.newInstance();
		} catch (InstantiationException e) {
			Trace.getFramework().error(e);
		} catch (IllegalAccessException e) {
			Trace.getFramework().error(e);
		} catch (ClassNotFoundException e) {
			Trace.getFramework().error(e);
		}

		result.setDriver(driver);
		result.setUrl(url);
		result.setUser(user);
		result.setPassword(password);
		result.setIdentifierCapital(identifierCapital);
		result.setTypeConverter(typeConverter);
		result.setSqlBuilder(sqlBuilder);

		return result;
	}

	/**
	 * Default constructor.
	 */
	public ContextListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();

		this.initializeWebsiteDirectory(servletContext);
		this.initializeWebsiteUrl(servletContext);

		Instance websiteInstance = this.initializeInstance(servletContext,
				Website.INSTANCE_NAME);
		Website.setInstance(websiteInstance);
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent event) {
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent event) {
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent event) {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
	}

}
