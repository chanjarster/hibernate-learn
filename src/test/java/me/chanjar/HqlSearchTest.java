package me.chanjar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Example testcase for Hibernate Search
 */
public class HqlSearchTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private static Logger log = LoggerFactory.getLogger( HqlSearchTest.class );

	@Before
	public void setUp() {
		initHibernate();
	}

	@After
	public void tearDown() {
		purge();
	}

	@Test
	public void testSearch() throws Exception {
    Query query = em.createQuery("select book from Book book where book.title like :title) order by book.title");
    query.setParameter("title", "t");
    List books = query.getResultList();
  }


	private void initHibernate() {
		emf = Persistence.createEntityManagerFactory( "hibernate-search-example" );
		em = emf.createEntityManager();
	}


	private void purge() {
    em.close();
		emf.close();
	}

}
