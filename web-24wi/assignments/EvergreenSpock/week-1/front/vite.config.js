import { defineConfig } from "vite"; //importing react into vite
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  root: "", //setting root directory to wherever ever our index.html which is in our front folder
});
