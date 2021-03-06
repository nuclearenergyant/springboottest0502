package www.diandian.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserInfo {
    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String path;

}