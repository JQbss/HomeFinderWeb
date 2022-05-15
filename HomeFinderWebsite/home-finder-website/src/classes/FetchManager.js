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

  static async GetMany(entityName, page = 0, limit = 25, filters) {
    return fetch(
      `${process.env.REACT_APP_API}/${entityName}?page=${page}&limit=${limit}${
        filters ? filters : ""
      }`
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
}

export default FetchManager;
