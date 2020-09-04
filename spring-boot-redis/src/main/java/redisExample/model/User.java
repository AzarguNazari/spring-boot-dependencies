package redisExample.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String password;
	private String email;
	private String nickname;
	private String regTime;

	public User() {
		super();
	}
	public User(String email, String nickname, String password, String userName, String regTime) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.userName = userName;
		this.regTime = regTime;
	}
}