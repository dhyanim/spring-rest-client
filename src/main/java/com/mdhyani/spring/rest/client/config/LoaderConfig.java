package com.mdhyani.spring.rest.client.config;

import com.mdhyani.spring.rest.client.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.mdhyani.spring.rest.client.utils.FileReaderUtil.readFileWithBufferedReader;
import static com.mdhyani.spring.rest.client.utils.FileReaderUtil.readFileWithFilesApi;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class LoaderConfig {

    private final JsonMapper jsonMapper = new JsonMapper();

    private final ResourceLoader resourceLoader;

    @Bean
    public CommandLineRunner commandLineRunner()  {
        return (args -> {
            Resource resource = resourceLoader.getResource("classpath:data/user.json");
            User userJsonObj  =  jsonMapper.readValue(resource.getInputStream(), User.class);
            log.info("User loaded fom json file {}", userJsonObj);
            List<User> usersJsonList = jsonMapper.readValue(resourceLoader.getResource("classpath:data/users.json").getInputStream(), new TypeReference<List<User>>(){});
            log.info("usersJsonList loaded from json file {} ", usersJsonList);
            Resource resource1 = resourceLoader.getResource("classpath:data/users_data.csv");
            Path path = Path.of(resource1.getFile().getPath());
            List<User> userList = readFileWithFilesApi(path);
            log.info("UserList loaded from csv file {} ", userList);
            List<User> users = readFileWithBufferedReader(resource1.getInputStream());
            log.info("users loaded through buffered reader {} ", users);
        });
    }


}
