const About = (props) => {
  return (
    <>
      <div className="contact-root">
        <div className="contact-container">
          <h2>Informację ogólne</h2>
          <div>
            <span>
              Ten projekt powstał w ramach zajęć z programowania zespołowego
              oraz wyłącznie w celach edukacyjnych.
              <br />
              Serdecznie prosimy zapoznać się z{" "}
              <a href="/Regulamin.pdf" target="_blank">
                regulaminem
              </a>
              .
            </span>
          </div>
          <div>
            <h2>Mechanizm działania</h2>
            <span>
              Całe działanie można opisać w trzech krokach:
              <ol>
                <li>Home Finder wyszukuje oferowane mieszkania w internecie</li>
                <li>
                  Nasz serwis analizuje zdjęcia umieszczone w ofertach i
                  rozpoznaje umeblowanie wraz z kluczowymi danymi
                </li>
                <li>
                  Nasza strona internetowa (aplikacja mobilna) daje
                  użytkownikowi możliwość wyszukać oferty zgodnie z oczekiwanym
                  wyposażeniem
                </li>
              </ol>
            </span>
          </div>
        </div>
      </div>
    </>
  );
};

export default About;
