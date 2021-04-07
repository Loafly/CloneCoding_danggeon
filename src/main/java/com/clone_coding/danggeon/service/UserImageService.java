package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.models.User;
import com.clone_coding.danggeon.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserImageService {
    private final UserRepository userRepository;

    public UserImageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public String getFullPath(String originFileName,String rootPath){
        mkDir(rootPath);
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        // 년월일시분초 14자리 포멧
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(fourteen_format.format(date_now)); // 14자리 포멧으로 출력한다

        return fourteen_format.format(date_now) + originFileName ;
    }

    public void mkDir(String path){
        path.replace("/","\\");
        System.out.println(path);

        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                System.out.println(Folder);
                Folder.mkdir(); //폴더 생성합니다.
                System.out.println("폴더가 생성되었습니다.");
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }else {
            System.out.println("이미 폴더가 생성되어 있습니다.");
        }
    }

    public User findByName(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
