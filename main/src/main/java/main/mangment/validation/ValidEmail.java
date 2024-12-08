package main.mangment.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация ValidEmail используется для валидации адресов электронной почты.
 *
 * <p>Эта аннотация может применяться к полям, классам и другим аннотациям для проверки,
 * что строка соответствует формату действительного адреса электронной почты.</p>
 * <p>При применении аннотации ValidEmail, будет использоваться EmailValidator
 * для выполнения проверки.</p>
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {

  /**
   * Сообщение об ошибке, которое будет возвращено, если адрес электронной почты невалиден.
   * @return сообщение об ошибке
   */
  String message() default "Неккоректно указана почта";

  /**
   * Группы валидации, к которым принадлежит данное ограничение.
   * @return массив групп валидации
   */
  Class<?>[] groups() default {};

  /**
   * Дополнительные данные, которые могут быть связаны с аннотацией.
   * @return массив полезной нагрузки
   */
  Class<? extends Payload>[] payload() default {};
}
