<template>
    <v-navigation-drawer
            id="app-drawer"
            v-model="inputValue"
            app
            dark
            floating
            persistent
            :mini-variant="mini"
            mobile-break-point="991"
            width="260"
    >
        <v-img
                :src="image"
                height="100%"
        >
            <v-layout
                    class="fill-height"
                    tag="v-list"
                    column
            >
                <v-list-tile v-if="mini" @click.stop="mini = !mini">
                    <v-list-tile-action>
                        <v-icon>chevron_right</v-icon>
                    </v-list-tile-action>
                </v-list-tile>
                <v-list-tile avatar>
                    <v-list-tile-avatar color="white">
                        <v-img :src="logo" height="34" contain />
                    </v-list-tile-avatar>
                    <v-list-tile-content class="title">
                        Vuetify MD
                    </v-list-tile-content>
                    <v-list-tile-action>
                        <v-btn icon @click.stop="mini = !mini">
                            <v-icon>chevron_left</v-icon>
                        </v-btn>
                    </v-list-tile-action>
                </v-list-tile>
                <v-divider/>
                <v-list-tile
                        v-if="responsive"
                >
                    <v-text-field
                            class="purple-input search-input"
                            label="Search..."
                            color="purple"
                    />
                </v-list-tile>
                <v-list-tile
                        v-for="(link, i) in links"
                        :key="i"
                        :to="link.to"
                        v-if="link.visible"
                        :active-class="color"
                        avatar
                        class="v-list-item"
                >
                    <v-list-tile-action>
                        <v-icon>{{ link.icon }}</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content v-text="link.text"/>
                </v-list-tile>
                <v-list-tile
                        disabled
                        active-class="primary"
                        class="v-list-item v-list__tile--buy"
                        to="/upgrade"
                >
                    <v-list-tile-action>
                        <v-icon>mdi-package-up</v-icon>
                    </v-list-tile-action>
                    <!--<v-list-tile-title class="font-weight-light">-->
                        <!--Upgrade To PRO-->
                    <!--</v-list-tile-title>-->
                </v-list-tile>
            </v-layout>
        </v-img>
    </v-navigation-drawer>
</template>

<script>
    // Utilities
    import {
        mapMutations,
        mapState
    } from 'vuex'

    export default {
        data: () => ({
            logo: './img/vuetifylogo.png',
            ifAdmin: 'false',
            mini: false,
            links: [
                // {
                //     to: '/timeHistory',
                //     icon: 'history',
                //     text: '出勤履歴',
                //     adminAuthority: false,
                //     visible:false
                // },
                // {
                //     to: '/timeManage',
                //     icon: 'calendar_today',
                //     text: '出勤管理',
                //     adminAuthority: false,
                //     visible:false
                // },
                // {
                //     to: '/userInfo',
                //     icon: 'mdi-account',
                //     text: 'ユーザー情報',
                //     adminAuthority: false,
                //     visible:false
                // },
                {
                    to: '/progressMemo',
                    icon: 'mdi-view-dashboard',
                    text: '進捗状況',
                    adminAuthority: false,
                    visible:false
                },
                {
                    to: '/simpleTools',

                    icon: 'mdi-map-marker',
                    text: '簡易ツール',
                    adminAuthority: false,
                    visible:false
                },
                {
                    to: '/wechatApiTest',
                    icon: 'mdi-map-marker',
                    text: '微信API',
                    adminAuthority: false,
                    visible:false
                },
                {
                    to: '/learning',
                    icon: 'mdi-clipboard-outline',
                    text: '日語学習',
                    adminAuthority: false,
                    visible:true
                },
                {
                    to: '/examLearn',
                    icon: 'book',
                    text: '日語学習2',
                    adminAuthority: false,
                    visible:true
                },
                {
                    to: '/sevenPay',
                    icon: 'payment',
                    text: '7Pay起動',
                    adminAuthority: false,
                    visible:true
                },
                {
                    to: '/productManage',
                    icon: 'help_outline',
                    text: '商品管理',
                    adminAuthority: false,
                    visible:true
                },
                {
                    to: '/orderManage',
                    icon: 'help_outline',
                    text: '订单管理',
                    adminAuthority: false,
                    visible:true
                },
                {
                    to: '/shopManage',
                    icon: 'chrome_reader_mode',
                    text: '店舗管理',
                    adminAuthority: false,
                    visible:false
                },

                {
                    to: '/userManage',
                    icon: 'mdi-account',
                    text: 'ユーザー管理',
                    adminAuthority: false,
                    visible:false
                },
                {
                    to: '/maps',
                    icon: 'mdi-map-marker',
                    text: 'Maps',
                    adminAuthority: true,
                    visible:true
                }
            ],
            responsive: false
        }),
        computed: {
            ...mapState('app', ['image', 'color']),
            inputValue: {
                get() {
                    return this.$store.state.app.drawer
                },
                set(val) {
                    this.setDrawer(val)
                }
            },
            items() {
                return this.$t('Layout.View.items')
            }
        },
        mounted() {
            this.onResponsiveInverted()
            window.addEventListener('resize', this.onResponsiveInverted)
        },
        beforeDestroy() {
            window.removeEventListener('resize', this.onResponsiveInverted)
        },
        created() {
            this.ifAdmin = localStorage.getItem('permission');
            for(let i = 0; i < this.links.length; i++) {

                if(this.links[i].adminAuthority && this.ifAdmin==="true") {
                    this.links[i].visible = true;
                } else if(!this.links[i].adminAuthority) {
                    this.links[i].visible = true;
                } else {
                    this.links[i].visible = false;
                }
            }
        },
        methods: {
            ...mapMutations('app', ['setDrawer', 'toggleDrawer']),
            onResponsiveInverted() {
                if (window.innerWidth < 991) {
                    this.responsive = true
                } else {
                    this.responsive = false
                }
            }
        }
    }
</script>

<style lang="scss">
    #app-drawer {
        .v-list__tile {
            border-radius: 4px;

            &--buy {
                margin-top: auto;
                margin-bottom: 17px;
            }
        }

        .v-image__image--contain {
            top: 9px;
            height: 60%;
        }

        .search-input {
            margin-bottom: 30px !important;
            padding-left: 15px;
            padding-right: 15px;
        }
    }
</style>
