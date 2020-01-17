<template>
    <v-container fill-height fluid grid-list-xl grid-list-lg>
        <v-layout justify-center align-center>
            <v-flex xs12>
                <material-card
                        color="green"
                        title="7PayApp起動"
                        text="起動パラメータ"
                        class="card-tabs">

                    <v-container fluid grid-list-md>
                        <v-layout row wrap>
                            <v-flex xs5>
                                <v-select class="select-normal"
                                          :items="items"
                                          item-text="text"
                                          item-value="value"
                                          v-model="value"
                                          label="7Pay環境選択"
                                          outline @change="changeLink"
                                ></v-select>
                            </v-flex>
                            <v-flex xs7>
                                <v-select class="select-normal"
                                          :items="pages"
                                          item-text="text"
                                          item-value="value"
                                          v-model="page"
                                          label="入口画面"
                                          outline @change="changeLink"
                                ></v-select>
                            </v-flex>
                        </v-layout>

                        <v-layout row wrap justify-center>
                            <v-flex xs5>
                                <v-text-field class="text-normal"
                                              label="UUID"
                                              v-model="uuid"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs7>
                                <v-text-field class="text-normal"
                                              label="UserHash"
                                              v-model="userHash"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>
                        <v-layout>

                            <v-flex xs12>
                                <v-textarea
                                        outline
                                        name="7PayUrl"
                                        label="7PayUrl"
                                        :value="url" append-outer-icon="add"
                                        @click:append-outer="open7Pay"
                                        no-resize auto-grow rows="2"
                                ></v-textarea>
                            </v-flex>
                        </v-layout>

                        <div class="text-xs-center">
                            <v-btn :loading="loading" :disabled="loading"
                                   color="blue-grey" class="white--text" @click="openLogServer(1)">
                                東日本ログ
                                <v-icon right dark>cloud_upload</v-icon>
                            </v-btn>

                            <v-btn :loading="loading3" :disabled="loading3"
                                    color="blue-grey" class="white--text"
                                    @click="openLogServer(2)"
                            >
                                西日本ログ
                                <v-icon right dark>cloud_upload</v-icon>
                            </v-btn>

                            <v-btn
                                    :loading="loading2"
                                    :disabled="loading2"
                                    color="success"
                                    @click="openLogServer(3)"
                            >
                                内連環境ログ
                                <template v-slot:loader>
                                    <span>Loading...</span>
                                </template>
                                <v-icon right dark>cloud_upload</v-icon>
                            </v-btn>

                            <v-btn
                                    :loading="loading4"
                                    :disabled="loading4"
                                    color="success"
                                    @click="openLogServer(4)"
                            >
                                内連環境STUB
                                <template v-slot:loader>
                                    <span class="custom-loader">
                                        <v-icon light>cached</v-icon>
                                    </span>
                                </template>
                                <v-icon right dark>launch</v-icon>
                            </v-btn>
                        </div>
                    </v-container>
                </material-card>
            </v-flex>

        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data: () => ({
            items: [
                {text: '本地環境', value: 'http://localhost:8084/IB/'},
                {text: '内連環境', value: 'https://dev7pay-je-wap01-wv.azurewebsites.net/IB/'}
            ],
            pages: [
                {text: '支払いバーコード画面', value: 'AZ_A_PY_001/AZ_A_PY_001_001.aspx?'},
                {text: '規約同意画面', value: 'AZ_A_RG_001/AZ_A_RG_001_001.aspx?'}
            ],
            url: '',
            value: 'https://dev7pay-je-wap01-wv.azurewebsites.net/IB/',
            page: 'AZ_A_PY_001/AZ_A_PY_001_001.aspx?',
            uuid: 'ikuUuid',
            userHash: 'ikuHash',

            loader: null,
            loading: false,
            loading2: false,
            loading3: false,
            loading4: false
        }),
        methods: {
            open7Pay() {
                window.open(this.url, 'target', '');
            },
            changeLink() {
                let otherUrl = "appkind=SEJWB&appid=ikuAppid&tn=ikuTn&ts=ikuTs&sig=ikuSig";
                this.url = this.value + this.page + otherUrl + "&uuid=" + this.uuid + "&userhash=" + this.userHash;
            },
            openLogServer(btnNum) {
                let serverLink;
                if (btnNum === 1) {
                    this.loader = 'loading';
                    serverLink = "https://pro7pay-je-wap01-wv.scm.azurewebsites.net/DebugConsole/?shell=powershell";
                } else if (btnNum === 2) {
                    this.loader = 'loading2';
                    serverLink = "https://pro7pay-jw-wap01-wv.scm.azurewebsites.net/DebugConsole/?shell=powershell";
                } else if (btnNum === 3) {
                    this.loader = 'loading3';
                    serverLink = "https://tst7pay-je-wap01-wv.scm.azurewebsites.net/DebugConsole/?shell=powershell";
                } else if (btnNum === 4) {
                    this.loader = 'loading4';
                    serverLink = "https://7Pay-stub.scm.azurewebsites.net/DebugConsole/?shell=powershell";
                }
                window.open(serverLink, '_blank', '');
            }
        },
        created() {
            let otherUrl = "appkind=SEJW&appid=ikuAppid&tn=ikuTn&ts=ikuTs&sig=ikuSig";
            this.url = this.value + this.page + otherUrl + "&uuid=" + this.uuid + "&userhash=" + this.userHash;
        },
        watch: {
            loader () {
                const l = this.loader
                this[l] = !this[l]

                setTimeout(() => (this[l] = false), 1000)

                this.loader = null
            }
        }
    }
</script>

<style scoped>
    .select-normal {
        margin: 2px;
    }


    .text-normal {
        margin: 2px;
    }

    .custom-loader {
        animation: loader 1s infinite;
        display: flex;
    }
    @-moz-keyframes loader {
        from {
            transform: rotate(0);
        }
        to {
            transform: rotate(360deg);
        }
    }
    @-webkit-keyframes loader {
        from {
            transform: rotate(0);
        }
        to {
            transform: rotate(360deg);
        }
    }
    @-o-keyframes loader {
        from {
            transform: rotate(0);
        }
        to {
            transform: rotate(360deg);
        }
    }
    @keyframes loader {
        from {
            transform: rotate(0);
        }
        to {
            transform: rotate(360deg);
        }
    }
</style>