package com.tiffanywang.happy.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.tiffanywang.happy.data.entity.Guest;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

	public Guest findOne(Long geustId);
}