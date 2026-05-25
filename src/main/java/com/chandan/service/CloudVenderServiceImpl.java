package com.chandan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chandan.exception.CloudVendorNotFoundException;
import com.chandan.model.CloudVender;
import com.chandan.repository.CloudVenderRepository;

@Service
public class CloudVenderServiceImpl implements CloudVenderService {

	private CloudVenderRepository repo;

	public CloudVenderServiceImpl(CloudVenderRepository repo) {
		this.repo = repo;
	}

	@Override
	public String createCloudVendor(CloudVender cloudVendor) {
		CloudVender save = repo.save(cloudVendor);
		return " "+save;
	}

	@Override
	public String updateCloudVendor(CloudVender cloudVendor) {
		CloudVender save = repo.save(cloudVendor);
		if (save != null) {
			return "update success";
		} else
			throw new CloudVendorNotFoundException("update failed");
	}

	@Override
	public String deleteCloudVendor(String cloudVenderId) {
		CloudVender findData = repo.findById(cloudVenderId).get();
		if (findData == null) {
			return "Can't delete as record is not available";
		} else {
			repo.deleteById(cloudVenderId);
			return "Vendor deleted successfully";
		}
	}

	@Override
	public CloudVender getCloudVendor(String cloudVenderId) {

		if (repo.findById(cloudVenderId).isEmpty()) {
			throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
		}
		return repo.findById(cloudVenderId).get();
	}

	@Override
	public List<CloudVender> getAllCloudVendors() {
		List<CloudVender> allVendors = repo.findAll();
		return allVendors;
	}
}