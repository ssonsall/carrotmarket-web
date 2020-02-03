package com.bitc502.grapemarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Search;
import com.bitc502.grapemarket.repository.SearchRepository;

@Service
public class SearchService {
	
	@Autowired
	private SearchRepository sRepo;

	public List<Search> getPopularKeyWords() {
		List<Search> searchs = new ArrayList<Search>();
		try {
			searchs = sRepo.popularKeyword();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchs;
	}

}
