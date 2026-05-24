package com.chandan.service;

import java.util.List;

import com.chandan.model.CloudVender;

public interface CloudVenderService {

	public String createCloudVendor(CloudVender cloudVendor);

	public String updateCloudVendor(CloudVender cloudVendor);

	public String deleteCloudVendor(String cloudVenderId);

	public CloudVender getCloudVendor(String cloudVenderId);
	
	public List<CloudVender> getAllCloudVendors();
	
}
