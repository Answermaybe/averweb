package com.nwnu.averweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.nwnu.averweb.model.SysRoleprivilege;
import com.nwnu.averweb.service.SysRolePrivilegeService;

@Service
public class SysRolePrivilegeServiceImpl extends BaseService<SysRoleprivilege>
		implements SysRolePrivilegeService {

	@Override
	public boolean rolegrant(String privilegelist, String rolecode) {
		SysRoleprivilege sysroleprivilege = new SysRoleprivilege();
		sysroleprivilege.setRolecode(rolecode);
		// boolean flag=true;
		if ((this.selectByProperty(sysroleprivilege)).size() > 0)// 如果曾经有授权，先删除授权
		{
			int i = this.delete(sysroleprivilege);
			if (i > 0)// 删除成功
			{
				if (privilegelist == null || privilegelist == "") {
					return true;
				} else {
					List<SysRoleprivilege> recordList = new ArrayList<SysRoleprivilege>();
					String[] plst = privilegelist.split(",");
					for (int j = 0; j < plst.length; j++) {
						SysRoleprivilege srdao = new SysRoleprivilege();
						srdao.setPrivilegecode(plst[j]);
						srdao.setRolecode(rolecode);
						recordList.add(srdao);
					}
					int c = this.insertList(recordList);// 再授权
					if (c > 0) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			if (privilegelist == null || privilegelist == "") {
				return true;
			} else {
				List<SysRoleprivilege> recordList = new ArrayList<SysRoleprivilege>();
				String[] plst = privilegelist.split(",");
				for (int j = 0; j < plst.length; j++) {
					SysRoleprivilege srdao = new SysRoleprivilege();
					srdao.setPrivilegecode(plst[j]);
					srdao.setRolecode(rolecode);
					recordList.add(srdao);
				}
				int c = this.insertList(recordList);// 再授权
				if (c > 0) {
					return true;
				} else {
					return false;
				}
			}
		}

	}
}
