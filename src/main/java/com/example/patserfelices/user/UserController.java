package com.example.patserfelices.user;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private CsvMapper mapper = new CsvMapper();
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/{username:.+}")
    public User getUserByUsername(@PathVariable String username) {
        System.out.println(username);
        return this.userRepository.findByUsername(username);
    }

    @PostMapping("/users/{username}")
    public void saveUser(User user) {
        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        this.userRepository.save(user);
    }

    @PostMapping("/users/csv")
    public List<User> saveUsersFromCSV(@RequestParam MultipartFile excelFile) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader reader = mapper.readerFor(User.class).with(schema);
        MappingIterator<User> iterator = reader.readValues(excelFile.getInputStream());
        List<User> users = iterator.readAll();
        encryptAllUsersPasswords(users);
        return userRepository.saveAll(users);
    }

    private void encryptAllUsersPasswords(List<User> users) {
        for( User user : users ){
            String password = user.getPassword();
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
        }
    }
}
