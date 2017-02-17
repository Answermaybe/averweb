package com.nwnu.averweb.service;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用接口
 */
@Service
public interface IService<T> {

	/*
	 * 根据主键查询,获得一个值
	 */
    T selectByKey(Object key);
    
    /*
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     */
    T selectOne(T record);
    
    /*
     * 获取全部
     */
    List<T> selectAll();
    
    /*
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    List<T> selectByProperty(T record);
    /*
     * 根据ids进行查询
     */
    List<T> selectByIds(List<Object> ids,String property,Class<T> clazz);
    
    /*
     * 根据实体中的属性查询总数，查询条件使用等号
     */
    int selectCount(T record);
    
    /*
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    int insert(T record);
    
    /*
     * 批量保存，仅支持mysql，且主键id必须自增
     */
    int insertList(List<T> recordList);

    /*
     * 根据主键更新属性不为null的值
     */
    int updateByPrimaryKey(T record);
    
    /*
     * 根据实体属性作为条件进行删除，查询条件使用等号 
     */
    int delete(T record);
    
    /*
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    int deleteByPrimaryKey(Object key);
    /*
     * 根据ids列表进行删除
     */
    int deleteByIds(List<Object> ids,String property,Class<T> clazz);
    /*
     * 根据Example条件进行查询，用于分页
     */
   // List<T> selectByExample(Object example);
    
    /*
     * 无条件分页
     */
    List<T> pageList(T record, Integer page, Integer rows);
    /*
     * 根据example条件进行查询
     */
    List<T> pageListByExample(Example example, Integer page, Integer rows);
    //TODO 其他...
}
