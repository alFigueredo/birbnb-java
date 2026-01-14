import { useState, useEffect, type ReactNode } from "react";
import { getUsuarios } from "../services/api";
import { UserContext } from "./useUsuario";
import type { Usuario } from "../types/Usuario";

interface Props {
  children: ReactNode;
}

export default function UserProvider({ children }: Props) {
  const [usuarios, setUsuarios] = useState<Usuario[]>([
    { id: 0, nombre: "Cargando usuarios...", tipo: "HUESPED" },
  ]);
  const [usuarioActual, setUsuarioActual] = useState<Usuario | null>(null);

  useEffect(() => {
    getUsuarios()
      .then((res) => {
        setUsuarios(res.data);
        setUsuarioActual(res.data.length > 0 ? res.data[0] : []); // por defecto, el primero
      })
      .catch((err) => console.error("Error al cargar usuarios:", err));
  }, []);

  return (
    <UserContext.Provider value={{ usuarios, usuarioActual, setUsuarioActual }}>
      {children}
    </UserContext.Provider>
  );
}
