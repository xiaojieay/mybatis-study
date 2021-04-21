package org.apache.ibatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The marker annotation that indicate a constructor for automatic mapping.
 *
 * <p>
 * <b>How to use:</b>
 *
 * <pre>
 * public class User {
 *
 *   private int id;
 *   private String name;
 *
 *   public User(int id) {
 *     this.id = id;
 *   }
 *
 *   &#064;AutomapConstructor
 *   public User(int id, String name) {
 *     this.id = id;
 *     this.name = name;
 *   }
 *   // ...
 * }
 * </pre>
 *
 * @author Tim Chen
 * @since 3.4.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR })
public @interface AutomapConstructor {
}
