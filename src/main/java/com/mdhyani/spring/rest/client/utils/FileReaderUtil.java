package com.mdhyani.spring.rest.client.utils;

import com.mdhyani.spring.rest.client.dto.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    private FileReaderUtil() {
        throw new RuntimeException("can not initiate");
    }

    public static List<User> readFileWithBufferedReader(InputStream inputStream) throws IOException {
        List<User> users = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)))  {
            //skip first line
            bufferedReader.readLine();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split(",");
                User user = new User(arr[0], arr[1], Integer.valueOf(arr[2]));
                users.add(user);
            }
        }
        return users;
    }

    public static List<User> readFileWithFilesApi(Path path) throws IOException {
        return Files.readAllLines(path).stream()
                .skip(1)
                .map(line -> line.split(","))
                .map(arr -> new User(arr[0], arr[1], Integer.valueOf(arr[2]))).toList();
    }
}
