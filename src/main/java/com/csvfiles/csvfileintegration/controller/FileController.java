package com.csvfiles.csvfileintegration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csvfiles.csvfileintegration.controller.services.FileServices;
import com.csvfiles.csvfileintegration.dto.ResponseStructure;
import com.csvfiles.csvfileintegration.entity.User;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileServices fileServices;

	@PostMapping("/upload")
	public ResponseEntity<ResponseStructure<List<User>>> uploadFile(@RequestParam("file") MultipartFile file) {
		if (fileServices.hashCSVFormate(file)) {
			List<User> users=fileServices.processAndSaveData(file);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<List<User>>("Succesfully uploaded into the database", users));
		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseStructure<List<User>>("please upload valid csv file", null));
	}
}
