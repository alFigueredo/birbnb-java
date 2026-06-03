import { jwtDecode } from "jwt-decode";
import { useEffect, useMemo, useState } from "react";
import { AuthContext, type JwtPayload } from "./useAuth";
import type { UserType } from "../types/Usuario";

const STORAGE_KEY = "aeu:currentUser";

const decodeToken = (token: string): JwtPayload | null => {
  try {
    return jwtDecode<JwtPayload>(token);
  } catch {
    return null;
  }
};

export default function AuthProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  const [token, setToken] = useState<string | null>(null);
  const [userId, setUserId] = useState<string | null>(null);
  const [email, setEmail] = useState<string | null>(null);
  const [name, setName] = useState<string | null>(null);
  const [type, setType] = useState<UserType>("HUESPED");
  const [initialized, setInitialized] = useState<boolean>(false);

  useEffect(() => {
    console.log("[AuthContext] Loading session from localStorage...");
    const saved = window.localStorage.getItem(STORAGE_KEY);
    console.log("[AuthContext] Saved data:", saved ? "Found" : "Not found");
    if (!saved) {
      setInitialized(true);
      return;
    }
    try {
      const parsed = JSON.parse(saved) as { token: string };
      const decoded = decodeToken(parsed.token);
      console.log("[AuthContext] Decoded saved token:", decoded);
      if (decoded) {
        setToken(parsed.token);
        setUserId(decoded.sub);
        setEmail(decoded.email);
        setName(decoded.name);
        setType(decoded.type);
        console.log("[AuthContext] Session restored:", {
          userId: decoded.sub,
          role: decoded.type,
        });
      }
    } catch (err) {
      console.error("[AuthContext] Error parsing saved session:", err);
    } finally {
      setInitialized(true);
    }
  }, []);

  const login = (newToken: string) => {
    console.log(
      "[AuthContext] login() called with token:",
      newToken.substring(0, 20) + "...",
    );
    const decoded = decodeToken(newToken);
    console.log("[AuthContext] Decoded token:", decoded);
    if (!decoded) {
      console.error("[AuthContext] Invalid token - cannot decode");
      return;
    }

    setToken(newToken);
    setUserId(decoded.sub);
    setEmail(decoded.email);
    setName(decoded.name);
    setType(decoded.type);
    window.localStorage.setItem(
      STORAGE_KEY,
      JSON.stringify({ token: newToken }),
    );
    console.log("[AuthContext] Token saved to localStorage:", STORAGE_KEY);
    console.log("[AuthContext] User logged in:", {
      userId: decoded.sub,
      role: decoded.type,
    });
  };

  const logout = () => {
    setToken(null);
    setUserId(null);
    setEmail(null);
    setName(null);
    setType("HUESPED");
    setInitialized(true);
    window.localStorage.removeItem(STORAGE_KEY);
  };

  const value = useMemo(
    () => ({ token, userId, name, email, type, initialized, login, logout }),
    [token, userId, name, email, type, initialized],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
