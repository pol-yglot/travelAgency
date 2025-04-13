package com.example.travelagency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 파일 util 클래스
 * */
@Service
public class FileService {

    // 프로필 이미지 서버 저장 경로
    @Value("${file.profile-image-dir}")
    private String profileImageDirectory;

    public String getProfileImageDirectory() {
        return profileImageDirectory;
    }

    public void setProfileImageDirectory(String profileImageDirectory) {
        this.profileImageDirectory = profileImageDirectory;
    }

    public Path resolveFilePath(String directory, String filename) {
        return Paths.get(directory, filename);
    }

    public void saveFile(MultipartFile file, String directory, String filename) throws IOException {
        Path filePath = resolveFilePath(directory, filename);
        Files.createDirectories(Paths.get(directory)); // 디렉토리가 없으면 생성
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
