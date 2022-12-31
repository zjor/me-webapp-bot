package com.github.zjor.telegram;

import com.github.zjor.ext.spring.ControllerUtils;
import com.github.zjor.ext.spring.aop.Log;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Slf4j
@RestController
@RequestMapping("api/profile")
public class ProfileController {

    private final ProfilePhotoService photoService;

    public ProfileController(ProfilePhotoService profilePhotoService) {
        this.photoService = profilePhotoService;
    }

    @Log
    @GetMapping("photo/{userId}")
    public void getProfilePhoto(
            @PathVariable("userId") Long userId,
            HttpServletResponse response) {
        try {
            var fileId = photoService.getProfilePhotoFileId(userId)
                    .orElseThrow(() -> ControllerUtils.notFound("No profile photo for user: " + userId));
            var imageUrl = photoService.getFileUrl(fileId);

            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            response.setContentType(connection.getContentType());
            IOUtils.copy(inputStream, response.getOutputStream());
            inputStream.close();
            response.getOutputStream().close();
        } catch (Exception e) {
            log.error("Failed to upload image: " + e.getMessage(), e);
            throw ControllerUtils.serverError(e.getMessage());
        }
    }

}
