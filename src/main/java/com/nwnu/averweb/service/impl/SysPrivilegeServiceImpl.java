package com.nwnu.averweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwnu.averweb.mapper.SysPrivilegeMapper;
import com.nwnu.averweb.model.SysPrivilege;
import com.nwnu.averweb.service.SysPrivilegeService;

@Service
public class SysPrivilegeServiceImpl extends BaseService<SysPrivilege>
		implements SysPrivilegeService {

	@Autowired
	SysPrivilegeMapper sysprivilegeMapper;

	@Override
	public String getCode(String ParentCode) {
		if (ParentCode.equals("0000")) {			
			String CodeStr = sysprivilegeMapper.getParentCode();
			if (CodeStr != null) {
				return CodeStr;
			} else {
				return "0100";
			}

		} else {

			String CodeStr = sysprivilegeMapper.getCode(ParentCode);
			if (CodeStr != null) {
				return CodeStr;
			} else {
				String str = ParentCode.substring(0, 2);
				return str + "01";
			}

		}
	}

}
