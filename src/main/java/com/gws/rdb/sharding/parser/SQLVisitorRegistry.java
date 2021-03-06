/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.gws.rdb.sharding.parser;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.gws.rdb.sharding.api.DatabaseType;
import com.gws.rdb.sharding.exception.DatabaseTypeUnsupportedException;
import com.gws.rdb.sharding.parser.visitor.basic.mysql.MySQLDeleteVisitor;
import com.gws.rdb.sharding.parser.visitor.basic.mysql.MySQLInsertVisitor;
import com.gws.rdb.sharding.parser.visitor.basic.mysql.MySQLSelectVisitor;
import com.gws.rdb.sharding.parser.visitor.basic.mysql.MySQLUpdateVisitor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SQL访问器注册表.
 * 
 * @author wangdong
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SQLVisitorRegistry {
    
    private static final Map<DatabaseType, Class<? extends SQLASTOutputVisitor>> SELECT_REGISTRY = new HashMap<>(DatabaseType.values().length);
    
    private static final Map<DatabaseType, Class<? extends SQLASTOutputVisitor>> INSERT_REGISTRY = new HashMap<>(DatabaseType.values().length);
    
    private static final Map<DatabaseType, Class<? extends SQLASTOutputVisitor>> UPDATE_REGISTRY = new HashMap<>(DatabaseType.values().length);
    
    private static final Map<DatabaseType, Class<? extends SQLASTOutputVisitor>> DELETE_REGISTRY = new HashMap<>(DatabaseType.values().length);
    
    static {
        registerSelectVistor();
        registerInsertVistor();
        registerUpdateVistor();
        registerDeleteVistor();
    }
    
    private static void registerSelectVistor() {
        SELECT_REGISTRY.put(DatabaseType.H2, MySQLSelectVisitor.class);
        SELECT_REGISTRY.put(DatabaseType.MySQL, MySQLSelectVisitor.class);
        // TODO 其他数据库
    }
    
    private static void registerInsertVistor() {
        INSERT_REGISTRY.put(DatabaseType.H2, MySQLInsertVisitor.class);
        INSERT_REGISTRY.put(DatabaseType.MySQL, MySQLInsertVisitor.class);
        // TODO 其他数据库
    }
    
    private static void registerUpdateVistor() {
        UPDATE_REGISTRY.put(DatabaseType.H2, MySQLUpdateVisitor.class);
        UPDATE_REGISTRY.put(DatabaseType.MySQL, MySQLUpdateVisitor.class);
        // TODO 其他数据库
    }
    
    private static void registerDeleteVistor() {
        DELETE_REGISTRY.put(DatabaseType.H2, MySQLDeleteVisitor.class);
        DELETE_REGISTRY.put(DatabaseType.MySQL, MySQLDeleteVisitor.class);
        // TODO 其他数据库
    }
    
    /**
     * 获取SELECT访问器.
     * 
     * @param databaseType 数据库类型
     * @return SELECT访问器
     */
    public static Class<? extends SQLASTOutputVisitor> getSelectVistor(final DatabaseType databaseType) {
        return getVistor(databaseType, SELECT_REGISTRY);
    }
    
    /**
     * 获取INSERT访问器.
     * 
     * @param databaseType 数据库类型
     * @return INSERT访问器
     */
    public static Class<? extends SQLASTOutputVisitor> getInsertVistor(final DatabaseType databaseType) {
        return getVistor(databaseType, INSERT_REGISTRY);
    }
    
    /**
     * 获取UPDATE访问器.
     * 
     * @param databaseType 数据库类型
     * @return UPDATE访问器
     */
    public static Class<? extends SQLASTOutputVisitor> getUpdateVistor(final DatabaseType databaseType) {
        return getVistor(databaseType, UPDATE_REGISTRY);
    }
    
    /**
     * 获取DELETE访问器.
     * 
     * @param databaseType 数据库类型
     * @return DELETE访问器
     */
    public static Class<? extends SQLASTOutputVisitor> getDeleteVistor(final DatabaseType databaseType) {
        return getVistor(databaseType, DELETE_REGISTRY);
    }
    
    private static Class<? extends SQLASTOutputVisitor> getVistor(final DatabaseType databaseType, final Map<DatabaseType, Class<? extends SQLASTOutputVisitor>> registry) {
        if (!registry.containsKey(databaseType)) {
            throw new DatabaseTypeUnsupportedException(databaseType.name()); 
        }
        return registry.get(databaseType);
    }
}
