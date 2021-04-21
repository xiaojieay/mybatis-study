/*
 * 核心
 *
 *      TypeHandler ------ 行为接口,定义了类型转换需要实现的方法
 *
 *      TypeHandlerRegistry ------ 保存JavaType、JdbcType对应的类型映射关系,为运行时通过对应类型获取到映射器
 *
 *      TypeReference ------ Type包装类,允许获取顶层Type
 *
 *      BaseTypeHandler ------ 基础类型映射处理器超类,实现TypeHandler方法定义基本类型转换方式,并定义模版类方法让子类自行实现
 *
 *      各种BaseTypeHandler实现类 ------ 模版子类,实现了模版方法单一维护自身的映射关系
 *
 * 用处
 *
 *      当Mybatis进行入参映射、结果返回映射时会通过TypeHandler将值赋值到SQL中或者响应结果中
 *
 *  设计模式
 *
 *      模版模式(行为模式)
 *
 *          参与类:TypeHandler、BaseTypeHandler、各种BaseTypeHandler实现类
 *
 *          优势
 *
 *              当需要配置一个新的类型转换时,不需要变更任何的使用逻辑,通过继承BaseTypeHandler并实现自身逻辑,再将自身添加进TypeHandlerRegistry即可
 *
 *              提高了代码的复用
 *
 *              符合开闭原则,只需要通过新增子类即可添加新的类型转换逻辑
 *
 */

package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 类型转换模块
 *
 * @author Clinton Begin
 */
public interface TypeHandler<T> {

    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

    /**
     * Gets the result.
     * <p>
     * 获取结果
     *
     * @param rs         the rs
     * @param columnName Colunm name, when configuration <code>useColumnLabel</code> is <code>false</code>
     * @return the result
     * @throws SQLException the SQL exception
     */
    T getResult(ResultSet rs, String columnName) throws SQLException;

    T getResult(ResultSet rs, int columnIndex) throws SQLException;

    T getResult(CallableStatement cs, int columnIndex) throws SQLException;

}
