package main.mangment.repository;

import main.mangment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment,Long> {
}
