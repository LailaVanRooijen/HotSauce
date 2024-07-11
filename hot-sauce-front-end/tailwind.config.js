/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      fontFamily: {
        bebas: ["'Bebas Neue'", "sans-serif"],
        fjalla: ["'Fjalla One'", "sans-serif"],
        "permanent-marker": ["'Permanent Marker'", "cursive"],
        "major-mono": ["Major Mono Display", "monospace"],
      },
      boxShadow: {
        "custom-shadow":
          "0 10px 15px -3px rgba(0, 0, 0, 0.2), 0 4px 6px -2px rgba(0, 0, 0, 0.06)",
        "custom-shadow-pepper":
          "0 10px 15px -3px rgba(178, 34, 34, 0.2), 0 4px 6px -2px rgba(178, 34, 34, 0.06)",
        "custom-shadow-jalapeno":
          "1px 10px 15px 3px rgba(63, 97, 45, 0.2), 1px 4px 6px 2px rgba(63, 97, 45, 0.06)",
      },
      colors: {
        pepper: "#B22222",
        "pepper-light": "#f5cbcb",
        "pepper-dark": "#811919",
        jalapeno: "#3F612D",
        "jalapeno-light": "#bbd8ab",
        "jalapeno-dark": "#345025",
        base: "#FFFDED",
      },
    },
  },
  plugins: [],
};
