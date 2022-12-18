package com.hotnerds.badgeroad.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotnerds.badgeroad.user.entity.User;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

   @NotNull
   @Size(min = 3, max = 50)
   @Email
   private String email;

   @NotNull
   @Size(min = 3, max = 100)
   private String password;

   @NotNull
   @Size(min = 3, max = 50)
   private String name;

   public static UserDto from(User user) {
      if(user == null) return null;

      return UserDto.builder()
              .email(user.getEmail())
              .password(user.getPassword())
              .name(user.getName())
              .build();
   }
}