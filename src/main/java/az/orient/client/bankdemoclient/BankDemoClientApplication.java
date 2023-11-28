package az.orient.client.bankdemoclient;

import az.orient.client.bankdemoclient.filter.FilterData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankDemoClientApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BankDemoClientApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BankDemoClientApplication.class);
    }

    @Bean
    FilterRegistrationBean<FilterData> filter (){
        final FilterRegistrationBean<FilterData> filterRegistration = new FilterRegistrationBean<>();

        filterRegistration.setFilter(new FilterData());
        filterRegistration.addUrlPatterns("/app/**");

        return filterRegistration;
    }

}
