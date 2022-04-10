class FetchManager {
  static async GetUserData() {
    return fetch(`${process.env.REACT_APP_API}/auth/user-details`, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        return data;
      });
  }

  static async GetOne(entityName, id) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}/${id}`)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        return data;
      });
  }
}

export default FetchManager;
