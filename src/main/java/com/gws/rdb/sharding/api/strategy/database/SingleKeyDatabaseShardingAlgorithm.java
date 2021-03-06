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

package com.gws.rdb.sharding.api.strategy.database;

import com.gws.rdb.sharding.api.strategy.common.SingleKeyShardingAlgorithm;

/**
 * 单分片键的分库算法接口.
 * 
 * @author wangdong
 */
public interface SingleKeyDatabaseShardingAlgorithm<T extends Comparable<?>> extends SingleKeyShardingAlgorithm<T>, DatabaseShardingAlgorithm {
}
