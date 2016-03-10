package com.chaipoint.hibernatehelper;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.chaipoint.constants.Constants;

public class HibernateOperations {
	private static final Logger logger = Logger.getLogger(HibernateOperations.class);

	static SessionFactory factory = null;
	private Session session = null;
	private Transaction txn = null;
	

	public Session getSession() {
		if (session == null || !session.isOpen()) {
			session = factory.openSession();
			txn = session.beginTransaction();
		}
		
		return session;
	}

	public HibernateOperations() {
		if (factory == null || factory.isClosed()) {
			factory = new Configuration().configure().buildSessionFactory();

		}
		getSession();
	}

	private void postAction() {
		txn.commit();
	}
	private void postSession(){
		if(session.isOpen())
		session.close();
		
	}

	public String save(Object obj) throws Exception {

		getSession();
		try {
			session.save(obj);
		} catch (Exception e) {
			// t.rollback();
			logger.error("Exception while saving " + obj + "using Hibernate Template\n" + e);
			System.out.println("Exception while saving " + obj + "using Hibernate Template\n" + e);
			// session.clear();
			throw new Exception(e);
		} finally {
			postAction();
			postSession();
		}

		return Constants.success;
	}

	public Object get(Class obj, Serializable param) {
		Object responseEntity = null;
		getSession();
		try {
			responseEntity = session.get(obj, param);
		} catch (Exception e) {
			logger.error("Exception while fetching " + obj + "using Hibernate Template\n" + e);
			System.out.println("Exception while fetching " + obj + "using Hibernate Template");
			// session.clear();
			return Constants.error;
		} finally {
			//postAction();
			postSession();
		}
		return responseEntity;
	}

	public Object get(Class obj) {
		Object responseEntity = null;
		getSession();
		try {
			responseEntity = session.createCriteria(obj).setCacheable(true).list();
		} catch (Exception ex) {
			logger.error("Exception while fetching " + obj + "using Hibernate Template");
			System.out.println("Exception while fetching " + obj + "using Hibernate Template");
			// session.clear();
			return Constants.error;
		} finally {
			//postAction();
			postSession();
		}

		return responseEntity;
	}

	public Object get(Criteria cr) {
		Object responseEntity = null;
		getSession();
		cr.setCacheable(true);
		try {
			responseEntity = cr.list();
		} catch (Exception ex) {
			logger.error("Exception while fetching data using Hibernate Template " + ex);
			System.out.println("Exception while fetching data using Hibernate Template " + ex);
			// session.clear();
			return Constants.error;
		} finally {
			//postAction();
			postSession();
		}

		return responseEntity;
	}

	public String delete(Criteria cr) {
		ArrayList<Object> responseEntity = null;
		getSession();
		try {
			responseEntity = (ArrayList<Object>) cr.list();
			for (int i = 0; i < responseEntity.size(); i++) {
				session.delete(responseEntity.get(i));
			}
		} catch (IndexOutOfBoundsException ex) {
			logger.error("IndexOutOfBoundsException while deleteing Entry from database " + ex);
			System.out.println("IndexOutOfBoundsException while deleteing Entry from database " + ex);

			// session.clear();
			return Constants.error;
		} catch (Exception ex) {
			logger.error("Exception while deleting data using Hibernate Template " + ex);
			System.out.println("Exception while deleting data using Hibernate Template " + ex);

			// session.clear();
			return Constants.error;
		} finally {
			postAction();
			postSession();
		}

		return Constants.success;
	}
	
	public String update(Object obj) {
		String response = Constants.success;
		getSession();
		try {
			session.update(obj);
			postAction();
		} catch (Exception ex) {
			logger.error("Exception while update with Error : " + ex);
			System.out.println("Exception while update with Error : " + ex);
			response = Constants.error;
		} finally {
			postSession();
		}
		return response;
	}

}
