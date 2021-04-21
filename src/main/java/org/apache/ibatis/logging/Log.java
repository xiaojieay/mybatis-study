/*
 * 核心
 *
 *      Log ------ Mybatis自定义日志接口,方便与外部第三方日志框架整合
 *
 *      LogFactory ------ 用于当前支持第三方框架日志类型生成
 *
 *      Log子类 ------ 具体与第三方日志框架整合的实现类
 *
 * 用处
 *
 *      用于打印记录Mybatis执行过程中的一些日志
 *
 * 设计模式
 *
 *      适配器模式(结构性模式)
 *
 *          使接口不兼容的对象能够相互合作
 *
 *          参与类:Log、Log实现类
 *
 *          Log是Mybatis内部日志接口,与第三方整合时通过在Mybatis内部Log上进行封装修饰实现对应第三方日志实现
 *
 *          优势
 *
 *              符合开闭原则,将目标类和适配者类解耦,通过引入一个适配器类来重用现有的适配者类,而无须修改原有代码
 *
 *              灵活性和拓展性强
 */

package org.apache.ibatis.logging;

/**
 * 日志模块
 *
 * @author Clinton Begin
 */
public interface Log {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void error(String s, Throwable e);

    void error(String s);

    void debug(String s);

    void trace(String s);

    void warn(String s);
}
