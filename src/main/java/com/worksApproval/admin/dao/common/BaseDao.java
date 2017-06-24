package com.worksApproval.admin.dao.common;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {
	T findOne(final long id);

	List<T> findAll();

	void create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final long entityId);

	/**
	 * 查找全部
	 * @param entityClazz
	 * @return 集合
	 */
	public List<T> findAll(Class<T> entityClazz);

	/**
	 * 返回查询得到的实体总数
	 * @param entityClazz
	 * @return 实体总数
	 */
	public long findCount(Class<T> entityClazz);

	/*
	 * 统计
	 */
	public long findCountByHql(String hql);
	/**
	 *  根据HQL语句查询实体
	 * @param hql
	 * @return
	 */
	List<T> find(String hql);
	/**
	 * // 根据带占位符参数HQL语句查询实体 占位符从0开始
	 * @param hql
	 * @param params
	 * @return 集合
	 */
	List<T> find(String hql, Object... params);

}
