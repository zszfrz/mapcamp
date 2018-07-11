package com.mapcamp.domain.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.mapcamp.domain.entity.User;

public interface UserService {

	void save(User user);

	// アソシエーション保存
	User findOne(Long id);

	// 画像アップロード
	User save(User user, MultipartFile file) throws IOException;

	// 画像ダウンロード
	byte[] downloadProfileImage(Long userId) throws IOException;
}
