import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username: 'Lisa',
    email: 'lisa@test.de',
    score: 10
  },
  mutations: {
    increment (state) {
      state.score += 10
    }
  }
})
