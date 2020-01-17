<template>
  <v-expansion-panel expand>
    <v-expansion-panel-content key="1">
      <template v-slot:header>
        <div class="text-xs-center">
          <v-chip color="primary" text-color="white">
            <v-avatar>
              <v-icon dark>cached</v-icon>
            </v-avatar>
            画面初期化（セッション無）
          </v-chip>
        </div>
      </template>
      <v-timeline dense>
        <v-timeline-item
                color="purple lighten-2"
                fill-dot
                right
        >
          <v-card>
            <v-card-title class="purple lighten-2">
              <v-icon
                      dark
                      size="42"
                      class="mr-3"
              >
                mdi-magnify
              </v-icon>
              <h2 class="display-1 white--text font-weight-light">Title 1</h2>
            </v-card-title>
            <v-container>
              <v-layout>
                <v-flex xs10>
                  Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit.
                </v-flex>
                <v-flex xs2>
                  <v-icon size="64">mdi-calendar-text</v-icon>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-timeline-item>

        <v-timeline-item
                color="amber lighten-1"
                fill-dot
                right
                small
        >
          <v-card>
            <v-card-title class="amber lighten-1 justify-end">
              <h2 class="display-1 mr-3 white--text font-weight-light">Title 2</h2>
              <v-icon
                      dark
                      size="42"
              >mdi-home-outline</v-icon>
            </v-card-title>
            <v-container>
              <v-layout>
                <v-flex xs8>
                  Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit.
                </v-flex>
                <v-flex xs4>
                  Lorem ipsum dolor sit amet, no nam oblique veritus.
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-timeline-item>

        <v-timeline-item
                color="cyan lighten-1"
                fill-dot
                right
        >
          <v-card>
            <v-card-title class="cyan lighten-1">
              <v-icon
                      class="mr-3"
                      dark
                      size="42"
              >
                mdi-email-outline
              </v-icon>
              <h2 class="display-1 white--text font-weight-light">Title 3</h2>
            </v-card-title>
            <v-container>
              <v-layout>
                <v-flex
                        v-for="n in 3"
                        :key="n"
                        xs4
                >
                  Lorem ipsum dolor sit amet, no nam oblique veritus no nam oblique.
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-timeline-item>

        <v-timeline-item
                color="red lighten-1"
                fill-dot
                right
                small
        >
          <v-card>
            <v-card-title class="red lighten-1 justify-end">
              <h2 class="display-1 mr-3 white--text font-weight-light">Title 4</h2>
              <v-icon
                      dark
                      size="42"
              >
                mdi-account-multiple-outline
              </v-icon>
            </v-card-title>
            <v-container>
              <v-layout>
                <v-flex xs2>
                  <v-icon size="64">mdi-server-network</v-icon>
                </v-flex>
                <v-flex xs10>
                  Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus.
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-timeline-item>

        <v-timeline-item
                color="green lighten-1"
                fill-dot
                right
        >
          <v-card>
            <v-card-title class="green lighten-1">
              <v-icon
                      class="mr-3"
                      dark
                      size="42"
              >
                mdi-phone-in-talk
              </v-icon>
              <h2 class="display-1 white--text font-weight-light">Title 5</h2>
            </v-card-title>
            <v-container>
              <v-layout>
                <v-flex>
                  Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-timeline-item>
      </v-timeline>
    </v-expansion-panel-content>
    <v-expansion-panel-content key="2">
      <template v-slot:header>
        <div class="text-xs-center">
          <v-chip color="blue lighten-1" text-color="white">
            <v-avatar>
              <v-icon dark>autorenew</v-icon>
            </v-avatar>
            画面初期化（セッション有）
          </v-chip>
        </div>
      </template>
    </v-expansion-panel-content>
    <v-expansion-panel-content key="3">
      <template v-slot:header>
        <div class="text-xs-center">
          <v-chip color="red" text-color="white">
            <v-avatar>
              <v-icon dark>zoom_in</v-icon>
            </v-avatar>
            バーコード、QRコード、拡大ボタン押下</v-chip>
        </div>
      </template>
    </v-expansion-panel-content>

    <v-expansion-panel-content key="4">
      <template v-slot:header>
        <div class="text-xs-center">
          <v-chip color="orange" text-color="white">
            <v-avatar>
              <v-icon dark>access_time</v-icon>
            </v-avatar>
            リフレッシュ、00:00になる</v-chip>
        </div>
      </template>
    </v-expansion-panel-content>
  </v-expansion-panel>
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
