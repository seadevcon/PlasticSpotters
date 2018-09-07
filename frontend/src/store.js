import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username: 'Lisa',
    score: 10
  },
  mutations: {
    increment (state) {
      state.score += 10
    }
  }
})
