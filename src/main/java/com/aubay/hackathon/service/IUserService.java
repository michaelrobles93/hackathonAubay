package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.UserCore;

import java.util.Optional;

public interface IUserService {

    Optional<UserCore> get(Long id);

    UserCore save(UserCore userCore);
}
