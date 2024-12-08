package main.mangment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений для контроллеров REST.
 *
 * <p>Этот класс перехватывает исключения,возвращает соответствующие сообщения
 * об ошибках с кодами состояния HTTP.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработчик для всех типов исключений.
     *
     * @param exception исключение, которое было выброшено
     * @return объект ErrorMessage с сообщением об ошибке
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleThrowableException(Throwable exception) {
        exception.printStackTrace();
        return new ErrorMessage("Ошибка сервера. " + exception.getMessage());
    }

    /**
     * Обработчик исключений валидации данных.
     *
     * @param exception исключение, вызванное ошибкой валидации аргументов метода
     * @return объект ErrorMessage с сообщением о проблемах с валидацией
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Данные не соответствуют требованиям валидации. " +exception.getMessage());
    }

    /**
     * Обработчик исключений, связанных с неверными данными для входа.
     *
     * @param exception исключение, вызванное неверными данными для входа
     * @return объект ErrorMessage с сообщением о неверных данных
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleLoginOrPasswordException(IncorrectDataException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Неверно указаны данные. " + exception.getMessage());
    }

    /**
     * Обработчик исключений, связанных с отсутствием объекта.
     *
     * @param exception исключение, вызванное отсутствием объекта
     * @return объект ErrorMessage с сообщением о том, что объект не найден
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Объект не найден. " + exception.getMessage());
    }

    /**
     * Обработчик исключений, связанных с недостаточными правами доступа.
     *
     * @param exception исключение, вызванное недостаточными правами
     * @return объект ErrorMessage с сообщением о недостатке прав
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePermissionDeniedException(NotEnoughRightsException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Недостаточно прав. " + exception.getMessage());
    }
}