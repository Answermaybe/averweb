package com.nwnu.averweb.controller;

import com.nwnu.averweb.model.SysPrivilege;
import com.nwnu.averweb.model.SysPrivilegeTree;
import com.nwnu.averweb.service.SysPrivilegeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "SysPrivilege")
public class SysPrivilegeController {

	@Autowired
	private SysPrivilegeService sysprivilegeService;

	@RequestMapping(value = "index")
	public String index() {
		return "SysPrivilege/index";
	}

	/***
	 * 列表
	 * 
	 * @param sysprivilege
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "treelist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getList(SysPrivilege sysprivilege,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int rows) {
		sysprivilege.setParentcode("0000");
		int total = sysprivilegeService.selectCount(sysprivilege);
		Example example = new Example(SysPrivilege.class);
		example.createCriteria().andEqualTo("parentcode", "0000");
		Map<String, Object> result = new HashMap<String, Object>();
		List<SysPrivilege> sysprivilegeList = sysprivilegeService
				.pageListByExample(example, page, rows);
		List<SysPrivilegeTree> treelst = new ArrayList<SysPrivilegeTree>();
		if (sysprivilegeList.size() > 0) {
			for (SysPrivilege sp : sysprivilegeList) {
				SysPrivilegeTree stree = new SysPrivilegeTree();
				stree.setId(sp.getId());
				stree.setIsshow(sp.getIsshow());
				stree.setParentcode(sp.getParentcode());
				stree.setPrivilegecode(sp.getPrivilegecode());
				stree.setPrivilegename(sp.getPrivilegename());
				stree.setRemark(sp.getRemark());
				stree.setSequence(sp.getSequence());
				stree.setUri(sp.getUri());
				SysPrivilege sdao = new SysPrivilege();
				sdao.setParentcode(sp.getPrivilegecode());
				List<SysPrivilege> slst = sysprivilegeService
						.selectByProperty(sdao);
				if (slst.size() > 0) {
					stree.setChildren(slst);
				}
				treelst.add(stree);
			}
		}

		result.put("total", total);
		result.put("rows", treelst);
		return result;
	}

	private List<SysPrivilege> getParentCodeLst(String selectvalue) {
		List<SysPrivilege> toplst = new ArrayList<SysPrivilege>();
		SysPrivilege sysprivilege = new SysPrivilege();
		sysprivilege.setPrivilegecode("0000");
		sysprivilege.setPrivilegename("顶级");
		toplst.add(sysprivilege);
		SysPrivilege sdao = new SysPrivilege();
		sdao.setParentcode("0000");
		List<SysPrivilege> flst = sysprivilegeService.selectByProperty(sdao);
		if (flst.size() > 0) {
			for (SysPrivilege dao : flst) {
				toplst.add(dao);
			}
		}
		return toplst;

	}

	// /获取编码
	@RequestMapping(value = "getCode", method = RequestMethod.GET)
	@ResponseBody
	public String getCode(@RequestParam String parentCode) {
		return sysprivilegeService.getCode(parentCode);
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param sysprivilege
	 * @return
	 */
	@RequestMapping(value = "view", method = RequestMethod.GET)
	public ModelAndView view(SysPrivilege sysprivilege) {
		ModelAndView result = new ModelAndView();
		if (sysprivilege.getId() != null) {
			sysprivilege = sysprivilegeService
					.selectByKey(sysprivilege.getId());
		} else {
			sysprivilege.setPrivilegecode(sysprivilegeService.getCode("0000"));
		}
		result.addObject("sysprivilege", sysprivilege);
		result.addObject("parentlist", getParentCodeLst("0000"));
		return result;
	}

	/***
	 * 保存，如果是现在，调用insert，如果是修改，调用updateByPrimaryKey
	 * 
	 * @param sysprivilege
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(SysPrivilege sysprivilege) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (sysprivilege.getParentcode() == null
				|| sysprivilege.getParentcode().equals("")) {
			result.put("IsSuccess", false);
			result.put("Message", "父级菜单不能为空");
			return result;
		}
		if (sysprivilege.getPrivilegecode() == null
				|| sysprivilege.getPrivilegecode().equals("")) {
			result.put("IsSuccess", false);
			result.put("Message", "菜单编码不能为空");
			return result;
		}
		if (sysprivilege.getPrivilegename() == null
				|| sysprivilege.getPrivilegename().equals("")) {
			result.put("IsSuccess", false);
			result.put("Message", "菜单名称不能为空");
			return result;
		}
		int i = 1;
		if (sysprivilege.getId() != null) {
			i = sysprivilegeService.updateByPrimaryKey(sysprivilege);
		} else {
			i = sysprivilegeService.insert(sysprivilege);
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
		int i = sysprivilegeService.deleteByPrimaryKey(id);
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
