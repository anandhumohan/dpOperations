package com.chaipoint.hibernatehelper;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;

import com.chaipoint.constants.Constants;
//import com.chaipoint.internalApi.ChaipointInternalApiImpl;
//import com.chaipoint.internalApi.wrapper.ChaipointInternalApi;
//import com.chaipoint.utils.GetPropertyValues;

public class HibernateOperations {
	private static final Logger logger = Logger.getLogger(HibernateTemplate.class);

	static SessionFactory factory = null;
	private Session session = null;
	private Transaction txn = null;
	private static boolean smsSent = false;

	public Session getSession() {
		try {
			if (session == null || !session.isOpen()) {
				session = factory.openSession();
				txn = session.beginTransaction();
			}
		} catch (JDBCConnectionException e) {

			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while getSession "+e.getMessage());
			
			factory.close();
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			txn = session.beginTransaction();

		}
		return session;
	}
	

	public HibernateOperations() {
		if (factory == null || factory.isClosed()) {
			factory = new Configuration().configure().buildSessionFactory();

		}
		//getSession();
	}

	private void postAction() {
		txn.commit();
	}

	private void postSession() {
		if (session.isOpen())
			session.close();

	}

	public String save(Object obj) throws Exception {

		getSession();
		try {
			session.save(obj);
			postAction();
		} catch (JDBCConnectionException e) {
			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while save Object "+e.getSQL());

			throw new Exception(e);
		} catch (ConstraintViolationException e) {
			// t.rollback();
			logger.error("Duplicate Value Exception while saving " + obj + "using Hibernate Template\n" + e);
			System.out.println("Duplicate Value Exception while saving " + obj + "using Hibernate Template\n" + e);
			// session.clear();
			throw new ConstraintViolationException("Duplicate Value Exception while saving", new SQLException(), obj.getClass()+"");
		}catch (Exception e) {
			// t.rollback();
			logger.error("Exception while saving " + obj + "using Hibernate Template\n" + e);
			System.out.println("Exception while saving " + obj + "using Hibernate Template\n" + e);
			// session.clear();
			throw new Exception(e);
		} finally {
			postSession();
		}

		return Constants.success;
	}

	public Object get(Class obj, Serializable param) {
		Object responseEntity = null;
		getSession();
		try {
			responseEntity = session.get(obj, param);
		} catch (JDBCConnectionException e) {
			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while get object with param"+e.getSQL());

		} catch (Exception e) {
			logger.error("Exception while fetching " + obj + "using Hibernate Template\n" + e);
			System.out.println("Exception while fetching " + obj + "using Hibernate Template");
			// session.clear();
			session.getTransaction().rollback();
			return Constants.error;
		} finally {
			// postAction();
			postSession();
		}
		return responseEntity;
	}

	public Object get(Class obj) {
		Object responseEntity = null;
		getSession();
		try {
			responseEntity = session.createCriteria(obj).setCacheMode(CacheMode.REFRESH).setCacheable(true).list();
		} catch (JDBCConnectionException e) {
			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while get a class "+e.getSQL());

		} catch (Exception ex) {
			logger.error("Exception while fetching " + obj + "using Hibernate Template");
			System.out.println("Exception while fetching " + obj + "using Hibernate Template");
			// session.clear();
			return Constants.error;
		} finally {
			// postAction();
			postSession();
		}

		return responseEntity;
	}

	public Object get(Criteria cr) {
		Object responseEntity = null;
		getSession();
	//	cr.setCacheMode(CacheMode.GET);
		try {
			responseEntity = cr.list();
			smsSent = false;
		} catch (JDBCConnectionException e) {
			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while getting criteria "+e.getSQL());

		} catch (Exception ex) {
			logger.error("Exception while fetching data using Hibernate Template " + ex);
			System.out.println("Exception while fetching data using Hibernate Template " + ex);
			// session.clear();
			return Constants.error;
		} finally {
			// postAction();
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
			postAction();
		} catch (JDBCConnectionException e) {
			logger.error("JDBC Connection Exception   " + e.getStackTrace());
			System.out.println("JDBC Connection Exception   " + e.getStackTrace());
		//	sendJDBCExceptionAlert("while delete "+e.getSQL());

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
			postSession();
		}

		return Constants.success;
	}
/*
	public static void sendJDBCExceptionAlert(String cause) {

		if (!smsSent) {
			ChaipointInternalApi api = new ChaipointInternalApiImpl();
			GetPropertyValues prop=new GetPropertyValues();
			String MSG = "JDBC Connection Error - Chaipoint "+prop.getPropValues("server")+" Server @ " +new Date() + "  Cause : "+cause;
			api.handleGetApi("http://boancomm.net/boansms/boansmsinterface.aspx?mobileno=918050132710&smsmsg=" + MSG
					+ "&uname=chaipoint&pwd=chaipoint14sms&pid=540");
			api.handleGetApi("http://boancomm.net/boansms/boansmsinterface.aspx?mobileno=919019748268&smsmsg=" + MSG
					+ "&uname=chaipoint&pwd=chaipoint14sms&pid=540");
			smsSent = true;
		}
	}
	*/
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
