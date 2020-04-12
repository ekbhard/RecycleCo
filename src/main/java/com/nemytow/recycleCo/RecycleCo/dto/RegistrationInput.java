package com.nemytow.recycleCo.RecycleCo.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class RegistrationInput {

  @NonNull
  String username ;

  @NonNull
  String password;

}
