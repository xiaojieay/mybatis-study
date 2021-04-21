/*
 * 核心
 *
 *      LanguageDriver ------ 动态SQL引擎,定义了创建数据库语句方法
 *
 *      LanguageDriverRegistry ------ 保存动态SQL引擎实现,方便程序中动态获取引擎
 *
 *      SqlNode ------ 动态SQL语句中的关键字节点,定义了处理方式
 *
 * 用处
 *
 *      SQL执行前将Mapper数据库文件中需要执行的SQL进行动态标签解析例如if、where、set等xml节点信息,解析完成后进行参数映射后才能执行
 *
 * 设计模式
 *
 *      组合模式(结构型模式)
 *
 *          参与类:SqlNode的实现类(SetSqlNode、IfSqlNode等)
 *
 *          通过MixedSqlNode将多个标签信息处理类组合在一起对外进行服务,可以在程序中根据条件动态的拼接组合的方式实现不同的效果
 *
 *          优势
 *
 *              符合开闭原则,在组合模式中增加新的实现或者新的组合很方便,无须对现有代码进行任何修改
 *
 *              客户端可以一致地使用一个组合结构或其中单个对象,不必关心处理的是单个对象还是整个组合结构,简化了客户端代码
 *
 */
package org.apache.ibatis.scripting;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

public interface LanguageDriver {

    /**
     * Creates a {@link ParameterHandler} that passes the actual parameters to the the JDBC statement.
     *
     * @param mappedStatement The mapped statement that is being executed
     * @param parameterObject The input parameter object (can be null)
     * @param boundSql        The resulting SQL once the dynamic language has been executed.
     * @return the parameter handler
     * @author Frank D. Martinez [mnesarco]
     * @see DefaultParameterHandler
     */
    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

    /**
     * Creates an {@link SqlSource} that will hold the statement read from a mapper xml file. It is called during
     * startup, when the mapped statement is read from a class or an xml file.
     *
     * @param configuration The MyBatis configuration
     * @param script        XNode parsed from a XML file
     * @param parameterType input parameter type got from a mapper method or specified in the parameterType xml attribute. Can be
     *                      null.
     * @return the sql source
     */
    SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType);

    /**
     * Creates an {@link SqlSource} that will hold the statement read from an annotation. It is called during startup,
     * when the mapped statement is read from a class or an xml file.
     *
     * @param configuration The MyBatis configuration
     * @param script        The content of the annotation
     * @param parameterType input parameter type got from a mapper method or specified in the parameterType xml attribute. Can be
     *                      null.
     * @return the sql source
     */
    SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType);

}
