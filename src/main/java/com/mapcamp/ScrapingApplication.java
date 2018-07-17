package com.mapcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.mapcamp.scraping.Scraping;

@ComponentScan
@EnableAutoConfiguration
public class ScrapingApplication implements CommandLineRunner{

    @Autowired
    private Scraping scraping;

    @Override
    public void run(String... arg0) throws Exception {
        scraping.execute();
    }

    public static void main(String[] args) {
        SpringApplication.run(ScrapingApplication.class, args);
        System.exit(0);
    }
}
