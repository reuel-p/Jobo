package com.jobo.jentry.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable,FK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	private final Class<T> fpersistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.fpersistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T getByKey(PK p_key) {
		return (T) getSession().get(persistentClass, p_key);
	}
	
	@SuppressWarnings("unchecked")
	public T getByFKey(FK p_key) {
		return (T) getSession().get(fpersistentClass, p_key);
	}

	public void persist(T p_entity) {
		getSession().persist(p_entity);
	}
	
	public void update(T p_entity) {
		getSession().update(p_entity);
	}

	public void delete(T p_entity) {
		getSession().delete(p_entity);
	}
	
	protected Criteria createEntityCriteria(){
		return getSession().createCriteria(persistentClass);
	}
}
