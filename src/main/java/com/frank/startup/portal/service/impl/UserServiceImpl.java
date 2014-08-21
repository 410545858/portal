package com.frank.startup.portal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.startup.portal.dao.MyBatisDao;
import com.frank.startup.portal.entity.Menu;
import com.frank.startup.portal.entity.Privilege;
import com.frank.startup.portal.entity.Role;
import com.frank.startup.portal.entity.User;
import com.frank.startup.portal.entity.UserRole;
import com.frank.startup.portal.service.UserService;



/**
 * @ClassName:     UserServiceImpl.java
 * @Description:   TODO
 * @author         FrankWong
 * @version        V1.0  
 * @Date           2013-07-07 10:02:46 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MyBatisDao myBatisDao;

	@Override
	public void add(User entity) {
		myBatisDao.add("userMapper.add", entity);
		int userId = entity.getId();
		String roleIdsStr = entity.getRoleIds();
		if(StringUtils.isNotBlank(roleIdsStr)){
			myBatisDao.add("userMapper.add", entity);
			String[] roleIds = roleIdsStr.split("\\|");
	          for (String roleId : roleIds) {
	             UserRole userRole = new UserRole();
	             userRole.setUserId(entity.getId());
	             userRole.setRoleId(roleId);
	             myBatisDao.add("userMapper.addUserRole", userRole);
	          }
		}
	}

	@Override
	public void deleteById(int id) {
		myBatisDao.deleteById("userMapper.deleteUserRoleByUserId", id);
		myBatisDao.deleteById("userMapper.deleteById", id);
	}

	@Override
	public void deleteByIds(List<Integer> ids) {
		for(Integer id:ids){
			myBatisDao.deleteById("userMapper.deleteUserRoleByUserId", id);
		}
		myBatisDao.deleteByIds("userMapper.deleteByIds", ids);
	}

	@Override
	public void update(User entity) {
		String roleIdsStr = entity.getRoleIds();
		myBatisDao.update("userMapper.update", entity);
		if(StringUtils.isNotBlank(roleIdsStr)){
			myBatisDao.deleteById("userMapper.deleteUserRoleByUserId", entity.getId());
			String[] roleIds = roleIdsStr.split("\\|");
	          for (String roleId : roleIds) {
	             UserRole userRole = new UserRole();
	             userRole.setUserId(entity.getId());
	             userRole.setRoleId(roleId);
	             myBatisDao.add("userMapper.addUserRole", userRole);
			}
		}
	}

	@Override
	public User getById(int id) {
		User user = myBatisDao.getById("userMapper.getById", id);
		if(null!=user){
			List<UserRole> list = myBatisDao.getList("userRoleMapper.getRolesByUserId", id);
		      StringBuilder sb = new StringBuilder();
		      if (list != null && !list.isEmpty()) {
		         for (UserRole userRole : list) {
		            if (sb.length() > 0) {
		               sb.append("|");
		            }
		            sb.append(userRole.getRoleId());
		         }
		      }
		      user.setRoleIds(sb.toString());
		}
		return user;
	}

	@Override
	public User login(String phone, String password) {
		User user = new User();
		user.setPhone(phone);
		user.setPassword(password);

		User result = myBatisDao.get("userMapper.login", user);
		if (null != result) {
			List<Role> roleList = myBatisDao.getList("roleMapper.getByLoginUser", result);
			if (roleList == null || roleList.size() == 0) { // 角色不存在
				return result;
			}
			
			Set<Privilege> privilegeSet = new HashSet<>();
			// 设置角色权限
			for (Role role : roleList) {
				privilegeSet.addAll(getPrivilegesByRoleId(role.getId()));
			}

			result.getMenuList().addAll(getMenuListByPrivileges(privilegeSet));
			result.getPrivilegeList().addAll(privilegeSet);
		}
		
		return result;
	}
	
	@Override
	public List<Privilege> getPrivilegesByRoleId(String roleId) {
		return myBatisDao.<Privilege>getList("roleMapper.getPrivilegeByRoleId", roleId);
	}
	
	@Override
	public List<Menu> getMenuListByPrivileges(Set<Privilege> privileges) {
		List<String> privilegeIdSet = new ArrayList<>(privileges.size());
		for (Privilege i : privileges) {
			privilegeIdSet.add(i.getId());
		}
		
		List<Menu> allSubMenu = myBatisDao.getList("menuMapper.getList"); // 所有菜单
		Map<Integer, List<Menu>> parentMenus = new HashMap<>(); // {pid: [menu]}
		for (Menu m : allSubMenu) {
			if (privileges.contains(m.getPrivilegeId())) {
				if (m.getParent() > 0) {
					List<Menu> subMenu = parentMenus.get(m.getParent());
					if (null == subMenu) {
						subMenu = new ArrayList<>();
						parentMenus.put(m.getParent(), subMenu);
					}
					subMenu.add(m);
				}
			}
		}
		List<Menu> menuList = new ArrayList<Menu>();
		for (Integer parentMenuId : parentMenus.keySet()) {
			Menu parentMenu = myBatisDao.getById("menuMapper.getById", parentMenuId);
			if (null != parentMenu) {
				parentMenu.getSubMenus().addAll(parentMenus.get(parentMenuId));
				menuList.add(parentMenu);
			}
		}
		sort(menuList); // 父菜单排序
		for (Menu m : menuList) {
			if (m.getSubMenus() != null && m.getSubMenus().size() > 0) {
				sort(m.getSubMenus()); // // 子菜单排序
			}
		}
		
		return menuList;
	}
	
	/**
	 * 
	 * @Title:        sort 
	 * @Description:  对Menu 按Priority排序
	 * @param:        @param subMenus    
	 * @return:       void    
	 * @throws 
	 * @author        FrankWong
	 * @Date          2014年7月22日 上午11:32:06
	 */
	public void sort(List<Menu> subMenus) {
		Comparator<Menu> comparator = new Comparator<Menu>() {
			public int compare(Menu s1, Menu s2) {
				return s1.getPriority() - s2.getPriority();
			}
		};
		Collections.sort(subMenus,comparator);
	}
	
	@Override
	public <T> List<T> getList() {
		return myBatisDao.getList("userMapper.getList");
	}

	@Override
	public List<Role> getRoleList() {
		return myBatisDao.getList("roleMapper.getList");
	}

	@Override
	public User getByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}
}
