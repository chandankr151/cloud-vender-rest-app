package com.chandan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.model.CloudVender;
import com.chandan.response.ResponseHandler;
import com.chandan.service.CloudVenderService;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

	public CloudVenderService cloudVendorService;

	public CloudVendorController(CloudVenderService cloudVenderService) {
		this.cloudVendorService = cloudVenderService;
	}

	@GetMapping("home")
	public String getHome() {
		return "From Home";
	}

	@GetMapping("{vendorId}")
	public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
		return ResponseHandler.responseBuilder("Requested Vender Details are give here", HttpStatus.OK,
				cloudVendorService.getCloudVendor(vendorId));
	}

	@GetMapping(value = "/getAll")
	public List<CloudVender> getAllExistingCloudVendor() {
		return cloudVendorService.getAllCloudVendors();
	}

	@PostMapping
	public String createCloudVendorDetails(@RequestBody CloudVender cloudVendor) {
		String cloudVendor2 = cloudVendorService.createCloudVendor(cloudVendor);
		return cloudVendor2;
	}

	@PutMapping
	public String updateCloudVendorDetails(@RequestBody CloudVender cloudVendor) {
		String updated = cloudVendorService.updateCloudVendor(cloudVendor);
		return updated;
	}

	@DeleteMapping("{vendorId}")
	public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId) {
		String delete = cloudVendorService.deleteCloudVendor(vendorId);
		return delete;
	}
}