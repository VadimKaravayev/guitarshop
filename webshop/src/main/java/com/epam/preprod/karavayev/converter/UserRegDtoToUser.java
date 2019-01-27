package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.dto.UserRegistrationDto;
import com.epam.preprod.karavayev.entity.User;

import java.util.Objects;

public class UserRegDtoToUser implements Converter<UserRegistrationDto, User> {

    @Override
    public User convert(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        user.setSubscription(Objects.nonNull(userRegistrationDto.getSubscription()));
        return user;
    }
}
