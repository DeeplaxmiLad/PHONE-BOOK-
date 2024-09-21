package com.servlets;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.entities.User;
import com.entities.Contact;

public class FactoryProvider {

    private static SessionFactory factory;

    // Method to get the Hibernate SessionFactory
    public static SessionFactory getFactory() {
        if (factory == null) {
            try {
                // Create a Configuration object
                Configuration config = new Configuration();
                
                // Load the Hibernate configuration file (hibernate.cfg.xml)
                config.configure("hibernate.cfg.xml");
                
                // Add the entity classes annotated with @Entity
                config.addAnnotatedClass(User.class);
                config.addAnnotatedClass(Contact.class);

                // Build the SessionFactory
                factory = config.buildSessionFactory();

            } catch (Exception e) {
                // Print stack trace if an exception occurs
                e.printStackTrace();
            }
        }
        return factory;
    }

    // Method to close the SessionFactory (optional, used when app is shutting down)
    public static void closeFactory() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}
