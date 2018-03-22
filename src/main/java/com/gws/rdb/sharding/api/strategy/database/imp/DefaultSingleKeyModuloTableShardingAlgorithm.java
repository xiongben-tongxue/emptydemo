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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

import com.google.common.collect.Range;
import com.gws.rdb.sharding.api.ShardingValue;
import com.gws.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

public final class DefaultSingleKeyModuloTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Integer> {
    
	 public static boolean isInteger(String str) {    
		    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		    return pattern.matcher(str).matches();    
		  } 
	 
	 public static String replaceTargeName(String targetName){
		 targetName.replace("ds_", "");
		 targetName.replace("ss_", "");
		 return targetName;
	 }
	 
    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        for (String each : availableTargetNames) {
        	String eachTarge = replaceTargeName(each);
        	if(isInteger(eachTarge)){
            	Integer modSharding = shardingValue.getValue()%availableTargetNames.size();
            	if(modSharding.equals(Integer.valueOf(eachTarge))){
            		return each;
            	}	
        	}
        	else{
        		return each;
        	}

        }
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<Integer> values = shardingValue.getValues();
        for (Integer value : values) {
            for (String tableNames : availableTargetNames) {
                if (tableNames.endsWith(value % availableTargetNames.size() + "")) {
                    result.add(tableNames);
                }
            }
        }
        return result;
    }
    
    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % availableTargetNames.size() + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}