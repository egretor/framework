package unknown.framework.module.database;

import java.util.List;

/**
 * 数据库行数据类
 */
public class Row {
	/**
	 * 数据集合
	 */
	private List<Object> values;

	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}
}
