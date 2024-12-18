package main.mangment.model;

/**
 * Перечисление для представления статуса.
 *
 * <p>Это перечисление определяет пять уровней статуса</p>
 */
public enum Status {

  /**
   * Статус в ожидании.
   */
  WAITING,

  /**
   * Статус принято.
   */
  ACCEPTED,

  /**
   * Статус отклоненый.
   */
  REJECTED,

  /**
   * Статус в прогрессе.
   */
  IN_PROGRESS,

  /**
   * Статус заверешенный.
   */
  COMPLETED
}
