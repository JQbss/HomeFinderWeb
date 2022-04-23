class FetchManager {
  static async GetUserData() {
    return fetch(`${process.env.REACT_APP_API}/auth/user-details`, {
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    })
      .then((response) => {
        if (response.status == 200) return response.json();
        else return "error";
      })
      .then((data) => {
        return data;
      });
  }

  static async GetOne(entityName, id) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}/${id}`)
      .then((response) => {
        if (response.status == 200) return response.json();
        else return "error";
      })
      .then((data) => {
        return data;
      });
  }

  static async GetMany(entityName) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}`)
      .then((response) => {
        if (response.status == 200) return response.json();
        else return "error";
      })
      .then((data) => {
        return data;
      });
  }
}

export default FetchManager;
