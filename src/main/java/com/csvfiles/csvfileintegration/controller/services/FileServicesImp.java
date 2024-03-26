package com.csvfiles.csvfileintegration.controller.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csvfiles.csvfileintegration.dao.UserDao;
import com.csvfiles.csvfileintegration.entity.User;

@Service
public class FileServicesImp implements FileServices {

	@Autowired
	private UserDao dao;

	@Override
	public boolean hashCSVFormate(MultipartFile file) {
		String type="text/csv";
		if(!type.equals(file.getContentType())) {
			return false;
		}			
		return true;
	}

	@Override
	public List<User> processAndSaveData(MultipartFile file) {	
		try {
			List<User> users=csvToUsers(file.getInputStream());
			List<User> saveAllUser = dao.saveAllUser(users);
			return saveAllUser;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<User> csvToUsers(InputStream inputStream) {
		List<User> users=new ArrayList<User>();
		try (BufferedReader file=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
				CSVParser csvParser=new CSVParser(file, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
				){
			List<CSVRecord> records = csvParser.getRecords();
			for (CSVRecord csvRecord : records) {
				User user=new User(Integer.parseInt(csvRecord.get("id")),csvRecord.get("name"),csvRecord.get("email"),csvRecord.get("password"),Long.parseLong(csvRecord.get("phone")));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
