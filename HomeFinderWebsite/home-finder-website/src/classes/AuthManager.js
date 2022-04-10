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
    }).then((resonse) => "ok");
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
    }).then((resonse) =>
      resonse
        .json()
        .then((data) =>
          localStorage.setItem("token", "Bearer " + data?.idToken)
        )
    );
  }
}

export default AuthManager;
