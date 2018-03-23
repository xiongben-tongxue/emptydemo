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

package com.gws.rdb.sharding.merger.iterator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gws.rdb.sharding.jdbc.AbstractShardingResultSet;
import com.gws.rdb.sharding.parser.result.merger.MergeContext;

/**
 * 迭代结果集.
 * 
 * @author wangdong
 */
public final class IteratorResultSet extends AbstractShardingResultSet {
    
    public IteratorResultSet(final List<ResultSet> resultSets, final MergeContext mergeContext) {
        super(resultSets, mergeContext.getLimit());
    }
    
    @Override
    protected boolean nextForSharding() throws SQLException {
        if (getCurrentResultSet().next()) {
            return true;
        }
        for (int i = getResultSets().indexOf(getCurrentResultSet()) + 1; i < getResultSets().size(); i++) {
            ResultSet each = getResultSets().get(i);
            if (each.next()) {
                setCurrentResultSet(each);
                return true;
            }
        }
        return false;
    }
}
