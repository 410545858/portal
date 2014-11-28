/**
 * 
 */
package com.frank.startup.portal.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.startup.portal.dao.MyBatisDao;
import com.frank.startup.portal.entity.LoginRecord;
import com.frank.startup.portal.entity.User;
import com.frank.startup.portal.service.LoginRecordService;

/**
 * @author frankwong
 *
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

	@Autowired
	private MyBatisDao myBatisDao;
	
	
	@Override
	public boolean add(LoginRecord entry) {
		myBatisDao.add("loginRecordMapper.add", entry);
		User user = new User(new Date(),entry.getIp(),entry.getLoginName());
		myBatisDao.add("userMapper.updateLastLoginInfo", user);
		return true;
	}

}
