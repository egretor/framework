package unknown.framework.module.database;

import java.util.List;

/**
 * 数据库表数据类
 */
public class Table {
	private List<String> fields;
	private List<Row> rows;

	/**
	 * 字段集合
	 * 
	 * @return 字段集合
	 */
	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	/**
	 * 行数据集合
	 * 
	 * @return 行数据集合
	 */
	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
}
