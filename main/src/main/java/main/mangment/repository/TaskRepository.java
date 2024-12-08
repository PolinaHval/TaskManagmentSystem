package main.mangment.repository;

import main.mangment.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для управления сущностями Task.
 *
 * <p>Этот интерфейс расширяет JpaRepository для выполнения операций CRUD
 * и JpaSpecificationExecutor для поддержки спецификаций JPA, что позволяет
 * создавать динамические запросы к базе данных.</p>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
}
