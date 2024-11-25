package main.mangment.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Для описания ошибки")
public class ErrorMessage {

  private String message;

  public ErrorMessage(String message) {
    this.message = message;
  }
}