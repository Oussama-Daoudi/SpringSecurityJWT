package com.example.security.user;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    public List<UserDto> getUserListWithPermissions() {
        List<User> userList = repository.findAll();
        List<UserDto> usersWithPermissions = new ArrayList<>();

        for (User user : userList) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstname(user.getFirstname());
            userDto.setLastname(user.getLastname());
            userDto.setEmail(user.getEmail());

            userDto.setPermissions(user.getAuthorities());

            usersWithPermissions.add(userDto);
        }

        return usersWithPermissions;
    }
}
