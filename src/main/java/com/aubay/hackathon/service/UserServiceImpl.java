package com.aubay.hackathon.service;

import com.aubay.hackathon.enums.RoleEnum;
import com.aubay.hackathon.model.core.UserCore;
import com.aubay.hackathon.model.table.UserTable;
import com.aubay.hackathon.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository repository;

    private ModelMapper mapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<UserCore> get(Long id) {

        Optional<UserTable> userTable = repository.findById(id);
        if (userTable.isPresent()) {
            return Optional.of(mapper.map(userTable.get(), UserCore.class));
        }
        return Optional.empty();
    }

    @Override
    public UserCore save(UserCore userCore) {

        userCore.setRole(RoleEnum.GESTOR.getCode());
        userCore.setUserStatus(true);
        userCore.setPassword(bCryptPasswordEncoder.encode(userCore.getPassword()));

        return mapper.map(repository.save(mapper.map(userCore, UserTable.class)), UserCore.class);
    }
}
