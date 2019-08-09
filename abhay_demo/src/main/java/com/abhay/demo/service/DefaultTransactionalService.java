package com.abhay.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhay.demo.dao.DefaultDao;
import com.abhay.demo.model.TestRegistration;
import com.abhay.demo.model.User;

@Service
public class DefaultTransactionalService {

	@Autowired
	private DefaultDao defaultDao;

	@Transactional(readOnly = false)
	public void saveOrUpDate(TestRegistration testRegistration) {
		defaultDao.saveOrUpdateTestReg(testRegistration);
	}

	@Transactional(readOnly = true)
	public List<TestRegistration> getAllRecord() {
		return defaultDao.getAllRecord();
	}
	
	@Transactional(readOnly = true)
	public User getUserInfoByMobileAndStatus(String mobile,boolean status) {
		
		return defaultDao.getUserInfoByMobileAndStatus(mobile,status);
	}
}
