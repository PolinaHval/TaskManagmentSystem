package main.mangment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleThrowableException(Throwable exception) {
        exception.printStackTrace();
        return new ErrorMessage("Ошибка сервера. " + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Данные не соответствуют требованиям валидации. " +exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleLoginOrPasswordException(IncorrectDataException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Неверно указаны данные. " + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(NotFoundException exception) {
        exception.printStackTrace();
        return new ErrorMessage("Объект не найден. " + exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePermissionDeniedException(NotEnoughRightsException e) {
        e.printStackTrace();
        return new ErrorMessage("Недостаточно прав. " + e.getMessage());
    }
}