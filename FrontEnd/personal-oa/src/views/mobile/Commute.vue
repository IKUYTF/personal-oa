<template>
    <v-layout>
        <v-flex xs12 sm6 offset-sm3>
            <v-card class='mainPage' :height="clientHeight">
                <div class="headline text-xs-center pa-5">
                    <v-fade-transition mode="out-in">
                        <router-view />
                    </v-fade-transition>
                </div>
            </v-card>

        </v-flex>
    </v-layout>
</template>

<script>
    export default {
        data() {
            return {
                clientHeight: ''
            }
        },
        metaInfo: {
            title: '勤怠時間登録'
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