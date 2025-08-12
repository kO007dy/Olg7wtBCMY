// 代码生成时间: 2025-08-12 20:52:11
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Primary;
import java.sql.SQLException;

/**
 * DatabaseConnectionPoolManager is a Spring Boot application that demonstrates
 * the management of a database connection pool using Apache Commons DBCP.
 */
@SpringBootApplication
public class DatabaseConnectionPoolManager {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseConnectionPoolManager.class, args);
    }

    /**
     * Creates a primary DataSource using Apache Commons DBCP.
     * @return The primary DataSource.
     */
    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Set the JDBC driver class
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase"); // Set the database URL
        dataSource.setUsername("username"); // Set the database username
        dataSource.setPassword("password"); // Set the database password

        // Set additional properties as needed, such as maxActive, maxIdle, etc.
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);

        try {
            dataSource.setLoginTimeout(60); // Timeout for establishing a connection
        } catch (SQLException e) {
            throw new RuntimeException("Failed to set login timeout for the DataSource", e);
        }

        return dataSource;
    }

    // Additional dataSource beans can be added here if needed for different environments or databases.
}
