import {
  useContext,
  createContext,
  type Dispatch,
  type SetStateAction,
} from "react";

export type Usuario = {
  id: string;
  nombre: string;
};

export type Context = {
  usuarios: Usuario[];
  usuarioActual: Usuario | null;
  setUsuarioActual: Dispatch<SetStateAction<Usuario | null>> | null;
};

export const UserContext = createContext<Context>({
  usuarios: [],
  usuarioActual: null,
  setUsuarioActual: null,
});

export default function useUsuario() {
  return useContext(UserContext);
}
