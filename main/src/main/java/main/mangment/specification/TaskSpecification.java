package main.mangment.specification;

import main.mangment.model.Priority;
import main.mangment.model.Status;
import main.mangment.model.Task;
import main.mangment.model.User;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> findByAuthor(User author) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author"), author);
    }

    public static Specification<Task> findByExecutor(User executor) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor"), executor);
    }

    public static Specification<Task> findByStatus(Status status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> findByPriority(Priority priority) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("priority"), priority);
    }
}
