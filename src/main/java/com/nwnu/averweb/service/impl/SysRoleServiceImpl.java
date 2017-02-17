package com.nwnu.averweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nwnu.averweb.mapper.SysRoleMapper;
import com.nwnu.averweb.model.SysRole;
import com.nwnu.averweb.service.SysRoleService;

@Service
public class SysRoleServiceImpl extends BaseService<SysRole> implements SysRoleService{

	@Autowired
	private SysRoleMapper sysroleMapper;	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		String obj=sysroleMapper.getCode();
		 if (obj != null)
         {
             return obj;
         }
         else
         {
             return "0001";
         }		
	}	

}
