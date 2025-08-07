// 代码生成时间: 2025-08-08 04:26:54
package com.example.tools;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseMigrationTool implements CommandLineRunner {
    
    private final DataSource dataSource;
    
    public DatabaseMigrationTool(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            // Create Liquibase instance and perform migration
            Liquibase liquibase = createLiquibaseInstance(connection);
            liquibase.update("yourChangeLogFileName");
            System.out.println("Database migration completed.");
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Error occurred during database migration: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Creates a Liquibase instance with the provided connection.
     * 
     * @param connection The database connection.
     * @return A Liquibase instance.
     */
    private Liquibase createLiquibaseInstance(Connection connection) {
        return new Liquibase(
            "yourChangeLogFileName",
            new ClassLoaderResourceAccessor(),
            new JdbcConnection(connection)
        );
    }
}
