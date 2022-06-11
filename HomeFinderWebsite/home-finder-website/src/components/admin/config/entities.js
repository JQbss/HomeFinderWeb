export const entities = {
  announcement: {
    list: ["uid", { address: ["miejscowosc"] }, "price"],
    listLabels: ["id", "Miejscowość", "Cena, zł"],
    edit: true,
    editFields: [
      { title: "text" },
      { description: "textarea" },
      { price: "text" },
    ],
    editLabels: ["Nazwa", "Opis", "Cena, zł"],
  },
};
