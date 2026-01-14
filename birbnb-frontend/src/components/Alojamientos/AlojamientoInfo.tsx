import { useState, useEffect } from "react";
import AlojamientoDatos from "./AlojamientoDatos";
import { getAlojamiento } from "../../services/api";
import FotoCarrusel from "./FotoCarrusel";
import "../../styles/AlojamientoPage.css";
import SkeletonInfo from "./SkeletonInfo";
import Formulario from "../Formulario";
import type { Alojamiento } from "../../types/Alojamiento";

interface Props {
  id: string;
}

export default function AlojamientoInfo({ id }: Props) {
  const [aloja, setAloja] = useState<Alojamiento | null>(null);

  async function cargarAlojamiento(alojaid: string) {
    try {
      const res = await getAlojamiento(alojaid);
      setAloja(res.data);
    } catch (err) {
      console.error("Error al cargar el alojamiento:", err);
    }
  }

  useEffect(() => {
    (async () => {
      await cargarAlojamiento(id);
    })();
  }, [id]);

  if (!aloja) return <SkeletonInfo />;

  return (
    <div id="aloja-info-div">
      <div id="aloja-info-carrusel">
        <FotoCarrusel fotos={aloja.fotos} />
      </div>

      <div id="aloja-info-datos">
        <AlojamientoDatos aloj={aloja} />
        <div id="aloja-info-form">
          <Formulario aloja={aloja} />
        </div>
      </div>
    </div>
  );
}
