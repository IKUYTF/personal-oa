<template>
    <v-layout row>
        <v-flex xs12 sm6 offset-sm3>
            <v-card>

                <v-list two-line>
                    <template v-for="(item, index) in items">
                        <v-subheader
                                v-if="item.header"
                                :key="item.header"
                        >
                            {{ item.header }}
                        </v-subheader>

                        <v-divider
                                v-else-if="item.divider"
                                :key="index"
                                :inset="item.inset"
                        ></v-divider>

                        <v-list-tile
                                v-else
                                :key="item.title"
                                avatar
                                @click=""
                        >
                            <v-list-tile-avatar>
                                <img :src="item.avatar">
                            </v-list-tile-avatar>

                            <v-list-tile-content>
                                <v-list-tile-title v-html="item.title"></v-list-tile-title>
                                <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                            </v-list-tile-content>
                        </v-list-tile>
                    </template>
                </v-list>
            </v-card>
        </v-flex>
    </v-layout>
</template>

<script>
    export default {
        data() {
            return {
                items: [
                    { header: '2月' },
                    {
                        avatar: 'http://www.thscom.co.jp/wp-content/themes/ths/common_img/header_logo.svg',
                        title: '02-28（木）正常出勤（本社）',
                        subtitle: "出勤時間「9:00」　退勤時間「18：00」"
                    },
                    { header: '3月（今月）' },
                    {
                        avatar: 'http://www.thscom.co.jp/wp-content/themes/ths/common_img/header_logo.svg',
                        title: '03-01（金）正常出勤（本社）',
                        subtitle: "出勤時間「9:00」　退勤時間「18：00」"
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'http://www.thscom.co.jp/wp-content/themes/ths/common_img/header_logo.svg',
                        title: '<span class="red--text text--lighten-1">03-02（土）休日出勤（本社）</span>',
                        subtitle: '<span class=\"red--text text--lighten-1\">出勤時間「9:00」　退勤時間「18：00」'
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'https://material.io/tools/icons/static/icons/baseline-home-24px.svg',
                        title: '<span class="green--text text--lighten-1">03-03（月）休憩</span>',
                        subtitle: '<span class="green--text text--lighten-1\">出勤時間「-:-」　退勤時間「-：-」'
                    },
                    {
                        avatar: 'https://www.isid.co.jp/common/images/logo-01.svg',
                        title: '03-04（月）正常出勤（ISID）',
                        subtitle: "出勤時間「9:00」　退勤時間「18：00」"
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'https://www.isid.co.jp/common/images/logo-01.svg',
                        title: '03-05（火）正常出勤（ISID）',
                        subtitle: '出勤時間「9:00」　退勤時間「18：00」'
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'https://www.isid.co.jp/common/images/logo-01.svg',
                        title: '03-06（水）正常出勤（ISID）',
                        subtitle: '出勤時間「-:-」　退勤時間「-：-」'
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'https://www.isid.co.jp/common/images/logo-01.svg',
                        title: '03-07（木）正常出勤（ISID）',
                        subtitle: '出勤時間「9:00」　退勤時間「18：00」'
                    },
                    { divider: true, inset: true },
                    {
                        avatar: 'https://www.isid.co.jp/common/images/logo-01.svg',
                        title: '03-08（金）正常出勤（ISID）',
                        subtitle: '出勤時間「-:-」　退勤時間「-：-」'
                    }
                ]
            }
        },
        metaInfo: {
            title: '出勤履歴'
        },
        methods: {},
        created() {
            this.clientHeight = `${document.documentElement.clientHeight}`;
            console.log(this.clientHeight);
            this.clientHeight = this.clientHeight - 70;
        }
    }
</script>
<style>
    .mainPage {
        width: 100%;
    }
</style>

<!--<style lang="scss" scoped>-->
<!--.table-header /deep/ {-->
<!--thead tr th {-->
<!--border: 1px solid rgb(209, 209, 209);-->
<!--}-->
<!--}-->
<!--.table-content /deep/ {-->
<!--tr td {-->
<!--border: 1px solid rgb(209, 209, 209);-->
<!--padding:0 8px 0 8px!important;-->
<!--}-->
<!--}-->
<!--</style>-->