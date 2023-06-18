package com.masai.dao;

import java.util.List;

import com.masai.Utility.EMUtils;
import com.masai.entity.LoggedInUserId;
import com.masai.entity.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomeThingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDaoImp implements CustomerDao{

	@Override
	public void addCustomer(User user) throws SomeThingWentWrongException {
		 EntityManager em = null;
		    try {
		        em = EMUtils.getEntityManager();

		        // check if User with the same name exists
		        Query query = em.createQuery("SELECT count(c) FROM User c WHERE username = :userName");
		        query.setParameter("userName", user.getUsername());
		        List<User> list = query.getResultList();
		        System.out.println(list.size());
		        if (list.size()  > 1) {
		            // You are here means a User with the given name exists, so throw an exception
		            throw new SomeThingWentWrongException("Username already exists with name " + user.getUsername());
		        }

		        // You are here means no company with the given name
		        EntityTransaction et = em.getTransaction();
		        et.begin();
		        em.persist(user);
		        et.commit();
		    } catch (PersistenceException ex) {
//		        throw new SomeThingWentWrongException("Unable to process the request, please try again later");
		        System.out.println(ex.getMessage());
		    } finally {
		        
		    	 if (em != null) {
		             em.close();
		         }
		        
		    }
		
	}

	@Override
	public void login(String username, String password) throws SomeThingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtils.getEntityManager();
			
			Query query = em.createQuery("SELECT c.id FROM User c WHERE username = :username AND password = :password AND isdeleted = 0");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<Integer> listInt = (List<Integer>)query.getResultList();
			if(listInt.size() == 0) {
				//you are here means company with given name exists so throw exceptions
				throw new NoRecordFoundException("The username or password is incorrect");
			}
			LoggedInUserId.loggedInUserId = listInt.get(0);
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
		
	}

	@Override
	public User findCustomerWithID(int id) throws SomeThingWentWrongException, NoRecordFoundException {
		
		EntityManager em = null;
		User user = null;
		try {
			em = EMUtils.getEntityManager();
			Query query = em.createQuery("SELECT c FROM User c WHERE id = :userId AND isdeleted = 0");
			query.setParameter("userId", id);
			user = (User) query.getSingleResult();
//			System.out.println(user);
			if(user == null) {
				throw new NoRecordFoundException("The user With the Given Id does not Exist");
			}
			
		}catch(PersistenceException ex) {
			throw new SomeThingWentWrongException("Unable to process request, try again later");
		}finally {
			em.close();
		}
		
		return user;
		
		
	}
	
	

}
