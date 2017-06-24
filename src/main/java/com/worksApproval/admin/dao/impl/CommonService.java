package com.worksApproval.admin.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;


//这里可以把sql当做参数进来,因为这个地方的方法的功能是一样的而且直接使用sql作为参数减少了if的条件的判断
@Service
public class CommonService {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public final Session getOpenSession() {
		return sessionFactory.openSession();
	}

	/**
	 * 判断是否重复数据
	 * 
	 * @param Hql
	 *            在select count(1)后面拼接的字段,期间需要用到的占位符的地方用(?N)数字标记,从1开始
	 * @param param 占位符中的参数           
	 * @return true,是重复的数据,反之,不是
	 */
	public boolean isRepeate(String partHql, Object... param) {
		Session session = this.getOpenSession();
		Transaction  transaction = session.getTransaction();
		transaction.begin();
		StringBuffer hql = new StringBuffer();
		hql.append("select count(1) ");
		hql.append(partHql);

		Query query = session.createQuery(hql.toString());
		int i = 1;

		if (param != null) {
			for (Object p : param) {
				query.setParameter(i++ + "", p);
			}
		}
		Long count = (Long) query.uniqueResult();
		transaction.commit();
		session.close();
		if (count >= 1L) {
			return true;
		}
		return false;
	}
	
/**
 * delete
 */
	/**
	 * 简单的删除方法,第二个参数是由逗号分割的数据,
	 * @param partHql hql
	 * @param param 逗号分割的id
	 * @param placeHolderName 占位符的名字
	 */
	public void simpleDelete(String partHql, String param,String placeHolderName) {
		Session session = this.getOpenSession();
		Transaction  transaction = session.getTransaction();
		transaction.begin();
		StringBuffer hql = new StringBuffer();
		hql.append(partHql);

		Query query = session.createQuery(hql.toString());
		int i = 1;

		List<Long> idList = new ArrayList<Long>(10);
		if (param != null) {
			for (String id : param.toString().split(",")) {
				idList.add(Long.valueOf(id));
			}
			query.setParameterList(placeHolderName,idList);
		}
		
		query.executeUpdate();
		
		transaction.commit();
		session.close();
	}
	
	public int checkUseCount(String id)
	{
		Object result = -1;
		Session session = this.getOpenSession();
		Transaction t = session.beginTransaction();
		t.begin();
		SQLQuery sqlQuery = session.createSQLQuery("select count(1) from user_info c where c.role_id = "+id);
		result = sqlQuery.uniqueResult();
		t.commit();
		session.close();
		return Integer.parseInt(result.toString());
	}
}
