package com.nwnu.averweb.controller;

import com.nwnu.averweb.model.SysPrivilege;
import com.nwnu.averweb.model.SysRole;
import com.nwnu.averweb.model.SysRoleprivilege;
import com.nwnu.averweb.service.SysPrivilegeService;
import com.nwnu.averweb.service.SysRolePrivilegeService;
import com.nwnu.averweb.service.SysRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "SysRole")
public class SysRoleController {

	@Autowired
	private SysRoleService sysroleService;
	@Autowired
	private SysPrivilegeService sysprivilegeService;
	@Autowired
	private SysRolePrivilegeService sysroleprivilegeService;
	
	@RequestMapping(value = "index")
	public String index() {		
		return "SysRole/index";
	}

	/***
	 * 列表
	 * 
	 * @param sysrole
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getList(SysRole sysrole,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows,
			@RequestParam(required = false, defaultValue = "id") String sort,
			@RequestParam(required = false, defaultValue = "asc") String order) {
		Example example = new Example(SysRole.class);		
		example.setOrderByClause(sort + " " + order);
		Map<String, Object> result = new HashMap<String, Object>();
		List<SysRole> sysroleList = sysroleService.pageListByExample(example,
				page, rows);
		int total = sysroleService.selectCount(sysrole);
		result.put("total", total);
		result.put("rows", sysroleList);
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param sysrole
	 * @return
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(SysRole sysrole) {
		ModelAndView result = new ModelAndView();
		if (sysrole.getId() != null) {
			sysrole = sysroleService.selectByKey(sysrole.getId());
		} else {
			sysrole.setRolecode(sysroleService.getCode());
		}
		result.addObject("sysrole", sysrole);
		return result;
	}

	/***
	 * 保存，如果是现在，调用insert，如果是修改，调用updateByPrimaryKey
	 * 
	 * @param sysrole
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(SysRole sysrole) {
		Map<String, Object> result = new HashMap<String, Object>();		
		if(sysrole.getRolename()==null||sysrole.getRolename().equals(""))
		{
			result.put("IsSuccess", false);
			result.put("Message", "角色名称不能为空");
			return result;
		}
		if(sysrole.getRolecode()==null||sysrole.getRolecode().equals(""))
		{
			result.put("IsSuccess", false);
			result.put("Message", "角色编码不能为空");
			return result;
		}
		int i = 1;
		if (sysrole.getId() != null) {			
			i = sysroleService.updateByPrimaryKey(sysrole);
		} else {
			i = sysroleService.insert(sysrole);
		}
		if (i == 1) {
			result.put("IsSuccess", true);
			result.put("Message", "保存成功");
		} else {
			result.put("IsSuccess", false);
			result.put("Message", "保存失败");
		}
		return result;
	}

	/***
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(Integer id) {
		Map<String, Object> result = new HashMap<String, Object>();
		int i = sysroleService.deleteByPrimaryKey(id);
		if (i == 1) {
			result.put("IsSuccess", true);
			result.put("Message", "删除成功");
		} else {
			result.put("IsSuccess", false);
			result.put("Message", "删除失败");
		}
		return result;
	}

	/**
	 * 根据角色拥有的权限加载权限树
	 * @param id
	 * @return
	 */
	@RequestMapping(value="grant")
	 public ModelAndView grant(Integer id)
     {
		 ModelAndView result = new ModelAndView();
		 SysRole sysrole=sysroleService.selectByKey(id);       
         StringBuilder sb = new StringBuilder();//树数据
         //查找全部权限内容
         List<SysPrivilege> allprivielgelst=sysprivilegeService.selectAll();        
         ///查找当前角色的菜单 
         SysRoleprivilege Rpdao=new SysRoleprivilege();
         Rpdao.setRolecode(sysrole.getRolecode());       
         List<SysRoleprivilege> Rplst = sysroleprivilegeService.selectByProperty(Rpdao);
        
         if (Rplst.size()==0)
         {        	
             ///根据全部权限内容加载菜单树 
             int i = 0;
             for(SysPrivilege pdao : allprivielgelst)
             {
                 if (i == allprivielgelst.size())
                 {
                     sb.append("{ id: " + pdao.getPrivilegecode() + ", pId:" + pdao.getParentcode() + ", name:\"" + pdao.getPrivilegename() + "\", open: true }");
                 }
                 else
                 {
                     sb.append("{ id: " + pdao.getPrivilegecode() + ", pId:" + pdao.getParentcode() + ", name: \"" + pdao.getPrivilegename() + "\", open: true },");
                 }
                 i++;
             }
         }
         else
         {
        	 String privilegelist="";
        	 int j=0;
        	 for(SysRoleprivilege rprivilege :Rplst)
        	 {
        		 if(j==Rplst.size())
        		 {
        			 privilegelist+=rprivilege.getPrivilegecode();
        		 }else
        		 {
        			 privilegelist+=rprivilege.getPrivilegecode()+",";
        		 }
        	 }        	
        	 result.addObject("privilegelist", privilegelist);            
             int i = 0;
             for (SysPrivilege pdao : allprivielgelst)
             {
                 if (i == allprivielgelst.size())
                 {
                     if (privilegelist.contains(pdao.getPrivilegecode()))
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name:\"" + pdao.getPrivilegename() + "\",checked: true, open: true }");
                     }
                     else
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name:\"" + pdao.getPrivilegename() + "\", open: true }");
                     }
                 }
                 else
                 {                	
                     if (privilegelist.contains(pdao.getPrivilegecode()))
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name: \"" + pdao.getPrivilegename() + "\",checked:true, open: true },");
                     }
                     else
                     {
                         sb.append("{ id: '" + pdao.getPrivilegecode() + "', pId:'" + pdao.getParentcode() + "', name: \"" + pdao.getPrivilegename() + "\", open: true },");
                     }
                 }
                 i++;
             }
         }
         result.addObject("MenuStr", sb.toString());       
         result.addObject("rolecode", sysrole.getRolecode());       
         return result;
     }
	 
	/**
	 * 权限保存
	 * @param privilegelist权限列表类似0101,0102,0202
	 * @param rolecode角色编码
	 * @return
	 */
	@RequestMapping(value="grantsave",method=RequestMethod.POST)
	@ResponseBody
	 public Map<String, Object> grantsave(String privilegelist, String rolecode)
     {
		Map<String, Object> result = new HashMap<String, Object>();
		if(sysroleprivilegeService.rolegrant(privilegelist, rolecode))
		{
			result.put("IsSuccess", true);
			result.put("Message", "授权成功");
		}else
		{
			result.put("IsSuccess", false);
			result.put("Message", "授权失败");
		}
		/*SysRoleprivilege record=new SysRoleprivilege();
		record.setRolecode(rolecode);
		boolean flag=true;
		if((sysroleprivilegeService.selectByProperty(record)).size()>0)//如果曾经有授权，先删除授权
		{
			int i=sysroleprivilegeService.delete(record);		
			if(i>0)//删除成功
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		if(flag)
		{
			List<SysRoleprivilege> recordList=new ArrayList<SysRoleprivilege>();
			String[] plst=privilegelist.split(",");
			for(int j=0;j<plst.length;j++)
			{
				SysRoleprivilege srdao=new SysRoleprivilege();
				srdao.setPrivilegecode(plst[j]);
				srdao.setRolecode(rolecode);
				recordList.add(srdao);			
			}
			int c=sysroleprivilegeService.insertList(recordList);//再授权	
			if(c>0)
			{
				result.put("IsSuccess", true);
				result.put("Message", "授权成功");
			}else
			{
				result.put("IsSuccess", false);
				result.put("Message", "授权失败");
			}
		}else
		{
			result.put("IsSuccess", false);
			result.put("Message", "权限删除失败");
		}*/
		return result;
        
     }
}
