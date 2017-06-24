package com.worksApproval.admin.dao.common;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.common.base.Preconditions;


public abstract class AbstractHibernateDao<T extends Serializable> 
implements BaseDao<T> {
    
    private Class<T> clazz;
    
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }
    
    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
	@Override
    public final T findOne(final long id) {
        return (T)getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public final void create(final T entity) {
         Preconditions.checkNotNull(entity);
         // getCurrentSession().persist(entity);
         getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().update(entity);
        return entity;
        //return (T)getCurrentSession().merge(entity);
    }

    @Override
    public final void delete(final T entity) {
         Preconditions.checkNotNull(entity);
         getCurrentSession().delete(entity);
    }

    @Override
    public final void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
    
    
	// 获取所有实体
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}
	// 获取实体总数

	public long findCount(Class<T> entityClazz)
	{
		List<?> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		// 返回查询得到的实体总数
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}


		public long findCountByHql(String hql)
		{
		/*	List<?> l = find("select ifnull(count(1),0) from ( "
				+ hql+" ) as mycount");*/
			
			String hql2 ="select count(*) from ( "
					+ hql+" ) y";
					
			return (Long) this.getCurrentSession().createQuery(hql2).uniqueResult();
			
		}

	
	
	// 根据HQL语句查询实体
	@SuppressWarnings("unchecked")
	public  List<T> find(String hql)
	{
		return (List<T>)getCurrentSession()
			.createQuery(hql)
			.list();
	}
	// 根据带占位符参数HQL语句查询实体
	@SuppressWarnings("unchecked")
	public List<T> find(String hql , Object... params)
	{
		// 创建查询
		Query query = getCurrentSession()
			.createQuery(hql);
		// 为包含占位符的HQL语句设置参数
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
    
    }