const Contact = (props) => {
  return (
    <>
      <div className="contact-root">
        <div className="contact-container">
          <h2>Kontakt</h2>
          <div>
            <span>
              E-mail:
              <br />
              <a href="mailto:zespolowka05@gmail.com?subject=Home Finder - feedback">
                zespolowka05@gmail.com
              </a>
            </span>
          </div>
          <div>
            <span>
              Numer telefonu:
              <br />
              <a href="tel:123-456-7890">123-456-7890</a>
            </span>
          </div>
          <img src="/contact-us-icon-transparent.png" height={150} />
        </div>
      </div>
    </>
  );
};

export default Contact;
