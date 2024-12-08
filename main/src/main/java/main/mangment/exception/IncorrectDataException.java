package main.mangment.exception;

/**
 * Исключение, представляющее ошибку, связанную с неверными данными.
 *
 * <p>Это пользовательское исключение расширяет RuntimeException и
 * используется для обозначения случаев, когда входные данные не соответствуют
 * ожидаемым требованиям или правилам валидации.</p>
 */
public class IncorrectDataException extends RuntimeException{

  /**
   * Конструктор исключения с заданным сообщением.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public IncorrectDataException(String message) {
    super(message);
  }
}
