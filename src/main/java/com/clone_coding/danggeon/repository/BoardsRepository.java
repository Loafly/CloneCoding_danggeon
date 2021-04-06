package com.clone_coding.danggeon.repository;

import com.clone_coding.danggeon.models.Boards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardsRepository extends JpaRepository<Boards,Long> {
    Optional<Boards> findById(Long id);
    List<Boards> findByTitleIsLikeOrContentsIsLike(String title, String contents);
}
