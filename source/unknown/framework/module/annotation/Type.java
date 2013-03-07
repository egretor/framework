package unknown.framework.module.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类型注解类
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Type {
	/**
	 * 类型值
	 * 
	 * @return 类型值
	 */
	public Types value();

	/**
	 * 主键
	 * 
	 * @return 主键
	 */
	public boolean major() default false;
}
