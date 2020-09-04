package springBootJPA.param;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailParam {
    private String userId;
    private Integer minAge;
    private Integer maxAge;
    private String realName;
    private String introduction;
    private String city;
}
