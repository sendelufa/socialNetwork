package ru.skillbox.socialnetwork.config;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Profile("dev")
@Component
class ProfileDev {

   private final String QUERY_ALL_TABLES =
       "SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA=DATABASE();";
   private final String QUERY_COUNT_ROWS = "SELECT COUNT(*) FROM %s;";
   private Logger logger = LogManager.getLogger(ProfileDev.class);
   @Autowired
   private SessionFactory sessionFactory;

   @Value("${socialnetwork.test-data-SQL}")
   private Resource testDataSqlPath;

   void fillTestData() {
      try (Session session = sessionFactory.openSession()) {
         List<String> allTables =
             session.createSQLQuery(QUERY_ALL_TABLES).list();
         if (isEmptyDatabaseTables(allTables, session)) {
            StringBuilder sql = new StringBuilder();
            try {
               List<String> list = Files.readAllLines(Paths.get(testDataSqlPath.getURI()));
               list.forEach(sql::append);
            } catch (IOException e) {
               e.printStackTrace();
            }
            logger.info(sql.toString());
            logger.info("\t\t\tSQL TEST DATA FINISH");
            session.beginTransaction();
            session.createSQLQuery(sql.toString()).executeUpdate();
            session.getTransaction().commit();
         }
      }
   }

   private boolean isEmptyDatabaseTables(List<String> tables, Session session) {
      for (String table : tables) {
         String query = String.format(QUERY_COUNT_ROWS, table);
         if (table.equalsIgnoreCase("flyway_schema_history") ||
             table.equalsIgnoreCase("notification_type")) {
            continue;
         }
         if (((BigInteger) session.createSQLQuery(query).getSingleResult()).longValue() > 0) {
            System.out.println(query);
            return false;
         }
      }
      return true;
   }
}
