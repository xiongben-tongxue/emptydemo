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

package com.gws.rdb.sharding.api.strategy.database.imp;

import com.google.common.collect.Range;
import com.gws.rdb.sharding.api.ShardingValue;
import com.gws.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

public final class DefaultSingleKeyModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
    
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % availableTargetNames.size() + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Long> values = (Collection<Long>) shardingValue.getValues();
        for (Long value : values) {
            for (String dataSourceName : availableTargetNames) {
                if (dataSourceName.endsWith(value %  availableTargetNames.size() + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i %  availableTargetNames.size() + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
