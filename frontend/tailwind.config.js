module.exports = {
  purge: [
    './public/*.html',
    './src/**/*.vue',
  ],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {},
  },
  variants: {
    extend: {
      backgroundColor: ['active'],
    }
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
