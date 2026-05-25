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

import com.chandan.exception.CloudVendorNotFoundException;
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

	@GetMapping("/home")
	public ResponseEntity<?> getHome() {
		return ResponseHandler.responseBuilder("From Home, GetMapping has called", HttpStatus.ACCEPTED, new Object());
	}

	@GetMapping(value = "getById/{vendorId}")
	public ResponseEntity<Object> getCloudVendorDetails(@PathVariable(name = "vendorId") String vendorId) {
		try {
			CloudVender cloudVendor = cloudVendorService.getCloudVendor(vendorId);
			if (cloudVendor != null)
				return ResponseHandler.responseBuilder("Requested Vender Details are give here", HttpStatus.FOUND,
						cloudVendor);
			else
				return ResponseHandler.responseBuilder("CloudVendor couldn't found with given id", HttpStatus.NOT_FOUND,
						" ");
		} catch (Exception ex) {
			throw new CloudVendorNotFoundException("Error Occured");
		}
	}

	@GetMapping(value = "/getAll")
	public List<CloudVender> getAllExistingCloudVendor() {
		return cloudVendorService.getAllCloudVendors();
	}

	@PostMapping(value = "/create")
	public ResponseEntity<?> createCloudVendorDetails(@RequestBody CloudVender cloudVendor) {
		ResponseEntity<Object> responseBuilder = ResponseHandler.responseBuilder(
				"CloudVender Client created successfully", HttpStatus.CREATED,
				cloudVendor + "\n" + cloudVendorService.createCloudVendor(cloudVendor));
		return responseBuilder;
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateCloudVendorDetails(@RequestBody CloudVender cloudVendor) {

		try {
			String updated = cloudVendorService.updateCloudVendor(cloudVendor);
			if (updated != null) {
				return ResponseHandler.responseBuilder("CloudVendor updated successfully", HttpStatus.OK, cloudVendor);
			} else {
				return ResponseHandler.responseBuilder("Couldn't update record", HttpStatus.BAD_REQUEST, " ");
			}
		} catch (Exception ex) {
			throw new CloudVendorNotFoundException("Couldn't Update CloudVendor as error occured during update");
		}
	}

	@DeleteMapping(value = "delete/{vendorId}")
	public ResponseEntity<?> deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId) {

		try {
			CloudVender cloudVendor = cloudVendorService.getCloudVendor(vendorId);
			if (cloudVendor != null) {
				return ResponseHandler.responseBuilder("CloudVendor with given id is deleted successfully",
						HttpStatus.OK, cloudVendorService.deleteCloudVendor(vendorId) + cloudVendor);
			} else {
				return ResponseHandler.responseBuilder("Can't Delete CloudVendor with given id as record not found",
						HttpStatus.NOT_FOUND, "No Record is available");
			}
		} catch (Exception ex) {
			throw new CloudVendorNotFoundException("Couldn't saved your client as error occured during save");
		}
	}
}