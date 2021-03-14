package jm.task.core.jdbc.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        Configuration config = new Configuration();
        Properties prop = new Properties();
        prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        prop.put(Environment.URL, "jdbc:mysql://localhost:3306/dbtest?serverTimezone=UTC&useSSL=false");
        prop.put(Environment.USER, "root");
        prop.put(Environment.PASS, "root");
        prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        prop.put(Environment.SHOW_SQL, "true");
        prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        prop.put(Environment.HBM2DDL_AUTO, "create-drop");

        config.setProperties(prop)
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        ServiceRegistry servReg = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();

        sessionFactory = config.buildSessionFactory(servReg);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
