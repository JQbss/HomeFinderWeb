const AdminItem = (props) => {
  const data = { ...props };
  return data ? (
    <div className="admin-home-item">
      <a href={`/admin/${data?.link}`}>
        <h3>{data?.header}</h3>
      </a>
    </div>
  ) : null;
};

const AdminHome = (props) => {
  return (
    <>
      <div className="admin-home-root">
        <div className="admin-home-container">
          <AdminItem header="Ogłoszenia" link="announcement" />
          <AdminItem header="Aktualności" link="news" />
          <AdminItem header="Użytkownicy" link="users" />
        </div>
      </div>
    </>
  );
};

export default AdminHome;
