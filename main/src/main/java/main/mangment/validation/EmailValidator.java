package main.mangment.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmailValidator реализует интерфейс ConstraintValidator для проверки
 * корректности формата адреса электронной почты.
 *
 * <p>Класс использует регулярное выражение для валидации строки, а именно почту.
 * Если строка соответствует заданному шаблону, считается, что адрес электронной почты является действительным.</p>
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
      "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

  /**
   * Проверяет, является ли переданная строка корректным адресом электронной почты.
   *
   * @param email адрес электронной почты
   * @param context контекст валидации
   * @return true, если адрес электронной почты корректен и false в противном случае
   */
  @Override
  public boolean isValid(final String email, final ConstraintValidatorContext context) {
    return validateEmail(email);
  }

  /**
   * Проверяет, соответствует ли переданная строка формату адреса электронной почты.
   *
   * @param email адрес электронной почты
   * @return true, если адрес электронной почты соответствует формату и false в противном случае
   */
  private boolean validateEmail(final String email) {
    Matcher matcher = PATTERN.matcher(email);
    return matcher.matches();
  }
}
