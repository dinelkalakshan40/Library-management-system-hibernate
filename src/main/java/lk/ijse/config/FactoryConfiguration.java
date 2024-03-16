package lk.ijse.config;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.entity.Transction;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

        private static FactoryConfiguration factoryConfiguration;

        private SessionFactory sessionFactory;

        private FactoryConfiguration() throws IOException {

            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Branch.class);
            configuration.addAnnotatedClass(Transction.class);

            sessionFactory = configuration.buildSessionFactory();
        }


        public static FactoryConfiguration getInstance() throws IOException {
            return(factoryConfiguration==null)?factoryConfiguration=
                    new FactoryConfiguration():factoryConfiguration;
        }
        public Session getSession(){
                return sessionFactory.openSession();
        }

}
