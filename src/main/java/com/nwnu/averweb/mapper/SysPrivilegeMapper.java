package com.nwnu.averweb.mapper;


import org.apache.ibatis.annotations.Param;

import com.nwnu.averweb.model.SysPrivilege;
import com.nwnu.averweb.util.AverBaseMapper;


public interface SysPrivilegeMapper extends AverBaseMapper<SysPrivilege>{
	String getCode(@Param("parentcode") String ParentCode);
	String getParentCode();
}