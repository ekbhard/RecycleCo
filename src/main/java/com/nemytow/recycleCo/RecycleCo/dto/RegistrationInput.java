package com.nemytow.recycleCo.RecycleCo.dto;

import lombok.*;

import java.util.Set;

@Value
public class RegistrationInput {

  @NonNull
  String username ;

  @NonNull
  String password;

}
