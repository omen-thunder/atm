package KingsATM.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig
        implements WebMvcConfigurer
//        , WebServerFactoryCustomizer<ConfigurableWebServerFactory>
{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/notFound").setViewName("forward:/index.html");
    }

//    @Override
//    public void customize(ConfigurableWebServerFactory factory) {
//
////        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/notFound");
////        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/notFound");
//        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/notFound");
//
//        factory.addErrorPages(
////                error401Page,
////                error403Page,
//                error404Page
//        );
//    }
}
