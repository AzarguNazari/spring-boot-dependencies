package org.mvnsearch.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Account {

    private Integer id;
    private String nick;
    private String phone;
    private String password;
    private Integer status;

    public Account(Integer id, String nick) {
        this.id = id;
        this.nick = nick;
    }
}
