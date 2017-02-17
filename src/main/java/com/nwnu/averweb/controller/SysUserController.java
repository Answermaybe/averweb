package com.nwnu.averweb.controller;

import com.nwnu.averweb.model.SysUser;
import com.nwnu.averweb.service.SysUserService;
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
@RequestMapping(value = "SysUser")
public class SysUserController {

	@Autowired
	private SysUserService sysuserService;
	
	@RequestMapping(value = "index")
	public String index() {		
		return "SysUser/index";
	}

	/***
	 * 列表
	 * 
	 * @param sysuser
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getList(SysUser sysuser,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows,
			@RequestParam(required = false, defaultValue = "id") String sort,
			@RequestParam(required = false, defaultValue = "asc") String order) {
		Example example = new Example(SysUser.class);		
		example.setOrderByClause(sort + " " + order);
		Map<String, Object> result = new HashMap<String, Object>();
		List<SysUser> sysuserList = sysuserService.pageListByExample(example,
				page, rows);
		int total = sysuserService.selectCount(sysuser);
		result.put("total", total);
		result.put("rows", sysuserList);
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param sysuser
	 * @return
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(SysUser sysuser) {
		ModelAndView result = new ModelAndView();
		if (sysuser.getId() != null) {
			sysuser = sysuserService.selectByKey(sysuser.getId());
		} 
		result.addObject("sysuser", sysuser);
		return result;
	}

	/***
	 * 保存，如果是现在，调用insert，如果是修改，调用updateByPrimaryKey
	 * 
	 * @param sysuser
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(SysUser sysuser) {
		Map<String, Object> result = new HashMap<String, Object>();		
		/*if(sysuser.getRolename()==null||sysuser.getRolename().equals(""))
		{
			result.put("IsSuccess", false);
			result.put("Message", "角色名称不能为空");
			return result;
		}
		if(sysuser.getRolecode()==null||sysuser.getRolecode().equals(""))
		{
			result.put("IsSuccess", false);
			result.put("Message", "角色编码不能为空");
			return result;
		}*/
		int i = 1;
		if (sysuser.getId() != null) {			
			i = sysuserService.updateByPrimaryKey(sysuser);
		} else {
			i = sysuserService.insert(sysuser);
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
		int i = sysuserService.deleteByPrimaryKey(id);
		if (i == 1) {
			result.put("IsSuccess", true);
			result.put("Message", "删除成功");
		} else {
			result.put("IsSuccess", false);
			result.put("Message", "删除失败");
		}
		return result;
	}

}
