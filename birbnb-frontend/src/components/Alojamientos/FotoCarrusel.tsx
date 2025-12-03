import { useState, type MouseEvent } from "react";
import type { Foto } from "../../pages/alojamientos/Alojamientos";

interface Props {
  fotos: Foto[];
}

export default function FotoCarrusel({ fotos }: Props) {
  const [fotoIndex, setFotoIndex] = useState(0);

  const handleNext = (e: MouseEvent) => {
    e.preventDefault();
    setFotoIndex((prev) => (prev + 1) % fotos.length);
  };

  const handlePrev = (e: MouseEvent) => {
    e.preventDefault();
    setFotoIndex((prev) => (prev - 1 + fotos.length) % fotos.length);
  };

  return (
    <div className="carrusel-div">
      <img
        src={fotos[fotoIndex]?.path}
        alt={fotos[fotoIndex]?.descripcion || "Imagen de alojamiento"}
      />

      {fotos.length > 1 && (
        <>
          <button
            onClick={handlePrev}
            className="button-carrusel button-carrusel-left"
          >
            ‹
          </button>
          <button
            onClick={handleNext}
            className="button-carrusel button-carrusel-right"
          >
            ›
          </button>
        </>
      )}

      {fotos.length > 1 && (
        <div className="carrusel-dots">
          {fotos.map((_, i) => (
            <span
              key={i}
              className={`carrusel-dot ${
                i === fotoIndex ? "carrusel-dot-on" : "carrusel-dot-off"
              }`}
            >
              {i === fotoIndex ? "●" : "○"}
            </span>
          ))}
        </div>
      )}
    </div>
  );
}
