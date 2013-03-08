package unknown.framework.module.database;

import java.util.List;

/**
 * 数据库操作类
 */
public class Operation {
	/**
	 * 类型
	 */
	private OperationTypes type;
	/**
	 * SQL语句
	 */
	private String sql;
	/**
	 * 参数
	 */
	private List<Object> parameters;
	/**
	 * 分页器
	 */
	private Paging paging;

	public OperationTypes getType() {
		return type;
	}

	public void setType(OperationTypes type) {
		this.type = type;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
