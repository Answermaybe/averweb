package com.nwnu.averweb.service;

import com.nwnu.averweb.model.SysRoleprivilege;;

public interface SysRolePrivilegeService extends IService<SysRoleprivilege>{
	 boolean rolegrant(String privilegelist, String rolecode);
}
