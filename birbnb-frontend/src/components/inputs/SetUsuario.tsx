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
        value={usuarioActual?._id || ""}
        onChange={(e) => {
          const user = usuarios.find((u) => u._id === e.target.value);
          setUsuarioActual?.(user ?? null);
        }}
      >
        {usuarios.map((u) => (
          <option key={u._id} value={u._id}>
            {u.nombre}
          </option>
        ))}
      </select>
    </>
  );
}
