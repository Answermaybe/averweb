package com.nwnu.averweb.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
/**
 * 继承自己的MyMapper
 *
 */
public interface AverBaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
