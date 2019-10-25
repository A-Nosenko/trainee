package app.config;

import app.model.ConnectionHolder;
import app.model.TreeHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 * Spring configuration class to declare beans.
 */
@Configuration
public class AppConfig {

    @Scope(scopeName = WebApplicationContext.SCOPE_APPLICATION)
    @Bean
    public ConnectionHolder connectionHolder() {
        return new ConnectionHolder();
    }

    @Scope(scopeName = WebApplicationContext.SCOPE_APPLICATION)
    @Bean
    public TreeHolder treeHolder() {
        return new TreeHolder();
    }
}
