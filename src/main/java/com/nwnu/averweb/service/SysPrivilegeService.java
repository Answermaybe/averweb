package com.nwnu.averweb.service;

import com.nwnu.averweb.model.SysPrivilege;

public interface SysPrivilegeService extends IService<SysPrivilege>{
	String getCode(String ParentCode);	
}
