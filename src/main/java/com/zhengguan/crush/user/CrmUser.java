package com.zhengguan.crush.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.zhengguan.crush.validator.FieldMatch;
import com.zhengguan.crush.validator.ValidEmail;

// confirm user registration info
@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The passwords you entered do not match.")
})
public class CrmUser {
	@NotNull
	@NotEmpty(message="Required")
	@Size(min=4, message="Must be 4 characters or more")
	private String username;

	@NotNull
	@NotEmpty(message="Please enter your password")
	@Size(min=4, message="Must be 4 characters or more")
	private String password;

	@NotNull
	@NotEmpty(message="Please enter your password again")
	@Size(min=1, message="is required")
	private String matchingPassword;

	@NotNull
	@NotEmpty(message="Required")
	@ValidEmail
	private String email;
	
	public CrmUser() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CrmUser{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", matchingPassword='" + matchingPassword + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
