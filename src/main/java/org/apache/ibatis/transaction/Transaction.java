/*
 * 核心
 *
 *      Transaction ------ 事务管理核心接口,提供获取连接以及事务提交回滚等方法
 *
 *      TransactionFactory ------ Transaction生成工厂,负责创建Transaction
 *
 *      Mybatis通常会和Spring生态进行整合,事务也会交由spring管理
 * 
 * 设计模式
 *
 *      抽象工厂模式(创建型模式)
 *
 *          参与类:TransactionFactory、ManagedTransactionFactory、JdbcTransactionFactory
 *
 *          TransactionFactory定义了工厂需要实现的创建方法,JdbcTransactionFactory作为子类工厂负责生产JdbcTransaction、ManagedTransactionFactory作为子类工厂负责生产ManagedTransaction
 *          当需要新的Transaction时,只需要一个新的TransactionFactory实现类生产即可
 *
 *          优势
 *
 *              符合开闭原则,只需要增加新的TransactionFactory实现即可生产新类型Transaction
 *
 *              隔离具体类创建过程,客户端只需要简单的调用即可
 */

package org.apache.ibatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Wraps a database connection. Handles the connection lifecycle that comprises: its creation, preparation,
 * commit/rollback and close.
 * <p>
 * 包装数据库连接
 *
 * @author Clinton Begin
 */
public interface Transaction {

    /**
     * Retrieve inner database connection.
     * <p>
     * 获取数据库连接
     *
     * @return DataBase connection
     * @throws SQLException the SQL exception
     */
    Connection getConnection() throws SQLException;

    /**
     * Commit inner database connection.
     * <p>
     * 提交事务
     *
     * @throws SQLException the SQL exception
     */
    void commit() throws SQLException;

    /**
     * Rollback inner database connection.
     * <p>
     * 回滚事务
     *
     * @throws SQLException the SQL exception
     */
    void rollback() throws SQLException;

    /**
     * Close inner database connection.
     *
     * @throws SQLException the SQL exception
     */
    void close() throws SQLException;

    /**
     * Get transaction timeout if set.
     *
     * @return the timeout
     * @throws SQLException the SQL exception
     */
    Integer getTimeout() throws SQLException;

}
