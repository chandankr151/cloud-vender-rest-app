package com.chandan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chandan.model.CloudVender;

@org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
public class CloudVenderRepositoryTest {

	@Autowired
	private CloudVenderRepository cloudVenderRepository;

	private CloudVender cloudVender;

	@BeforeEach
	void setUp() {

		cloudVender = new CloudVender("cloud_vender2", "ABC_Cloud2", "vendor_address", "+918709827474");

		cloudVenderRepository.save(cloudVender);
	}

	@AfterEach
	void tearDown() {

		cloudVender = null;
		cloudVenderRepository.deleteAll();
	}

	// SUCCESS TEST CASE

	@Test
	public void testFindByVendorName_Found() {

		List<CloudVender> cloudVenderList = cloudVenderRepository.findByVendorName("ABC_Cloud2");

		assertThat(cloudVenderList).isNotNull();
		assertThat(cloudVenderList.size()).isEqualTo(1);

		assertThat(cloudVenderList.get(0).getVendorId()).isEqualTo(cloudVender.getVendorId());

		assertThat(cloudVenderList.get(0).getVendorAddress()).isEqualTo(cloudVender.getVendorAddress());
	}

	// FAILURE TEST CASE

	@Test
	public void testFindByVendorName_NotFound() {

		List<CloudVender> cloudVenderList = cloudVenderRepository.findByVendorName("Cloud2");

		assertThat(cloudVenderList.isEmpty()).isTrue();
	}
}