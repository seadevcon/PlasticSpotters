<template>
  <div class="page-container">
    <md-app md-mode="reveal">
      <md-app-toolbar class="md-primary">
        <div class="md-toolbar-row">
          <div class="md-toolbar-section-start">
            <md-button class="md-icon-button" @click="menuVisible = !menuVisible">
              <md-icon>menu</md-icon>
            </md-button>

            <span class="md-title">Plastic Spotters</span>
          </div>

          <div class="md-toolbar-section-end">
            <md-avatar class="md-middle">
              <img src="https://vuematerial.io/assets/examples/avatar-2.jpg" alt="People">
            </md-avatar>

            <div>
              <div style="height: 50%"><md-icon>account_circle</md-icon> {{ username }}</div>
              <div style="height: 50%"><md-icon>trending_up</md-icon> {{ score }} XP</div>
            </div>
          </div>
        </div>
      </md-app-toolbar>

      <md-app-drawer :md-active.sync="menuVisible">
        <md-toolbar class="md-transparent" md-elevation="0">Navigation</md-toolbar>

        <md-list>
          <md-list-item>
            <md-icon>map</md-icon>
            <router-link class="md-list-item-text" to="/">Home</router-link>
          </md-list-item>
          <md-list-item>
            <md-icon>timeline</md-icon>
            <router-link class="md-list-item-text" to="/stats">Stats</router-link>
          </md-list-item>
        </md-list>
      </md-app-drawer>

      <md-app-content>
        <router-view/>
      </md-app-content>

    </md-app>
  </div>
</template>

<script>
import store from './store'

export default {
  name: 'Reveal',
  data: () => ({
    menuVisible: false
  }),
  computed: {
    username () {
      return store.state.username
    },
    score () {
      return store.state.score
    }
  }
}
</script>

<style lang="scss" scoped>
  .md-toolbar-section-end {
    justify-content: flex-end;

    .md-avatar {
      margin-right: 10px !important;
    }
  }

  .md-app-content
  {
    padding: 0;
  }

  .md-drawer {
    width: 230px;
    max-width: calc(100vw - 125px);
  }
</style>
