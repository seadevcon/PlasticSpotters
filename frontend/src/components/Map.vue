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

export default {
  name: 'Map',
  data: () => ({
    showDialog: false,
    store: store,
    latlng: {
      lat: 0,
      lng: 0
    },
    vessels: [
      {
        name: 'The Ocean Cleanup',
        link: 'https://www.theoceancleanup.com/',
        icon: 'https://visualpharm.com/assets/393/Historic%20Ship-595b40b75ba036ed117d625d.svg',
        type: 'Autonomous',
        category: 'Heavy plastic',
        diagram: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq6GhS0KpRAAo5l-6s2r7V3oXBHr0UPqu2Mwi9QIUttHQH4Mp7',
        lat: 51.460,
        lng: 0.970
      },
      {
        name: 'Pacific Garbage Screening',
        link: 'https://pacific-garbage-screening.de/en/startseite-2/',
        icon: 'https://visualpharm.com/assets/540/Viking%20Ship-595b40b85ba036ed117db7b8.svg',
        type: 'Non autonomous',
        category: 'Small plastic',
        diagram: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq6GhS0KpRAAo5l-6s2r7V3oXBHr0UPqu2Mwi9QIUttHQH4Mp7',
        lat: 51.644,
        lng: 1.191
      },
      {
        name: 'Pacific Garbage Screening',
        link: 'https://pacific-garbage-screening.de/en/startseite-2/',
        icon: 'https://visualpharm.com/assets/178/Sailing%20Ship%20Large-595b40b85ba036ed117daf9b.svg',
        type: 'Non autonomous',
        category: 'Small plastic',
        diagram: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRq6GhS0KpRAAo5l-6s2r7V3oXBHr0UPqu2Mwi9QIUttHQH4Mp7',
        lat: 51.432,
        lng: 1.229
      }
    ],
    reports: [
      {
        author: 'Lisa',
        description: 'Test',
        image: 'https://public-media.smithsonianmag.com/filer/4b/93/4b93429f-9241-4653-adb3-65ecfc7eaa5a/istock_20210548_medium.jpg',
        lat: 51.554,
        lng: 1.410
      },
      {
        author: 'Lisa',
        description: 'Test',
        image: 'https://public-media.smithsonianmag.com/filer/4b/93/4b93429f-9241-4653-adb3-65ecfc7eaa5a/istock_20210548_medium.jpg',
        lat: 51.484,
        lng: 2.140
      }
    ]
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

    this.vessels.forEach(vessel => {
      const icon = new LeafIcon({ iconUrl: vessel.icon })
      L.marker([ vessel.lat, vessel.lng ], { icon: icon }).addTo(mymap).bindPopup(`
        <p><a href="${vessel.link}" target="_blank">${vessel.name}</a></p>
        <p>Type: ${vessel.type}</p>
        <p>Category: ${vessel.category}</p>
        <img src="${vessel.diagram}">
      `).openPopup()
    })

    this.reports.forEach(report => {
      L.marker([ report.lat, report.lng ]).addTo(mymap).bindPopup(`
        <p>Author: ${report.author}</p>
        <p>Description: ${report.description}</p>
        <img src="${report.image}">
      `).openPopup()
    })

    mymap.on('click', e => {
      this.latlng = e.latlng
      this.showDialog = true
    })
  },
  methods: {
    save () {
      this.showDialog = false
      store.commit('increment')
    }
  }
}
</script>

<style lang="scss" scoped>
#mapid {
  height: calc(100vh - 64px);
}
</style>
