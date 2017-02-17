/**
 * 
 */
package com.nwnu.averweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.nwnu.averweb.service.IService;
import com.nwnu.averweb.util.AverBaseMapper;

/**
 * @author Administrator
 * @param <T>
 *
 */

public abstract class BaseService<T> implements IService<T> {

	@Autowired
	protected AverBaseMapper<T> mapper;

	public AverBaseMapper<T> getMapper() {
		return mapper;
	}

	/*
	 * 根据主键查询,获得一个实体
	 */
	@Override
	public T selectByKey(Object key) {
		// TODO
		return mapper.selectByPrimaryKey(key);
	}

	/*
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * 
	 * @see com.nwnu.averweb.service.IService#selectOne(java.lang.Object)
	 */
	@Override
	public T selectOne(T record) {
		// TODO
		return mapper.selectOne(record);
	}

	/*
	 * 无条件获取全部
	 * 
	 * @see com.nwnu.averweb.service.IService#selectAll()
	 */
	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

	/*
	 * 根据实体中的属性值进行查询获取列表，查询条件使用等号
	 * 
	 * @see com.nwnu.averweb.service.IService#select(java.lang.Object)
	 */
	@Override
	public List<T> selectByProperty(T record) {
		// TODO Auto-generated method stub
		return mapper.select(record);
	}

	/*
	 * 通过操作ids字符串进行操作，ids 如 "1,2,3" 这种形式的字符串，这个方法要求实体类中有且只有一个带有@Id注解的字段，否则会抛出异常
	 * @see com.nwnu.averweb.service.IService#selectByIds(java.lang.String)
	 */
	@Override
	public List<T> selectByIds(List<Object> ids,String property,Class<T> clazz) {
		// TODO Auto-generated method stub
		 Example example = new Example(clazz);
		 example.createCriteria().andIn(property, ids);  
	     return this.mapper.selectByExample(example) ;
	}

	/*
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * 
	 * @see com.nwnu.averweb.service.IService#selectCount(java.lang.Object)
	 */
	@Override
	public int selectCount(T record) {
		// TODO Auto-generated method stub
		return mapper.selectCount(record);
	}

	/*
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * 
	 * @see com.nwnu.averweb.service.IService#save(java.lang.Object)
	 */
	@Override
	public int insert(T record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	/*
	 * 批量保存，仅支持mysql，且主键id必须自增
	 * 
	 * @see com.nwnu.averweb.service.IService#insertList(java.util.List)
	 */
	@Override
	public int insertList(List<T> recordList) {
		// TODO Auto-generated method stub
		return mapper.insertList(recordList);
	}

	/*
	 * 根据主键更新属性不为null的值
	 * 
	 * @see
	 * com.nwnu.averweb.service.IService#updateByPrimaryKey(java.lang.Object)
	 */
	@Override
	public int updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	/*
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * 
	 * @see com.nwnu.averweb.service.IService#delete(java.lang.Object)
	 */
	@Override
	public int delete(T record) {
		// TODO Auto-generated method stub
		return mapper.delete(record);
	}

	/*
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * 
	 * @see
	 * com.nwnu.averweb.service.IService#deleteByPrimaryKey(java.lang.Object)
	 */
	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(key);
	}

	/**
	 * 根据ids批量删除
	 */
	@Override
	public int deleteByIds(List<Object> ids,String property,Class<T> clazz) {		
		// TODO Auto-generated method stub
		 Example example = new Example(clazz);
		 example.createCriteria().andIn(property, ids);  
	     return this.mapper.deleteByExample(example);
	}
	
	/*
	 * 根据T返回分页List
	 * @see com.nwnu.averweb.service.IService#pageList(java.lang.Object, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<T> pageList(T record, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 设置分页参数  		
        // 查询数据  
        List<T> lists = mapper.select(record);
        return lists;  
	}

	/*
	 * 根据example条件进行翻页
	 *  Example example = new Example(SysUser.class);
		    Example.Criteria criteria = example.createCriteria();		    
	        if (StringUtil.isNotEmpty(sysuser.getName())) {
	            criteria.andLike("name", "%" + sysuser.getName() + "%");
	        }
	        
	        if (sysuser.getId() != null) {
	            criteria.andEqualTo("id", sysuser.getId());
	        }
	 * @see com.nwnu.averweb.service.IService#pageListByExample(tk.mybatis.mapper.entity.Example, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<T> pageListByExample(Example example, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);// 设置分页参数  
        // 查询数据  		
        List<T> lists = mapper.selectByExample(example);  
        return lists;  
	}

	
}
