<template>
  <v-footer
    id="core-footer"
    absolute
    height="82"
  >
    <v-bottom-nav mandatory :active.sync="bottomNav" :value="true" absolute color="transparent">
      <v-btn color="teal" flat to="/">
        <span>防伪扫描</span>
        <v-icon>list_alt</v-icon>
      </v-btn>

      <v-btn color="teal" flat to="/manageSys">
        <span>防伪管理</span>
        <v-icon>edit</v-icon>
      </v-btn>

      <!--<v-btn color="teal" flat to="/commute">-->
        <!--<span>交通費</span>-->
        <!--<v-icon>train</v-icon>-->
      <!--</v-btn>-->
      <!--<v-btn color="teal" flat to="/user-profile">-->
        <!--<span>自分</span>-->
        <!--<v-icon>person_outline</v-icon>-->
      <!--</v-btn>-->
    </v-bottom-nav>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    bottomNav: '',
    links: [
      { name: 'Home', Link: '/dashboard' },
      { name: 'About Us', Link: 'http://www.thscom.co.jp/' },
      { name: 'Contact Us', Link: 'http://www.thscom.co.jp/' }
    ]
  })
}
</script>

<style>
#core-footer {
  z-index: 0;
}
</style>
