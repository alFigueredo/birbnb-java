import FotoCarrusel from "./FotoCarrusel";
import AlojamientoDatos from "./AlojamientoDatos";
import { Link } from "react-router";
import type { Alojamiento } from "../../types/Alojamiento";

interface Props {
  aloj: Alojamiento;
}

export default function AlojamientoCard({ aloj }: Props) {
  return (
    <div className="alojamiento-card">
      <Link to={`/alojamientos/${aloj.id}`}>
        <div className="aloja-info-link">
          <FotoCarrusel fotos={aloj.fotos} />
        </div>
      </Link>
      <AlojamientoDatos aloj={aloj} />
    </div>
  );
}
