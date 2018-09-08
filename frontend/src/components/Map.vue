<template>
  <div>
    <div id="mapid"></div>

    <md-dialog :md-active.sync="showDialog">
      <md-dialog-title>Report garbage</md-dialog-title>

      <md-steppers md-vertical>
        <md-step id="first" md-label="Your data" md-description="The recorded data">
          <p>Username: {{ username }}</p>
          <p>Latituge: {{ latlng.lat }}</p>
          Longtituge: {{ latlng.lng }}
        </md-step>

        <md-step id="second" md-label="Report information" md-description="Add additional info">
          <md-field>
            <label>Description</label>
            <md-textarea md-autogrow></md-textarea>
          </md-field>

          <md-field>
            <label>Image</label>
            <md-file accept="image/*" />
          </md-field>
        </md-step>
      </md-steppers>

      <md-dialog-actions>
        <md-button class="md-primary" @click="showDialog = false">Close</md-button>
        <md-button class="md-primary" @click="save">Save</md-button>
      </md-dialog-actions>
    </md-dialog>
  </div>
</template>

<script>
import store from '../store'
import axios from 'axios'

export default {
  name: 'Map',
  data: () => ({
    showDialog: false,
    store: store,
    latlng: {
      lat: 0,
      lng: 0
    }
  }),
  computed: {
    username () {
      return store.state.username
    }
  },
  mounted () {
    const mymap = L.map('mapid').setView([51.618, 1.496], 10)

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
      maxZoom: 18
    }).addTo(mymap)

    const LeafIcon = L.Icon.extend({
      options: {
        shadowUrl: '',
        iconSize: [ 38, 95 ],
        shadowSize: [ 0, 0 ],
        iconAnchor: [ 22, 94 ],
        shadowAnchor: [ 0, 0 ],
        popupAnchor: [ -3, -76 ]
      }
    })

    axios.get('http://localhost:8080/cleaner/all')
      .then(response => {
        response.data.forEach(vessel => {
          const icon = new LeafIcon({ iconUrl: vessel.icon })
          L.marker([ vessel.lat, vessel.lng ], { icon: icon }).addTo(mymap).bindPopup(`
          <p><a href="${vessel.url}" target="_blank">${vessel.name}</a></p>
          <p>Type: ${vessel.type}</p>
          <p>Category: ${vessel.category}</p>
          <img src="${vessel.diagram}">
        `).openPopup()
        })
      })
      .catch(error => console.log(error))

    axios.get('http://localhost:8080/pollutionSpot/all')
      .then(response => {
        response.data.forEach(report => {
          L.marker([ report.lat, report.lng ]).addTo(mymap).bindPopup(`
          <p>Author: ${report.author}</p>
          <p>Category: ${report.category}</p>
          <img src="${report.image}">
        `).openPopup()
        })
      })
      .catch(error => console.log(error))

    mymap.on('click', e => {
      this.latlng = e.latlng
      this.showDialog = true
    })
  },
  methods: {
    save () {
      this.showDialog = false
      store.commit('increment')
      axios.post('http://localhost:8080/pollutionSpot/add', {
        author: 1,
        category: 'Test',
        lat: this.latlng.lat,
        lng: this.latlng.lng
      })
        .then(response => console.log(response))
        .catch(error => console.log(error))
    }
  }
}
</script>

<style lang="scss" scoped>
#mapid {
  height: calc(100vh - 64px);
}
</style>
