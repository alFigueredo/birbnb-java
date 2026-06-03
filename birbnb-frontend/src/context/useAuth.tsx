import { createContext, useContext } from "react";
import type { UserType, Usuario } from "../types/Usuario";

export interface JwtPayload {
  sub: number; // userId
  name: string;
  email: string;
  type: UserType;
}

export interface AuthContextValue {
  token: string | null;
  userId: number | null;
  name: string | null;
  email: string | null;
  type: UserType;
  initialized: boolean;
  login: (token: string) => void;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextValue | undefined>(
  undefined,
);

export default function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return ctx;
}

export const useUsuario = (): Usuario => {
  const auth = useAuth();
  return {
    id: auth.userId ?? 0,
    nombre: auth.name ?? "",
    email: auth.email ?? "",
    tipo: auth.type,
  };
};
