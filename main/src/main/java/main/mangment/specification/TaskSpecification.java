package main.mangment.specification;

import main.mangment.model.Priority;
import main.mangment.model.Status;
import main.mangment.model.Task;
import main.mangment.model.User;
import org.springframework.data.jpa.domain.Specification;

/**
 * Класс TaskSpecification предоставляет спецификации для фильтрации задач.
 *
 * <p>Спецификации позволяют создавать динамические запросы к базе данных
 * на основе различных критериев, таких как автор, исполнитель, статус и приоритет.</p>
 */
public class TaskSpecification {

    /**
     * Создает спецификацию для поиска задач по автору.
     *
     * @param author автор задач
     * @return спецификация для поиска задач, созданных указанным автором
     */
    public static Specification<Task> findByAuthor(User author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author);
    }

    /**
     * Создает спецификацию для поиска задач по исполнителю.
     *
     * @param executor исполнитель задач
     * @return спецификация для поиска задач, назначенных указанному исполнителю
     */
    public static Specification<Task> findByExecutor(User executor) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor"), executor);
    }

    /**
     * Создает спецификацию для поиска задач по статусу.
     *
     * @param status статус задач
     * @return спецификация для поиска задач с указанным статусом
     */
    public static Specification<Task> findByStatus(Status status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    /**
     * Создает спецификацию для поиска задач по приоритету.
     *
     * @param priority приоритет задач
     * @return спецификация для поиска задач с указанным приоритетом
     */
    public static Specification<Task> findByPriority(Priority priority) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("priority"), priority);
    }
}
