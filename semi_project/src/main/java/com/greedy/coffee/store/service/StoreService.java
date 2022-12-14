package com.greedy.coffee.store.service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.best.entity.Beans;
import com.greedy.coffee.store.dto.StoreDTO;
import com.greedy.coffee.store.entity.Store;
import com.greedy.coffee.store.repository.StoreRepository;

@Service
@Transactional
public class StoreService {

	public static final int TEXT_PAGE_SIZE = 10;		//잘라올 컨텐츠 갯수
	public static final int TEXT_STORE_TYPE = 1;		//보드 타입
	public static final String SORT_BY = "stoName";		//정렬 기준
	public static final char ACTIVE_STATUS = 'Y';		//보여질 기본 상태


	private final StoreRepository storeRepository;
	private final ModelMapper modelMapper;	
	
	@Autowired
	public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
		this.storeRepository = storeRepository;
		this.modelMapper = modelMapper;
	}

	//store - List
	public Page<StoreDTO> selectStoreList(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY));
		Page<Store> storeList = null;
		
		
		if(searchValue != null && !searchValue.isEmpty()) {
			//검색어가 있는 상황
			storeList = storeRepository.findBySearchValue(TEXT_STORE_TYPE, ACTIVE_STATUS, searchValue, pageable);
		} else {
			//검색어가 없는 상황															
			storeList = storeRepository.findByStoTypeAndStoStatus(TEXT_STORE_TYPE, ACTIVE_STATUS, pageable);
		}
		
		return storeList.map(store -> modelMapper.map(store, StoreDTO.class));
	}

	
	//store - Regist
	public void registStore(StoreDTO store) {

		store.setStoType(TEXT_STORE_TYPE);
		storeRepository.save(modelMapper.map(store, Store.class));
		
	}


	//store - Update
	public void updateStore(StoreDTO store) {
			
		Store foundStore = storeRepository.findById(store.getStoCode()).get();
		foundStore.setStoName(store.getStoName());
		foundStore.setStoAdd(store.getStoAdd());
		foundStore.setBean(modelMapper.map(store.getBean(), Beans.class));	//joinColumn은 modelMapper 처리
		foundStore.setStoStatus(store.getStoStatus());
		foundStore.setCall(store.getCall());
	}
	

	//store - Remove
	public void removeStore(StoreDTO removeStore) {
		
		Store foundStore = storeRepository.findById(removeStore.getStoCode()).get();
		foundStore.setStoStatus('N');
	}	
		

		
		
	
	




	
	
	
	

	
	

	
	

	

	


	


	








}
