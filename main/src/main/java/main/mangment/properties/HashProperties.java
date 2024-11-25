package main.mangment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class HashProperties {

  @Value("${hashSecret}")
  private String secret;

  @Value("${complexity}")
  private int complexity;
}
