//package geteway.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseSourceConfig {
//
//	@Bean
//	public DataSource dataSource(){
//		HikariConfig config = new HikariConfig();
//
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/imdb_db");
//		config.setUsername("root");
//		config.setPassword("adm1234");
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//
//
//		// Konfigurasi Pool
//		config.setMaximumPoolSize(10);
//		config.setMinimumIdle(2);
//		config.setIdleTimeout(30000);     // 60 detik
//		config.setMaxLifetime(60000);   // 30 menit
//		config.setConnectionTimeout(3000); // 3 detik
//
//		return new HikariDataSource(config);
//	}
//}
