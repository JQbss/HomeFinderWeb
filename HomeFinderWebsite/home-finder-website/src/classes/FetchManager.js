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
        if (data == "error") return "error";
        return fetch(`${process.env.REACT_APP_API}/users/${data?.uid}`, {
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        }).then((resp) => resp?.json().then((userInfo) => userInfo));
      });
  }

  static async GetOne(entityName, id) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}/${id}`, {
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

  static async GetMany(entityName, page = 0, limit = 25, filters) {
    let filtersStr = "";
    if (filters instanceof Object)
      for (const [key, value] of Object.entries(filters)) {
        if (key && value) filtersStr += `${key}=${value}&`;
      }
    return fetch(
      `${process.env.REACT_APP_API}/${entityName}?page=${page}&limit=${limit}${
        filtersStr != "" ? `&${filtersStr}` : ""
      }`,
      {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      }
    )
      .then((response) => {
        if (response.status == 200) return response.json();
        else return "error";
      })
      .then((data) => {
        return data;
      });
  }

  static async GetAddressDetails(lat, lon) {
    return fetch(
      `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${lat}&lon=${lon}&accept-language=pl`
    )
      .then((response) => {
        if (response.status == 200) return response.json();
        else return "error";
      })
      .then((data) => {
        return data;
      });
  }

  static async EditUserData(userId, data) {
    return fetch(`${process.env.REACT_APP_API}/users/${userId}`, {
      method: "PATCH",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem("token"),
      },
    }).then((response) => "ok");
  }

  static async Patch(entityName, id, data) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}/${id}`, {
      method: "PATCH",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem("token"),
      },
    }).then((response) => "ok");
  }

  static async Post(entityName, data) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}`, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Authorization: localStorage.getItem("token"),
      },
    }).then((response) => "ok");
  }

  static async Delete(entityName, id) {
    return fetch(`${process.env.REACT_APP_API}/${entityName}/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    }).then((response) => "ok");
  }
}

export default FetchManager;
