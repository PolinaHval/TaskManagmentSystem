package main.mangment.exception;

/**
 * Исключение, представляющее ошибку, связанную с невалидным токеном.
 */
public class AuthException extends RuntimeException {
  public AuthException(String message) {
    super(message);
  }
}
