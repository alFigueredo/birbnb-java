export type UserType = "HUESPED" | "ANFITRION";

export type PostUsuario = {
  nombre: string;
  email: string;
  tipoUsuario: UserType;
};

export type Usuario = {
  id: number;
  nombre: string;
  email: string;
  tipo: UserType;
};
