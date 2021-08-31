package com.fatihkocc9.sfgdi.config;


import com.fatihkocc9.pets.PetService;
import com.fatihkocc9.pets.PetServiceFactory;
import com.fatihkocc9.sfgdi.datasource.FakeDataSource;
import com.fatihkocc9.sfgdi.repository.EnglishGreetingRepository;
import com.fatihkocc9.sfgdi.repository.EnglishGreetingRepositoryImpl;
import com.fatihkocc9.sfgdi.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
public class GreetingServiceConfig {

    @Bean
    FakeDataSource fakeDataSource(@Value("${fatihkocc.username}") String username,
                                  @Value("${fatihkocc.password}") String password,
                                  @Value("${fatihkocc.jdbcUrl}") String jdbcUrl) {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setPassword(password);
        fakeDataSource.setUsername(username);
        fakeDataSource.setJdbcUrl(jdbcUrl);
        return fakeDataSource;
    }


    @Bean
    PetServiceFactory petServiceFactory() {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("dog");
    }

    @Profile("cat")
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory) {
        return petServiceFactory.getPetService("cat");
    }


    @Bean
    EnglishGreetingRepository englishGreetingRepository() {
        return new EnglishGreetingRepositoryImpl();
    }


    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Profile({"ES", "default"})
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService() {
        return new I18NSpanishService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService() {
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService() {
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService() {
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService() {
        return new SetterInjectedGreetingService();
    }
}
