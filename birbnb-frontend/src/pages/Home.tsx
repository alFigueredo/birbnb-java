import { Link } from "react-router";
import "../styles/Home.css";

export default function Home() {
  return (
    <main id="main-home">
      {/*LOGO*/}
      <div id="birbnb-div" className="transition-transform">
        <img src="/logobirbnb.png" alt="Logo Birbnb" width={220} height={220} />
      </div>

      <h1 id="title-home">¡Bienvenid@ a Birbnb! 🗺️</h1>

      <p id="paragraph-home">
        Si estás buscando un alojamiento ideal,{" "}
        <strong>¡LO ENCONTRASTE!</strong> 💜 Explorá nuestras opciones y
        encontrá el hospedaje perfecto para vos ✨
      </p>

      <Link to="/alojamientos">
        <button id="boton-alojamientos" data-cy="get-alojas">
          Explorar alojamientos
        </button>
      </Link>
    </main>
  );
}
