package com.nemytow.recycleCo.RecycleCo.dto;

import lombok.*;

import java.util.Set;

@Value
public class RegistrationData {

  @NonNull
  String username ;

  @NonNull
  String password;

}
