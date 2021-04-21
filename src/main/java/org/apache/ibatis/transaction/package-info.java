/*
 * 事务层
 *
 *    实现数据库的事务控制获取连接、commit、rollback,通常整合Spring后事务由Spring管理
 *    对外暴露TransactionFactory接口方便与第三方接入
 */

package org.apache.ibatis.transaction;