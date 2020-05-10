package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.UserCore;
import com.aubay.hackathon.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    private ModelMapper mapper;

    public UserDetailsServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCore userCore = mapper.map(repository.findByEmail(username), UserCore.class);
        return new User(userCore.getEmail(), userCore.getPassword(), Collections.emptyList());
    }
}
