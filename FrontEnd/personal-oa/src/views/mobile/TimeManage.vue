<template>
    <v-layout wrap
    >
        <v-flex xs4 class="text-xs-center">
            <v-btn round @click="$refs.calendar.prev()">
                <v-icon dark left>keyboard_arrow_left</v-icon>
                先月
            </v-btn>
        </v-flex>
        <v-flex xs3 offset-xs1 class="text-xs-center">
            <v-menu ref="menu" v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    :return-value.sync="date"
                    lazy transition="scale-transition"
                    offset-y full-width max-width="290px" min-width="290px">
                <template v-slot:activator="{ on }">
                    <v-text-field v-model="date" label="今月" class="date-field"
                                  prepend-icon="event" readonly v-on="on">
                    </v-text-field>
                </template>
                <v-date-picker v-model="date" type="month" no-title
                        scrollable @input="chooseMonth">
                <v-spacer></v-spacer>
                </v-date-picker>
            </v-menu>
        </v-flex>
        <v-flex xs4 class="text-xs-center">
            <v-btn round @click="$refs.calendar.next()">
                来月
                <v-icon right dark>keyboard_arrow_right</v-icon>
            </v-btn>
        </v-flex>
        <v-flex xs12 class="mb-3">
            <v-sheet height="500">
                <v-calendar ref="calendar" :now="today" :value="date" color="primary"
                        v-model="start" :type="type">
                    <template v-slot:day="{ date }">
                        <template v-for="event in eventsMap[date]">
                            <v-menu :key="event.title" v-model="event.open"
                                    full-width offset-x>
                                <template v-slot:activator="{ on }">
                                    <div v-if="!event.time" v-ripple
                                            class="my-event" v-on="on"
                                            v-html="event.title">
                                    </div>
                                </template>
                                <v-card color="grey lighten-4"
                                        min-width="300px" flat>
                                    <v-toolbar color="primary" dark class="detail-toolbar">
                                        <v-btn icon>
                                            <v-icon>edit</v-icon>
                                        </v-btn>
                                        <v-toolbar-title v-html="event.title"></v-toolbar-title>
                                        <v-spacer></v-spacer>
                                        <v-btn icon>
                                            <v-icon>favorite</v-icon>
                                        </v-btn>
                                        <v-btn icon>
                                            <v-icon>more_vert</v-icon>
                                        </v-btn>
                                    </v-toolbar>
                                    <v-card-title primary-title>
                                        <span v-html="event.details"></span>
                                    </v-card-title>
                                    <v-card-actions>
                                        <v-btn flat color="secondary">
                                            Cancel
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-menu>
                        </template>
                    </template>
                </v-calendar>
            </v-sheet>
        </v-flex>
        <!--<v-dialog-->
                <!--v-model="dialog"-->
                <!--fullscreen-->
                <!--hide-overlay-->
                <!--transition="dialog-bottom-transition"-->
                <!--scrollable-->
        <!--&gt;-->
            <!--<v-card tile>-->
                <!--<v-toolbar card dark color="primary">-->
                    <!--<v-btn icon dark @click="dialog = false">-->
                        <!--<v-icon>close</v-icon>-->
                    <!--</v-btn>-->
                    <!--<v-toolbar-title>Settings</v-toolbar-title>-->
                    <!--<v-spacer></v-spacer>-->
                    <!--<v-toolbar-items>-->
                        <!--<v-btn dark flat @click="dialog = false">Save</v-btn>-->
                    <!--</v-toolbar-items>-->
                    <!--<v-menu bottom right offset-y>-->
                        <!--<template v-slot:activator="{ on }">-->
                            <!--<v-btn dark icon v-on="on">-->
                                <!--<v-icon>more_vert</v-icon>-->
                            <!--</v-btn>-->
                        <!--</template>-->
                        <!--<v-list>-->
                            <!--<v-list-tile v-for="(item, i) in items" :key="i" @click="">-->
                                <!--<v-list-tile-title>{{ item.title }}</v-list-tile-title>-->
                            <!--</v-list-tile>-->
                        <!--</v-list>-->
                    <!--</v-menu>-->
                <!--</v-toolbar>-->
                <!--<v-card-text>-->
                    <!--<v-tooltip right>-->
                        <!--<template v-slot:activator="{ on }">-->
                            <!--<v-btn v-on="on">Tool Tip Activator</v-btn>-->
                        <!--</template>-->
                        <!--Tool Tip-->
                    <!--</v-tooltip>-->
                    <!--<v-list three-line subheader>-->
                        <!--<v-subheader>User Controls</v-subheader>-->
                        <!--<v-list-tile avatar>-->
                            <!--<v-list-tile-content>-->
                                <!--<v-list-tile-title>Content filtering</v-list-tile-title>-->
                                <!--<v-list-tile-sub-title>Set the content filtering level to restrict apps that can be downloaded</v-list-tile-sub-title>-->
                            <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                        <!--<v-list-tile avatar>-->
                            <!--<v-list-tile-content>-->
                                <!--<v-list-tile-title>Password</v-list-tile-title>-->
                                <!--<v-list-tile-sub-title>Require password for purchase or use password to restrict purchase</v-list-tile-sub-title>-->
                            <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                    <!--</v-list>-->
                    <!--<v-divider></v-divider>-->
                    <!--<v-list three-line subheader>-->
                        <!--<v-subheader>General</v-subheader>-->
                        <!--<v-list-tile avatar>-->
                            <!--<v-list-tile-action>-->
                                <!--<v-checkbox v-model="notifications"></v-checkbox>-->
                            <!--</v-list-tile-action>-->
                            <!--<v-list-tile-content>-->
                                <!--<v-list-tile-title>Notifications</v-list-tile-title>-->
                                <!--<v-list-tile-sub-title>Notify me about updates to apps or games that I downloaded</v-list-tile-sub-title>-->
                            <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                        <!--<v-list-tile avatar>-->
                            <!--<v-list-tile-action>-->
                                <!--<v-checkbox v-model="sound"></v-checkbox>-->
                            <!--</v-list-tile-action>-->
                            <!--<v-list-tile-content>-->
                                <!--<v-list-tile-title>Sound</v-list-tile-title>-->
                                <!--<v-list-tile-sub-title>Auto-update apps at any time. Data charges may apply</v-list-tile-sub-title>-->
                            <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                        <!--<v-list-tile avatar>-->
                            <!--<v-list-tile-action>-->
                                <!--<v-checkbox v-model="widgets"></v-checkbox>-->
                            <!--</v-list-tile-action>-->
                            <!--<v-list-tile-content>-->
                                <!--<v-list-tile-title>Auto-add widgets</v-list-tile-title>-->
                                <!--<v-list-tile-sub-title>Automatically add home screen widgets</v-list-tile-sub-title>-->
                            <!--</v-list-tile-content>-->
                        <!--</v-list-tile>-->
                    <!--</v-list>-->
                <!--</v-card-text>-->

                <!--<div style="flex: 1 1 auto;"></div>-->
            <!--</v-card>-->
        <!--</v-dialog>-->
    </v-layout>
</template>

<script>
    export default {
        data() {
            return {
                date: new Date().toISOString().substr(0, 7),
                menu: false,
                dialog: false,
                today: new Date().toISOString().substr(0, 10),
                events: [
                    {
                        title: '9:00',
                        details: '出勤時間：9:00',
                        date: '2019-04-01',
                        open: false
                    },
                    {
                        title: '18:00',
                        details: '退勤時間：18:00<br/>残業時間：0時間',
                        date: '2019-04-01',
                        open: false
                    },
                    {
                        title: '9:00',
                        details: '出勤時間：9:00',
                        date: '2019-04-02',
                        open: false
                    },
                    {
                        title: '20:00',
                        details: '退勤時間：18:00<br/>残業時間：0時間',
                        date: '2019-04-02',
                        open: false
                    },{
                        title: '9:00',
                        details: '出勤時間：9:00',
                        date: '2019-04-03',
                        open: false
                    },
                    {
                        title: '20:00',
                        details: '退勤時間：18:00<br/>残業時間：0時間',
                        date: '2019-04-03',
                        open: false
                    }
                ],
                type: 'month',
                start: new Date().toISOString().substr(0, 7) + '-01'
            }
        },
        metaInfo: {
            title: '勤怠時間登録'
        },
        computed: {
            // convert the list of events into a map of lists keyed by date
            eventsMap () {
                const map = {};
                this.events.forEach(e => (map[e.date] = map[e.date] || []).push(e))
                this.date = this.start.substr(0, 7);
                return map
            }
        },
        methods: {
            open (event) {
                alert(event.title)
            },
            chooseMonth() {
                this.$refs.menu.save(this.date);
                this.start = this.date + '-01';
            }
        }
    }
</script>
<style lang="stylus" scoped>
    .my-event {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        border-radius: 2px;
        background-color: #1867c0;
        color: #ffffff;
        border: 1px solid #1867c0;
        width: 100%;
        font-size: 12px;
        padding: 3px;
        cursor: pointer;
        margin-bottom: 1px;
    }

    .date-field {
        width: 90px;
    }

    .detail-toolbar {
        padding-left: 10px;
    }
</style>

