package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Autowired
    BoardsService(BoardsRepository boardsRepository){
        this.boardsRepository = boardsRepository;
    }

    public Boards getBoard(Long id){
        return boardsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    public List<Boards> getBoardsSearch(String text){
        if (text.isEmpty()){
            throw new IllegalArgumentException("텍스트를 입력해 주세요.");
        }

        text = "%" + text + "%";
        return boardsRepository.findByTitleIsLikeOrContentsIsLike(text,text);
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

    public String getFullPath(String rootPath, String originFileName){
        String path = rootPath + "img";
        mkDir(path);
        Date date_now = new Date(System.currentTimeMillis()); // 현재시간을 가져와 Date형으로 저장한다
        // 년월일시분초 14자리 포멧
        SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(fourteen_format.format(date_now)); // 14자리 포멧으로 출력한다

        return path + "/" + fourteen_format.format(date_now) + originFileName;
    }
}
