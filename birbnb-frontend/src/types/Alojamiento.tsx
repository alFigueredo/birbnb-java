import type { Usuario } from "./Usuario";

export type Direccion = {
  calle: string;
  altura: string;
  latitud: string;
  longitud: string;
};

export type Foto = {
  path: string;
  descripcion: string;
};

export type Alojamiento = {
  id: number;
  nombre: string;
  descripcion: string;
  anfitrion: Usuario;
  precioPorNoche: number;
  cantHuespedesMax: number;
  direccion: Direccion;
  fotos: Foto[];
};

export type Filtro = {
  caractPedidas: string[];
  nombre: string;
  ciudad: string;
  pais: string;
  precioGt: number;
  precioLt: number;
  cantHuespedes: number;
  lat: string;
  long: string;
  page?: number;
};

export type PartialFiltro = Partial<Filtro>;

export type Pagina = {
  page: number;
  limit: number;
  total: number;
  cantPaginas: number;
};
