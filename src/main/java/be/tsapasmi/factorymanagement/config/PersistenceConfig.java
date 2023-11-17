package be.tsapasmi.factorymanagement.config;

import be.tsapasmi.factorymanagement.domain.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("SpellCheckingInspection")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "be.tsapasmi.factorymanagement.dal")
@EnableJpaAuditing
public class PersistenceConfig {

    @Bean
    AuditorAware<User> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
