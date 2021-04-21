package org.apache.ibatis.session;

/**
 * 结果集处理
 *
 * @author Clinton Begin
 */
public interface ResultHandler<T> {

    /**
     * 处理查询结果
     *
     * @param resultContext
     */
    void handleResult(ResultContext<? extends T> resultContext);

}
