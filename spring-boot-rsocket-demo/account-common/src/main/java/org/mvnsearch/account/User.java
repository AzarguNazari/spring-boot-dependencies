package org.mvnsearch.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String nick;
    private String email;
    private String phone;

    public User(Integer id, String nick) {
        this.id = id;
        this.nick = nick;
    }
}