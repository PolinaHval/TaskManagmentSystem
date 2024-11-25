package main.mangment.service;

public interface HashPassword {

  String hashingPassword(String password);

  boolean validatePassword(String password, String hashingPassword);
}
