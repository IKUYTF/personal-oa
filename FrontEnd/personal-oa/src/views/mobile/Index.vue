<template>
    <v-layout>
        <v-flex xs12 sm6 offset-sm3>
            <v-card class='mainPage' :height="clientHeight">
                <div class="headline text-xs-center pa-5">
                    <v-fade-transition mode="out-in">
                        <router-view />
                    </v-fade-transition>
                </div>

                <v-bottom-nav mandatory :active.sync="bottomNav" :value="true" absolute color="transparent">
                    <v-btn color="teal" flat value="recent">
                        <span>出勤履歴</span>
                        <v-icon>list_alt</v-icon>
                    </v-btn>

                    <v-btn color="teal" flat value="favorites">
                        <span>出勤管理</span>
                        <v-icon>edit</v-icon>
                    </v-btn>

                    <v-btn color="teal" flat value="自分" >
                        <span>自分</span>
                        <v-icon>person_outline</v-icon>
                    </v-btn>
                </v-bottom-nav>
            </v-card>

        </v-flex>
    </v-layout>
</template>

<script>
    export default {
        data() {
            return {
                bottomNav: 'recent',
                clientHeight: ''
            }
        },
        metaInfo: {
            title: '勤怠時間登録'
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