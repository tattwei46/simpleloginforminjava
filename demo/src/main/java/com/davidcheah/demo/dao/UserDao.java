package com.davidcheah.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.davidcheah.demo.model.User;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	EntityManager entityManager;
	
	String findUserByNameQuery = "from User WHERE username=?1";

	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	public List<User> findAll() {
		TypedQuery<User> namedQuery = entityManager.createNamedQuery("find_all_users", User.class);
		return namedQuery.getResultList();
	}

	public User findByUsername(String username) {
		try {
			TypedQuery<User> tq = entityManager.createQuery(findUserByNameQuery, User.class);
			User user = tq.setParameter(1, username).getSingleResult();
			return user;
		} catch (NoResultException noresult) {
			// if there is no result
		} catch (NonUniqueResultException notUnique) {
			// if more than one result
		}

		return null;
	}
	
	public User insert(User user) {
		return entityManager.merge(user);
	}

}
