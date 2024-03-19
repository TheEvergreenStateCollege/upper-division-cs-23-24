import { defineConfig } from "vite"; //importing react into vite
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  root: "src",
});
