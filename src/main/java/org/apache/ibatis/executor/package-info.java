package org.apache.ibatis.executor;

/*
 * executor执行器
 *
 *    通过Executor+StatementHandler+ResultSetHandler+ParameterHandler完成SQL执行的整个过程
 */

/*
 * 此包涉及的设计模式
 *
 *    模版模式
 *
 *        行为设计模式,在超类中定义了一个算法的框架,允许子类在不修改结构的情况下实现自身的框架
 *
 *        Executor抽象了方法,BaseExecutor实现了基本的功能流程通过定义了doUpdate、doQuery等基本方法由子类自身去实现
 *
 *            BatchExecutor、ReuseExecutor、SimpleExecutor实现了自身的逻辑
 *
 *        StatementHandler抽象了基本的数据库操作方法,BaseStatementHandler定义了基础的操作通过定义instantiateStatement由子类自身实现
 *
 *            SimpleStatementHandler、PreparedStatementHandler、CallableStatementHandler实现了自身的逻辑
 *
 *        核心优点
 *
 *            1、公共代码逻辑由超类实现减少代码量
 *
 *            2、允许自定义自身算法实现而不关注公共部分
 */
