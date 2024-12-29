package com.example.decofolio.domain.user.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "계정 아이디는 Null, 공백, 띄어쓰기를 가 불가합니다.")
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하여야 합니다.")
    @JsonProperty("accountId")
    private String accountId;

    @NotBlank(message = "비밀번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*()_+-=?/])[a-zA-Z0-9~!@#$%^&*()_+-=?/]{8,30}$", message = "비밀번호는 8자 이상 30자 이하여야 합니다.")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "이름은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 2, max = 30, message = "이름은 2자 이상 30자 이하여야 합니다.")
    @JsonProperty("name")
    private String name;

    @Min(value = 1, message = "나이는 1살 이상이어야 합니다.")
    @Max(value = 120, message = "나이는 120살 이하여야 합니다.")
    @JsonProperty("age")
    private Integer age;

    @NotBlank(message = "거주지는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 3, max = 50, message = "거주지는 3자 이상 50자 이하여야 합니다.")
    @JsonProperty("location")
    private String location;

}
