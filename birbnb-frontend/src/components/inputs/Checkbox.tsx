import type { ChangeEvent } from "react";

interface Props {
  id: string;
  label: string;
  incluyeFiltro: (value: string) => boolean;
  setearFiltros: (e: ChangeEvent<HTMLInputElement>) => void;
}

export default function Checkbox({
  id,
  label,
  incluyeFiltro,
  setearFiltros,
}: Props) {
  return (
    <div className="checkbox-div">
      <label htmlFor={id} className="checkbox-label">
        {label}
      </label>
      <input
        type="checkbox"
        checked={incluyeFiltro(id) || false}
        id={id}
        value={id}
        onChange={(e) => setearFiltros(e)}
      />
    </div>
  );
}
