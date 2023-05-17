package com.budgetify.service;

import com.budgetify.dao.SessionDao;
import com.budgetify.dao.UserDao;
import com.budgetify.dto.LoginDto;
import com.budgetify.dto.LoginResponseDto;
import com.budgetify.entity.Session;
import com.budgetify.entity.User;
import com.budgetify.exception.ApiException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
public class SessionService {
    private final UserDao baseUserDao;
    private final SessionDao baseSessionDao;

    public LoginResponseDto createSession(LoginDto loginDto) {
        RequestValidator.validate(loginDto);

        User user = baseUserDao.findByEmail(loginDto.getEmail());

        checkPassword(loginDto.getPassword(), user.getPassword());

        Session session = buildSession(user);

        baseSessionDao.save(session);

        return LoginResponseDto.builder()
                .email(user.getEmail())
                .session(session.getId())
                .build();
    }

    private Session buildSession(User user) {
        String sessionId = generateSessionId(user);

        return Session.builder()
                .id(sessionId)
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .closedAt(null)
                .status("A")
                .build();
    }

    private void checkPassword(String requestedPassword, String actualEncryptedPassword) {
        String actualPassword = PasswordEncryption.decryptPassword(actualEncryptedPassword);

        if (!requestedPassword.equals(actualPassword)) {
            throw new ApiException("Incorrect password.");
        }
    }

    private String generateSessionId(User user) {
        int userHashcode = Math.abs(user.hashCode());
        int nowHashcode = Math.abs(LocalDateTime.now().hashCode());
        int randomHashcode = Math.abs(Integer.hashCode(new Random().nextInt()));
        String randomString1 = StringUtils.generateRandomString(10);
        String randomString2 = StringUtils.generateRandomString(10);

        return String.format("%s-%s-%s-%s-%s", userHashcode, randomString1, nowHashcode, randomString2, randomHashcode);
    }
}
