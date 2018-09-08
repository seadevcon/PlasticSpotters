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
            <label for="count">Category</label>
            <md-select v-model="count" name="count" id="count">
              <md-option value="1">Some plastic</md-option>
              <md-option value="2">A lot of plastic</md-option>
            </md-select>
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
const HeatmapOverlay = require('../leaflet-heatmap.js')

export default {
  name: 'Map',
  data: () => ({
    showDialog: false,
    count: 0,
    latlng: {
      lat: 0,
      lng: 0
    },
    map: null,
    heatmapLayer: null
  }),
  computed: {
    username () {
      return store.state.username
    },
    email () {
      return store.state.email
    }
  },
  mounted () {
    this.map = L.map('mapid').setView([51.618, 1.496], 10)

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: 'Map data © <a href="https://openstreetmap.org">OpenStreetMap</a> contributors',
      maxZoom: 18
    }).addTo(this.map)

    this.map.on('click', e => {
      this.latlng = e.latlng
      this.showDialog = true
    })

    this.heatmapLayer = new HeatmapOverlay({
      radius: 0.1,
      maxOpacity: 0.5,
      scaleRadius: true,
      useLocalExtrema: true,
      latField: 'lat',
      lngField: 'lng',
      valueField: 'count'
    })
    this.map.addLayer(this.heatmapLayer)

    this.refresh()
  },
  methods: {
    save () {
      this.showDialog = false
      axios.post('http://localhost:8080/pollutionSpot/add', {
        author: { id: 1, email: this.email, name: this.username },
        count: this.count,
        lat: this.latlng.lat,
        lng: this.latlng.lng,
        imageUrl: 'https://public-media.smithsonianmag.com/filer/4b/93/4b93429f-9241-4653-adb3-65ecfc7eaa5a/istock_20210548_medium.jpg'
      })
        .then(response => {
          store.commit('increment')
          this.refresh()
        })
        .catch(error => console.log(error))
    },
    refresh () {
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
            const icon = new LeafIcon({ iconUrl: vessel.iconUrl })
            L.marker([ vessel.lat, vessel.lng ], { icon: icon }).addTo(this.map).bindPopup(`
          <p><a href="${vessel.companyUrl}" target="_blank">${vessel.name}</a></p>
          <p>Type: ${vessel.type}</p>
          <p>Category: ${vessel.category}</p>
          <a href="${vessel.diagramUrl}" target="_blank"><img src="${vessel.diagramUrl}"></a>
          <a href="${vessel.donationUrl}" target="_blank" class="md-button md-primary md-raised">Donate</a>
        `).openPopup()
          })
        })
        .catch(error => console.log(error))

      axios.get('http://localhost:8080/pollutionSpot/all')
        .then(response => {
          response.data.forEach(report => {
            L.marker([ report.lat, report.lng ]).addTo(this.map).bindPopup(`
          <p>Author: ${report.author.name}</p>
          <p>Category: ${report.category}</p>
          <a href="${report.imageUrl}" target="_blank"><img src="${report.imageUrl}"></a>
        `).openPopup()

            this.heatmapLayer.setData({
              max: 8,
              data: response.data
            })
          })
        })
        .catch(error => console.log(error))
    }
  }
}
</script>

<style lang="scss">
#mapid {
  height: calc(100vh - 64px);
}

.leaflet-popup-content > a.md-button {
  width: 100%;
  margin: 6px 0 0 0;
  background: rgb(68, 138, 255) !important;
  color: rgb(255, 255, 255);
  font-weight: 550;
  text-align: center;
  line-height: 36px;
}
</style>
