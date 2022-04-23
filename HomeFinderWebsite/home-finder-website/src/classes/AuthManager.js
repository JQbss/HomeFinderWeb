class AuthManager {
  static async RegisterUser(email, password) {
    return fetch(`${process.env.REACT_APP_API}/auth/register`, {
      method: "POST",
      body: JSON.stringify({
        email: email,
        password: password,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => "ok");
  }

  static async LoginUser(email, password) {
    return fetch(`${process.env.REACT_APP_API}/auth/login`, {
      method: "POST",
      body: JSON.stringify({
        email: email,
        password: password,
      }),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) =>
      response.status == 200
        ? response
            .json()
            .then((data) =>
              localStorage.setItem("token", "Bearer " + data?.idToken)
            )
        : "error"
    );
  }

  static LogoutUser() {
    localStorage.removeItem("token");
  }

  static CheckLoginUser() {
    return localStorage.getItem("token") != null ? true : false;
  }
}

export default AuthManager;
