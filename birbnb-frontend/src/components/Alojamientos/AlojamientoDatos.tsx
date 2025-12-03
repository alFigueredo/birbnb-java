import { Link } from "react-router";
import type { Alojamiento } from "../../pages/alojamientos/Alojamientos";

interface Props {
  aloj: Alojamiento;
}
export default function AlojamientoDatos({ aloj }: Props) {
  return (
    <div className="aloja-info">
      <Link to={`/alojamientos/${aloj.id}`}>
        <h2 className="aloja-info-title">{aloj.nombre}</h2>
      </Link>

      <p className="aloja-info-desc">{aloj.descripcion}</p>

      <p className="aloja-info-precio">
        💸${aloj.precioPorNoche.toLocaleString()} por noche
      </p>

      <p className="aloja-info-cant">
        🧑‍🤝‍🧑 <strong>Cantidad máxima:</strong> {aloj.cantHuespedesMax} huéspedes
      </p>

      <p className="aloja-info-direc">
        📍 {aloj.direccion.calle} {aloj.direccion.altura}
      </p>
    </div>
  );
}
