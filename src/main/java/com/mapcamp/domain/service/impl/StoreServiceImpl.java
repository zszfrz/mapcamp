package com.mapcamp.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.Store;
import com.mapcamp.domain.repository.StoreRepository;
import com.mapcamp.domain.service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store save(Store store){
        return storeRepository.save(store);
    }
    
    public Store saveAndFlush(Store store) {
    	return storeRepository.saveAndFlush(store);
    }
    
    @Override
    public Store findOneOrNew(String name){
    	Store store = storeRepository.findByName(name);
        if(store == null) store = new Store();
        return store;
    }

    @Override
    public Page<Store> findAll(Pageable pageable){
        return storeRepository.findAll(pageable);
    }
    
//    
//    
//    @Override
//    public Store findOne(Long id){
//        return storeRepository.findOne(id);
//    }

}

