<template>
    <v-tabs centered grow color="cyan" dark icons-and-text>
        <v-tabs-slider color="yellow"></v-tabs-slider>

        <v-tab v-for="(tab, i) in tabs" :href="'#'+tab.href">
            {{tab.name}}
            <v-icon>{{tab.icon}}</v-icon>
        </v-tab>

        <v-tab-item key="1" value="payment">
            <v-data-table :headers="headers" :items="items" class="elevation-1">
            <template v-slot:items="props">
                <td>{{ props.item.name }}</td>
                <td class="justify-center layout px-0">
                    <v-chip v-for="(chip, i) in props.item.chips" :color="chip.color" text-color="white">
                        {{chip.name}}
                    </v-chip>
                </td>
            </template>
            </v-data-table>
        </v-tab-item>
        <v-tab-item key="2" value="charge">
            <v-container>
                <v-layout row wrap>
                    <v-flex xs12 lg6>
                        <v-expansion-panel popout>
                            <v-expansion-panel-content key="3">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="lime darken-2" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>payment</v-icon>
                                            </v-avatar>
                                            クレカ・デビットチャージ画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="4">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="teal darken-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>atm</v-icon>
                                            </v-avatar>
                                            ATMチャージ画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="5">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="pink darken-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>card_membership</v-icon>
                                            </v-avatar>
                                            nanacoチャージ画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>

                        </v-expansion-panel>
                    </v-flex>

                    <v-flex xs12 lg6>

                        <v-expansion-panel inset>
                            <v-expansion-panel-content key="1">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="brown darken-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>gamepad</v-icon>
                                            </v-avatar>
                                            チャージ方法選択画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="2">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="blue lighten-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>mobile_friendly</v-icon>
                                            </v-avatar>
                                            チャージ完了画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="6">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="green darken-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>money</v-icon>
                                            </v-avatar>
                                            レジで現金チャージバーコード画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="7">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="light-green darken-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_out_map</v-icon>
                                            </v-avatar>
                                            レジで現金チャージバーコード拡大表示画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="8">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="teal darken-2" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>360</v-icon>
                                            </v-avatar>
                                            レジで現金チャージバーコード番号表示画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-flex>
                </v-layout>
            </v-container>

        </v-tab-item>
        <v-tab-item key="3" value="balance">
            <v-expansion-panel expand>
                <v-expansion-panel-content key="1">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="primary" text-color="white">
                                <v-avatar>
                                    <v-icon dark>cached</v-icon>
                                </v-avatar>
                                残高・履歴画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-tab-item>
        <v-tab-item key="4" value="menu">
            <v-expansion-panel expand>
                <v-expansion-panel-content key="1">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="primary" text-color="white">
                                <v-avatar>
                                    <v-icon dark>cached</v-icon>
                                </v-avatar>
                                メニュー画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-tab-item>
        <v-tab-item key="5" value="password">
            <v-container>
                <v-layout row wrap>
                    <v-flex xs12 lg6>
                        <v-expansion-panel popout>
                            <v-expansion-panel-content key="3">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="red" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            認証PW設定画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="4">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="red" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            秘密の質問設定画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="5">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="red" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            認証PW設定完了画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>

                        </v-expansion-panel>
                    </v-flex>

                    <v-flex xs12 lg6>

                        <v-expansion-panel inset>
                            <v-expansion-panel-content key="1">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="primary" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>cached</v-icon>
                                            </v-avatar>
                                            認証PW変更画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="2">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="blue lighten-1" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            秘密の質問入力画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="6">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="red" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            認証PW再設定画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                            <v-expansion-panel-content key="7">
                                <template v-slot:header>
                                    <div class="text-xs-center">
                                        <v-chip color="red" text-color="white">
                                            <v-avatar>
                                                <v-icon dark>zoom_in</v-icon>
                                            </v-avatar>
                                            認証PW入力画面
                                        </v-chip>
                                    </div>
                                </template>
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-tab-item>
        <v-tab-item key="6" value="card">
            <v-expansion-panel expand>
                <v-expansion-panel-content key="1">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="primary" text-color="white">
                                <v-avatar>
                                    <v-icon dark>cached</v-icon>
                                </v-avatar>
                                登録カード一覧選択画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
                <v-expansion-panel-content key="2">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="blue lighten-1" text-color="white">
                                <v-avatar>
                                    <v-icon dark>zoom_in</v-icon>
                                </v-avatar>
                                登録カード一覧画面
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
                                登録可能会社一覧画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-tab-item>
        <v-tab-item key="7" value="login">
            <v-expansion-panel expand>
                <v-expansion-panel-content key="1">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="primary" text-color="white">
                                <v-avatar>
                                    <v-icon dark>cached</v-icon>
                                </v-avatar>
                                サービス案内・規約同意画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
                <v-expansion-panel-content key="2">
                    <template v-slot:header>
                        <div class="text-xs-center">
                            <v-chip color="blue lighten-1" text-color="white">
                                <v-avatar>
                                    <v-icon dark>zoom_in</v-icon>
                                </v-avatar>
                                登録完了画面
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
                                利用規約画面
                            </v-chip>
                        </div>
                    </template>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-tab-item>
    </v-tabs>

</template>

<script>
    export default {
        data: () => ({
            tabs: [
                {name: '支払い', href: 'payment', icon: 'monetization_on'},
                {name: 'チャージ', href: 'charge', icon: 'add'},
                {name: '残高・履歴', href: 'balance', icon: 'history'},
                {name: 'メニュー', href: 'menu', icon: 'list'},
                {name: '認証PW', href: 'password', icon: 'visibility_off'},
                {name: 'カード登録', href: 'card', icon: 'payment'},
                {name: '登録', href: 'login', icon: 'person_add'}
            ],
            headers: [
                {text: '画面名', align: 'left', sortable: false, value: 'name'},
                {text: '電文', value: 'reqRes', sortable: false,}
            ],
            items: [
                { name: '支払いバーコード画面',
                    chips: [
                        { color: 'deep-purple darken-2', name: 'SSO認証'},
                        { color: 'deep-purple darken-2', name: 'ワンタイムバーコード取得'},
                    ]
                }
            ]


        }),
        methods: {},
        created() {
        },
        watch: {},
        computed: {}
    }
</script>

<style scoped>
    @import '../../assets/css/common_new.css';
    @import '../../assets/css/payment1.css';
    @import '../../assets/css/charge.css';

    .btn {
        margin: 0 !important;
    }

    .btn-img {
        width: 25px;
        padding: 0 0 20px 0;
    }

    .btn-img2 {
        width: 15px;
        padding: 0 0 20px 10px;
    }

    .scroll-card {
        overflow-y: scroll
    }

    .changeButton {
        position: absolute;
        top: -9px;
        right: 1%;
        width: 100px;
    }

    .no-margin {
        margin-bottom: 0 !important;
    }

</style>