import { type AxiosRequestConfig } from "axios";

const getAuthToken = (): string | null => {
  const stored = localStorage.getItem("aeu:currentUser");
  if (!stored) return null;
  return JSON.parse(stored).token ?? null;
};

export const getAuthHeader = (): AxiosRequestConfig => {
  return {
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${getAuthToken()}`,
    },
  };
};
