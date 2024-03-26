package com.csvfiles.csvfileintegration.controller.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.csvfiles.csvfileintegration.entity.User;

public interface FileServices {

	boolean hashCSVFormate(MultipartFile file);

	List<User> processAndSaveData(MultipartFile file);

	


}
