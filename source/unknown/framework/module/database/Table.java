package unknown.framework.module.database;

import java.util.List;

/**
 * 数据库表数据类
 */
public class Table {
	/**
	 * 字段集合
	 */
	private List<String> fields;
	/**
	 * 行数据集合
	 */
	private List<Row> rows;

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> rows) {
		this.rows = rows;
	}
}
