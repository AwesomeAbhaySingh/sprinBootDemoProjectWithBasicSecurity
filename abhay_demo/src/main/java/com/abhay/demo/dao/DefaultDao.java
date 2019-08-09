package com.abhay.demo.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.abhay.demo.model.TestRegistration;
import com.abhay.demo.model.User;

@Repository
public class DefaultDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrUpdateTestReg(TestRegistration testRegistration) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(testRegistration);

	}
	
	public List<TestRegistration> getAllRecord() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TestRegistration.class);
		List<TestRegistration> list = criteria.list();
		if (!list.isEmpty()) {
			return list;
		}
		return Collections.EMPTY_LIST;
	}

	public User getUserInfoByMobileAndStatus(String mobile, boolean status) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("mobile", mobile));
		criteria.add(Restrictions.eq("status", status));
		List<User> list = criteria.list();
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
