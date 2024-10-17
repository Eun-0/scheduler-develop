package com.eun0.schedulerdevelop.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {
    @NotBlank(message = "작성자명은 공백일 수 없습니다.")
    @Size(min=1, max=5, message = "작성자명은 최소 1자에서 최대 5자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\p{Punct}]*$", message = "작성자명은 공백을 제외한 영어, 한글, 숫자, 특수기호만 가능합니다.")
    private String username;

    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
