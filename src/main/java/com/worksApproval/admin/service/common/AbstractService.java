package com.worksApproval.admin.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.worksApproval.admin.dao.common.BaseDao;

@Transactional
public abstract class AbstractService<T extends Serializable> implements BaseDao<T>  
{
	//这里是想要父类使用子类的具体的DAO,rocky
	//获取DAO
	protected abstract BaseDao<T> getDao();

	@Override
	public T findOne(long id) {
		// TODO Auto-generated method stub
		return getDao().findOne(id);
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getDao().findAll();
	}

	@Override
	public void create(T entity) {
		// TODO Auto-generated method stub
		getDao().create(entity);
		
	}

	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return getDao().update(entity);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
		
	}

	@Override
	public void deleteById(long entityId) {
		getDao().deleteById(entityId);
		
	}

	@Override
	public List<T> findAll(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return getDao().findAll(entityClazz);
	}

	@Override
	public long findCount(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		return getDao().findCount(entityClazz);
	}

	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return getDao().find(hql);
	}

	@Override
	public List<T> find(String hql, Object... params) {
		// TODO Auto-generated method stub
		return getDao().find(hql, params);
	}

	@Override
	public long findCountByHql(String hql) {
		// TODO Auto-generated method stub
		return getDao().findCountByHql(hql);
	}
	
}
