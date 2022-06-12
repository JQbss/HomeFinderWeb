export const entities = {
  announcement: {
    list: ["uid", "localization", "price"],
    listLabels: ["id", "Miejscowość", "Cena, zł"],
    edit: true,
    editFields: [
      { title: "text" },
      { localization: "text" },
      { description: "textarea" },
      { price: "text" },
    ],
    editLabels: ["Nazwa", "Miasto", "Opis", "Cena, zł"],
    createFields: [
      { title: "text" },
      { localization: "text" },
      { description: "textarea" },
      { price: "text" },
    ],
    createLabels: ["Nazwa", "Miasto", "Opis", "Cena, zł"],
  },
  users: {
    list: ["uid", "email"],
    listLabels: ["id", "E-mail"],
    edit: true,
    editFields: [
      { email: "text" },
      { firstName: "text" },
      { lastName: "text" },
    ],
    editLabels: ["E-mail", "Imię", "Nazwisko"],
    createFields: [
      { email: "text" },
      { firstName: "text" },
      { lastName: "text" },
    ],
    createLabels: ["E-mail", "Imię", "Nazwisko"],
  },
};
