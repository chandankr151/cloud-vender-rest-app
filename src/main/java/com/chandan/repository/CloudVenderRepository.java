package com.chandan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandan.model.CloudVender;

public interface CloudVenderRepository extends JpaRepository<CloudVender,String> {
	List<CloudVender> findByVendorName(String venderName);
}
