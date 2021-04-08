package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.UserProfileUpdateDto;
import com.clone_coding.danggeon.handler.CustomMessageResponse;
import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.service.S3Service;
import com.clone_coding.danggeon.service.UserImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Transactional(readOnly = true)
public class ProfileController {
    private final UserImageService userImageService;
    private final S3Service s3Service;
    private final String IMAGEPATH = "src/main/resources/static/images/profile/";

    public ProfileController(UserImageService userImageService, S3Service s3Service) {
        this.userImageService = userImageService;
        this.s3Service = s3Service;
    }

    @GetMapping("/api/profile")
    public Object getUserFromToken(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        User user = userImageService.findByName(username);
        return user;
    }

    @PostMapping("/api/profile/update")
    @Transactional
    public Object updateUserProfile(HttpServletRequest request, @Valid @ModelAttribute UserProfileUpdateDto userProfileUpdateDto, BindingResult bindingResult) throws IOException { //프론트에서 토큰을 받아서 사용자 객체 알아야합니다.
        if (bindingResult.hasErrors()) {//username과 email에 유효성 검사가 통과되지 않을 때
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            String errorMessage = allErrors.get(0).getDefaultMessage();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomMessageResponse errors = new CustomMessageResponse(errorMessage, status.value());
            return ResponseEntity
                    .status(status)
                    .body(errors);
        }
        //request요청으로 들어온걸 토큰 인터셉터로 처리를하고 난 다음에 결과물이다.
        String username = (String) request.getAttribute("username");
        User user = userImageService.findByName(username);
        //파일이름을 시분초14자리를 만들고 다시 원래 파일이름을 뒤에 붙여준다.
//        String dateTimeFileName = userImageService.getFullPath(userProfileUpdateDto.getProfile_img().getOriginalFilename(), IMAGEPATH);

        MultipartFile multipartFile = userProfileUpdateDto.getProfile_img();
        String fileName = multipartFile.getOriginalFilename();
        String url = s3Service.upload(multipartFile);

//        File targetFile = new File(IMAGEPATH, dateTimeFileName);

        System.out.println("url = " + url);
        user.setProfile_img(url);
        user.setUsername(userProfileUpdateDto.getUsername());
        user.setEmail(userProfileUpdateDto.getEmail());

//        try {
//            System.out.println(userProfileUpdateDto.getProfile_img().getInputStream().getClass());
//            InputStream fileStream = userProfileUpdateDto.getProfile_img().getInputStream();
//            FileUtils.copyInputStreamToFile(fileStream, targetFile);
//
//            String pre_Path = "/static/images/profile/";
//
//            user.setUsername(userProfileUpdateDto.getUsername());
//            user.setEmail(userProfileUpdateDto.getEmail());
//            user.setProfile_img(pre_Path + dateTimeFileName);
//
//
//
//
//        } catch (IllegalStateException | IOException e) {
//            e.printStackTrace();
//        }
        return user;
    }
}

