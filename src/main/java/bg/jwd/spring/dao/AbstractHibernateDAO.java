package bg.jwd.spring.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 *   The DAO with Spring and Hibernate
 * - http://www.baeldung.com/2011/12/02/the-persistence-layer-with-spring-3-1-and-hibernate/
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public abstract class AbstractHibernateDAO<T extends Serializable> {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractHibernateDAO.class);

	protected Class<T> clazz;

	@Inject
	protected SessionFactory sessionFactory;


	@Transactional(readOnly = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		logger.debug("--- setSessionFactory!");
		this.sessionFactory = sessionFactory;
	}

	@PostConstruct
	public void postConstruct() {
		logger.debug("PostConstruct");
		logger.debug("sessionfactory = " + sessionFactory);
	}

	@Transactional(readOnly = true)
	public void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	@Transactional(readOnly = true)
	public T findById(final long id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getSession().createQuery("FROM " + clazz.getName()).list();
	}

//	@Transactional
	public void save(final T entity) {
		Session session = getSession();
//		Transaction tr = session.beginTransaction();
		session.save( entity );
		session.flush();
//		tr.commit();
	}
//	@Transactional
	public void saveOrUpdate(final T entity) {
		Session session = getSession();
//		Transaction tr = session.beginTransaction();
		session.saveOrUpdate( entity );
		session.flush();
//		tr.commit();
	}

	@SuppressWarnings("unchecked")
//	@Transactional
	public T update(final T entity) {
		return (T) getSession().merge( entity );
	}

//	@Transactional
	public void delete(final T entity) {
		getSession().delete( entity );
	}

//	@Transactional
	public void deleteById(final long id) {
		final T entity = findById(id);
		delete( entity );
	}

	///////////////////
	// Helper methods
	public final Session getSession() {
		return getNewSession();
//		return getCurrentSession();
	}
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	protected final Session getNewSession() {
//		logger.debug("--- sessionFactory = " + sessionFactory);
		return sessionFactory.openSession();
	}
}