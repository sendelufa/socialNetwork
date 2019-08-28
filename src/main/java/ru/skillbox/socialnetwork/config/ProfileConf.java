package ru.skillbox.socialnetwork.config;

import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Order(100)
@ConditionalOnProperty(
    value = "profile",
    havingValue = "dev")
public class ProfileConf {

   private final String QUERY_ALL_TABLES =
       "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA=DATABASE();";
   private final String QUERY_COUNT_ROWS = "SELECT COUNT(*) FROM %s;";

   @Autowired
   private SessionFactory sessionFactory;

   @Bean
   public void fillTestData() {
      try (Session session = sessionFactory.openSession()) {
         List<String> allTables =
             session.createSQLQuery(QUERY_ALL_TABLES).list();
         System.out.println(isEmptyDatabaseTables(allTables, session));
      }
   }

   private boolean isEmptyDatabaseTables(List<String> tables, Session session) {
      for (String table : tables) {
         String query = String.format(QUERY_COUNT_ROWS, table);
         if (((BigInteger) session.createSQLQuery(query).getSingleResult()).longValue() > 0) {
            System.out.println(query);
            return false;
         }
      }
      return true;
   }
}
