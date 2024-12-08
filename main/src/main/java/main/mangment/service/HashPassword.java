package main.mangment.service;

/**
 * Интерфейс HashPassword определяет методы для хеширования паролей
 * и проверки их корректности.
 */
public interface HashPassword {

  /**
   * Хеширует указанный пароль.
   *
   * @param password пароль, который необходимо хешировать
   * @return строка, представляющая хешированный пароль
   */
  String hashingPassword(String password);

  /**
   * Проверяет, соответствует ли введенный пароль его хешу.
   *
   * @param password введенный пользователем пароль
   * @param hashingPassword хешированный пароль для проверки
   * @return true, если пароль соответствует хешу, иначе false
   */
  boolean validatePassword(String password, String hashingPassword);
}
