/*
 * 核心
 *
 *      SqlSession ------ Mybatis核心对象,实际对外提供操作数据库方法
 *
 *      SqlSessionFactory ------ SqlSession工厂
 *
 *      SqlSessionFactoryBuilder ------ SqlSessionFactory构建器
 *
 *      Configuration ------ Mybatis运行过程中所有的配置信息存放
 *
 *      ResultHandler ------ 处理SQL执行后返回结果
 *
 * 用处
 *
 *      Mybatis执行时通过SqlSession作为操作数据库的入口实现整个调用过程并通过ResultHandler处理返回的结果
 *
 * 设计模式
 *
 *      builder构建器模式(创建型模式)
 *
 *          参与类:SqlSessionFactoryBuilder、SqlSessionFactory
 *
 *          SqlSessionFactoryBuilder内部定义了多种如何创建SqlSessionFactory的方法,用户可以自行选择创建方式不用关系具体的创建过程,如果需要新增新对象的创建方式时直接使用新的Builder即可
 *
 *          优势
 *
 *              解耦,客户端不必知道产品内部组成的细节,将产品本身与产品的创建过程解耦,使得相同的创建过程可以创建不同的产品对象
 *
 *              开闭原则,新增Builder不用修改原有的代码
 *
 *              可以更加细粒度控制对象创建过程
 *
 *
 *      抽象工厂模式(创建型模式)
 *
 *          参与类:SqlSessionFactory、DefaultSqlSessionFactory
 *
 *          优势
 *
 *              符合开闭原则,只需要增加新的TransactionFactory实现即可生产新类型Transaction
 *
 *              隔离具体类创建过程,客户端只需要简单的调用即可
 */

package org.apache.ibatis.session;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;

/**
 * The primary Java interface for working with MyBatis. Through this interface you can execute commands, get mappers and
 * manage transactions.
 * <p>
 * mybatis核心接口,操作数据库
 *
 * @author Clinton Begin
 */
public interface SqlSession extends Closeable {

    /**
     * Retrieve a single row mapped from the statement key.
     * <p>
     * 查询单条数据
     *
     * @param <T>       the returned object type
     * @param statement the statement
     * @return Mapped object
     */
    <T> T selectOne(String statement);

    /**
     * Retrieve a single row mapped from the statement key and parameter.
     *
     * @param <T>       the returned object type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Mapped object
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * Retrieve a list of mapped objects from the statement key.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement);

    /**
     * Retrieve a list of mapped objects from the statement key and parameter.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * Retrieve a list of mapped objects from the statement key and parameter, within the specified row bounds.
     *
     * @param <E>       the returned list element type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds Bounds to limit object retrieval
     * @return List of mapped object
     */
    <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);

    /**
     * The selectMap is a special case in that it is designed to convert a list of results into a Map based on one of
     * the properties in the resulting objects. Eg. Return a of Map[Integer,Author] for selectMap("selectAuthors","id")
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param mapKey    The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, String mapKey);

    /**
     * The selectMap is a special case in that it is designed to convert a list of results into a Map based on one of
     * the properties in the resulting objects.
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey    The property to use as key for each value in the list.
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);

    /**
     * The selectMap is a special case in that it is designed to convert a list of results into a Map based on one of
     * the properties in the resulting objects.
     *
     * @param <K>       the returned Map keys type
     * @param <V>       the returned Map values type
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param mapKey    The property to use as key for each value in the list.
     * @param rowBounds Bounds to limit object retrieval
     * @return Map containing key pair data.
     */
    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     *
     * @param <T>       the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     *
     * @param <T>       the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement, Object parameter);

    /**
     * A Cursor offers the same results as a List, except it fetches data lazily using an Iterator.
     *
     * @param <T>       the returned cursor element type.
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param rowBounds Bounds to limit object retrieval
     * @return Cursor of mapped objects
     */
    <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds);

    /**
     * Retrieve a single row mapped from the statement key and parameter using a {@code ResultHandler}.
     *
     * @param statement Unique identifier matching the statement to use.
     * @param parameter A parameter object to pass to the statement.
     * @param handler   ResultHandler that will handle each retrieved row
     */
    void select(String statement, Object parameter, ResultHandler handler);

    /**
     * Retrieve a single row mapped from the statement using a {@code ResultHandler}.
     *
     * @param statement Unique identifier matching the statement to use.
     * @param handler   ResultHandler that will handle each retrieved row
     */
    void select(String statement, ResultHandler handler);

    /**
     * Retrieve a single row mapped from the statement key and parameter using a {@code ResultHandler} and
     * {@code RowBounds}.
     *
     * @param statement Unique identifier matching the statement to use.
     * @param parameter the parameter
     * @param rowBounds RowBound instance to limit the query results
     * @param handler   ResultHandler that will handle each retrieved row
     */
    void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler);

    /**
     * Execute an insert statement.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the insert.
     */
    int insert(String statement);

    /**
     * 执行简单的插入操作
     * <p>
     * Execute an insert statement with the given parameter object. Any generated autoincrement values or selectKey
     * entries will modify the given parameter object properties. Only the number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert.
     */
    int insert(String statement, Object parameter);

    /**
     * Execute an update statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the update.
     */
    int update(String statement);

    /**
     * Execute an update statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update.
     */
    int update(String statement, Object parameter);

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @return int The number of rows affected by the delete.
     */
    int delete(String statement);

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete.
     */
    int delete(String statement, Object parameter);

    /**
     * Flushes batch statements and commits database connection. Note that database connection will not be committed if
     * no updates/deletes/inserts were called. To force the commit call {@link SqlSession#commit(boolean)}
     */
    void commit();

    /**
     * Flushes batch statements and commits database connection.
     *
     * @param force forces connection commit
     */
    void commit(boolean force);

    /**
     * Discards pending batch statements and rolls database connection back. Note that database connection will not be
     * rolled back if no updates/deletes/inserts were called. To force the rollback call
     * {@link SqlSession#rollback(boolean)}
     */
    void rollback();

    /**
     * Discards pending batch statements and rolls database connection back. Note that database connection will not be
     * rolled back if no updates/deletes/inserts were called.
     *
     * @param force forces connection rollback
     */
    void rollback(boolean force);

    /**
     * Flushes batch statements.
     *
     * @return BatchResult list of updated records
     * @since 3.0.6
     */
    List<BatchResult> flushStatements();

    /**
     * Closes the session.
     */
    @Override
    void close();

    /**
     * Clears local session cache.
     * <p>
     * 清空本地Session缓存
     */
    void clearCache();

    /**
     * Retrieves current configuration.
     *
     * @return Configuration
     */
    Configuration getConfiguration();

    /**
     * Retrieves a mapper.
     *
     * @param <T>  the mapper type
     * @param type Mapper interface class
     * @return a mapper bound to this SqlSession
     */
    <T> T getMapper(Class<T> type);

    /**
     * Retrieves inner database connection.
     *
     * @return Connection
     */
    Connection getConnection();
}
