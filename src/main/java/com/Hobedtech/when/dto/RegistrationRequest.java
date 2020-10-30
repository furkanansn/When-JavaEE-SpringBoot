package com.Hobedtech.when.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * Created by temelt on 15.02.2019.
 */
@Data
public class RegistrationRequest {
    @Min(message = "Kullanıcı adı en az 3 karakterden oluşmalıdır", value = 3)
    private String userName;
    @Min(message = "Parola en az 8 karakterden oluşmalıdır",value = 8)
    private String password;
    @NotEmpty(message = "E-mail boş olmamalıdır")
    @Email
    private String email;


}
