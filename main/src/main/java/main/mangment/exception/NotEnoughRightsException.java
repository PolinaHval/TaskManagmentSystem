package main.mangment.exception;
/**
 * Исключение, представляющее ошибку, связанную с недостаточными правами доступа.
 *
 * <p>Это пользовательское исключение расширяет RuntimeException и
 * используется для обозначения случаев, когда у пользователя недостаточно прав
 * к запрашивваему ресурсу</p>
 */
public class NotEnoughRightsException extends RuntimeException {

  /**
   * Конструктор исключения с заданным сообщением.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public NotEnoughRightsException(String message) {
    super(message);
  }
}
