import { type Context } from "../../context/useUsuario";

export default function SetUsuario({
  usuarios,
  usuarioActual,
  setUsuarioActual,
}: Context) {
  return (
    <>
      <label id="usuario-label" htmlFor="usuario">
        Usuario:
      </label>
      <select
        id="usuario"
        className={`${usuarioActual ? "text-black" : "text-gray"}`}
        value={usuarioActual?.id || ""}
        onChange={(e) => {
          const user = usuarios.find((u) => u.id === parseInt(e.target.value));
          setUsuarioActual?.(user ?? null);
        }}
      >
        {usuarios.map((u) => (
          <option key={u.id} value={u.id}>
            {u.nombre}
          </option>
        ))}
      </select>
    </>
  );
}
