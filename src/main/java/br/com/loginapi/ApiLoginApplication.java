package br.com.loginapi;
import java.util.Map;

import br.com.loginapi.core.Login;
import br.com.loginapi.db.LoginDAO;
import br.com.loginapi.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class ApiLoginApplication extends Application<ApiLoginConfiguration> {
    public static void main(String[] args) throws Exception {
        new ApiLoginApplication().run(args);
    }

    private final HibernateBundle<ApiLoginConfiguration> hibernateBundle =
            new HibernateBundle<ApiLoginConfiguration>(Login.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ApiLoginConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void initialize(Bootstrap<ApiLoginConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<ApiLoginConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ApiLoginConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<ApiLoginConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(ApiLoginConfiguration configuration) {
                return configuration.getViewRendererConfiguration();
            }
        });
    }

    @Override
    public void run(ApiLoginConfiguration configuration, Environment environment) {
        final LoginDAO dao = new LoginDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new LoginResource(dao));
    }
}
