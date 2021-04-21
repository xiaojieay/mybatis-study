package org.apache.ibatis.session;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

/**
 * Builds {@link SqlSession} instances.
 *
 * SqlSessionFactory构建器,入口类
 *
 * @author Clinton Begin
 */
public class SqlSessionFactoryBuilder {

    /**
     * 构建SqlSessionFactory
     *
     * @param reader
     * @return
     */
    public SqlSessionFactory build(Reader reader) {

        return build(reader, null, null);
    }

    public SqlSessionFactory build(Reader reader, String environment) {

        return build(reader, environment, null);
    }

    public SqlSessionFactory build(Reader reader, Properties properties) {

        return build(reader, null, properties);
    }

    public SqlSessionFactory build(Reader reader, String environment, Properties properties) {

        try {
            // xmlConfig构建器
            XMLConfigBuilder parser = new XMLConfigBuilder(reader, environment, properties);
            return build(parser.parse());
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        } finally {
            ErrorContext.instance().reset();
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public SqlSessionFactory build(InputStream inputStream) {

        return build(inputStream, null, null);
    }

    public SqlSessionFactory build(InputStream inputStream, String environment) {

        return build(inputStream, environment, null);
    }

    public SqlSessionFactory build(InputStream inputStream, Properties properties) {

        return build(inputStream, null, properties);
    }

    public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {

        try {
            XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
            return build(parser.parse());
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error building SqlSession.", e);
        } finally {
            ErrorContext.instance().reset();
            try {
                inputStream.close();
            } catch (IOException e) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }

    public SqlSessionFactory build(Configuration config) {

        return new DefaultSqlSessionFactory(config);
    }

}