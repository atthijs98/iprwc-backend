package iprwc;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import iprwc.api.User;
import iprwc.core.JwtAuthenticator;
import iprwc.core.JwtHelper;
import iprwc.resources.UserResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class iprwcApplication extends Application<iprwcConfiguration> {

    public static Jdbi jdbiConnection;

    public static void main(final String[] args) throws Exception {
        new iprwcApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "iprwc";
    }

    @Override
    public void initialize(Bootstrap<iprwcConfiguration> bootstrap) {
        bootstrap.addBundle(new MultiPartBundle());
    }

    @Override
    public void run(final iprwcConfiguration configuration, final Environment environment) throws ClassNotFoundException {
        setupJdbiConnection(environment, configuration.getDatabase());

        // For cross platform calls
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin,Authorization,auth");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Set jwt secret
        registerJwtAuth(configuration, environment);
        JwtHelper.jwtSecret = configuration.getJwtSecret();

        //register resources
        environment.jersey().register(new UserResource());
    }

    private void setupJdbiConnection(final Environment environment, DataSourceFactory dataSourceFactory) {
        final JdbiFactory jdbiFactory = new JdbiFactory();
        jdbiConnection = jdbiFactory.build(environment, dataSourceFactory, "postgresql");
    }

    private void registerJwtAuth(final iprwcConfiguration configuration, final Environment environment) {
        final byte[] jwtSecret = configuration.getJwtSecret();
        final JwtConsumer consumer = new JwtConsumerBuilder()
                .setAllowedClockSkewInSeconds(30)
                .setRequireExpirationTime()
                .setVerificationKey(new HmacKey(jwtSecret))
                .build();

        environment.jersey().register(new AuthDynamicFeature(
                new JwtAuthFilter.Builder<User>()
                        .setJwtConsumer(consumer)
                        .setPrefix("Bearer")
                        .setAuthenticator(new JwtAuthenticator())
                        .buildAuthFilter()
        ));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
