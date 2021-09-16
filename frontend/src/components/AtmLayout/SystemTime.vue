<template>
    <span>{{date}} {{time}}</span>
</template>

<script>
export default {
  name: "SystemTime",
  props: {
  },
  data() {
    return {
        date: '',
        time: '',
    }
  },

  mounted: async function(){
      this.refresh();
  },

  methods: {
      async refresh(){
          setInterval( () => {
            let date = new Date();
            this.date = date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
            this.time = date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
          }, 1000);
      }
  }
}
</script>
