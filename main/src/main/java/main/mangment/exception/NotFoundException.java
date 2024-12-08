package main.mangment.exception;

/**
 * Исключение, представляющее ошибку, связанную с отсуствием объекта.
 *
 * <p>Это пользовательское исключение расширяет RuntimeException и
 * используется для обозначения случаев, когда объект отсутствует</p>
 */
public class NotFoundException extends RuntimeException{

  /**
   * Конструктор исключения с заданным сообщением.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public NotFoundException(String message) {
    super(message);
  }
}
