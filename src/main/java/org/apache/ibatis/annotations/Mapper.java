package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker interface for MyBatis mappers.
 *
 * 标识为mybatis的接口
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * &#064;Mapper
 * public interface UserMapper {
 *     // ...
 * }
 * </pre>
 *
 * @author Frank David Martínez
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface Mapper {
    // Interface Mapper
}
