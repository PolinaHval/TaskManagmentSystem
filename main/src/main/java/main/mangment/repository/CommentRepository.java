package main.mangment.repository;

import main.mangment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс CommentRepository предоставляет методы для работы с сущностью комментария.
 *
 * <p>Этот интерфейс расширяет JpaRepository, что позволяет использовать
 * стандартные методы для выполнения операций CRUD (создание, чтение, обновление, удаление)
 * над комментариями.</p>
 */
@Repository
public interface CommentRepository  extends JpaRepository<Comment,Long> {
}
