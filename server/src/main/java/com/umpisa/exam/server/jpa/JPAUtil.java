package com.umpisa.exam.server.jpa;

import com.umpisa.exam.server.Config;
import org.slf4j.*;

import javax.persistence.*;
import java.util.*;
import java.util.function.*;

public class JPAUtil {
  private static final Logger log = LoggerFactory.getLogger(JPAUtil.class);
  private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
  private static EntityManagerFactory emFactory;

  private JPAUtil() {

  }

  public static EntityManager getEntityManager() {
    return Optional.ofNullable(threadLocal.get())
      .orElseGet(() -> {
        final EntityManager em = emFactory.createEntityManager();
        threadLocal.set(em);
        return em;
      });
  }

  public static Properties testProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.connection.url", "jdbc:h2:mem:");
    props.setProperty("hibernate.connection.username", "sa");
    props.setProperty("hibernate.connection.password", "");
    props.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    props.setProperty("hibernate.show_sql", "false");
    return props;
  }

  public static void initTest(Config config) {
    Thread.currentThread().setContextClassLoader(JPAUtil.class.getClassLoader());
    emFactory = Persistence.createEntityManagerFactory("laf", config.getProperties());
  }

  public static void init(Config config) {
    Thread.currentThread().setContextClassLoader(JPAUtil.class.getClassLoader());
    emFactory = Persistence.createEntityManagerFactory("laf", config.getProperties());
  }

  public static void transaction(Runnable closure) {
    transaction(() -> {
      closure.run();
      return null;
    });
  }

  public static <T> T transaction(Supplier<T> closure) {
    final EntityManager em = getEntityManager();
    final EntityTransaction trx = em.getTransaction();
    boolean owner = false;

    if(!trx.isActive()) {
      trx.begin();
      owner = true;
    }

    try {
      T result = closure.get();

      if (owner && trx.isActive()) {
        trx.commit();
        em.close();
        threadLocal.remove();
      }

      return result;
    }
    catch(Exception ex) {
      try {
        if(trx.isActive())
          trx.rollback();
      }
      catch(Exception e) {
        log.error("Exception", e);
      }

      throw ex;
    }
  }

  public static void shutdown() {
    if(emFactory.isOpen()) {
      try {
        emFactory.close();
      }
      catch(IllegalStateException ex) {
        log.error("IllegalStateException", ex);
      }
    }
  }
}