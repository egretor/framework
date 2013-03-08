package unknown.framework.module.database;

import java.util.List;


/**
 * 数据库表映射类
 */
public class TableMap {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 主键字段
	 */
	private FieldMap major;
	/**
	 * 主键字段除外的其他字段
	 */
	private List<FieldMap> others;
	/**
	 * 所有字段
	 */
	private List<FieldMap> fieldMaps;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FieldMap getMajor() {
		return major;
	}

	public void setMajor(FieldMap major) {
		this.major = major;
	}

	public List<FieldMap> getOthers() {
		return others;
	}

	public void setOthers(List<FieldMap> others) {
		this.others = others;
	}

	public List<FieldMap> getFieldMaps() {
		return fieldMaps;
	}

	public void setFieldMaps(List<FieldMap> fieldMaps) {
		this.fieldMaps = fieldMaps;
	}
}
