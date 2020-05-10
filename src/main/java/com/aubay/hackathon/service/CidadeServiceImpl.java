package com.aubay.hackathon.service;

import com.aubay.hackathon.model.table.CidadeTable;
import com.aubay.hackathon.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl implements ICidadeService {

    private final CidadeRepository repository;

    @Autowired
    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CidadeTable> getCidades() {
        return repository.findAll();
    }
}
