<template>
    <v-container fill-height fluid grid-list-xl grid-list-lg>
        <v-layout justify-center align-center>
            <v-flex xs12>
                <material-card
                        color="green"
                        title="簡易ツール"
                        text="開発中"
                        class="card-tabs">

                    <v-container fluid grid-list-md>


                        <v-layout row wrap justify-center>
                            <v-flex xs6>
                                <v-text-field class="text-normal"
                                              label="QRコード幅（px）"
                                              v-model="qrWidth"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs6>
                                <v-text-field class="text-normal"
                                              label="QRコード Version（1-40）"
                                              v-model="qrVersion"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                        </v-layout>   <v-layout row wrap justify-center>
                            <v-flex xs4>
                                <v-text-field class="text-normal"
                                              label="Screen pixel（length）"
                                              v-model="screenL"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                            <v-flex xs4>
                                <v-text-field class="text-normal"
                                              label="Screen pixel（width）"
                                              v-model="screenW"
                                              outline @input="changeLink"
                                ></v-text-field>
                            </v-flex>
                        <v-flex xs4>
                            <v-text-field class="text-normal"
                                          label="Screen Size（inch）"
                                          v-model="screenSize"
                                          outline @input="changeLink"
                            ></v-text-field>
                        </v-flex>
                        </v-layout>
                        <v-layout>

                            <v-flex xs12>

                                <v-text-field class="text-normal"
                                              label="Per Cell Width（mm）"
                                              v-model="cellWidth"
                                              outline
                                ></v-text-field>
                            </v-flex>
                        </v-layout>

                        <!--<div class="text-xs-center">-->
                            <!--<v-btn :loading="loading" :disabled="loading"-->
                                   <!--color="blue-grey" class="white&#45;&#45;text" @click="openLogServer(1)">-->
                                <!--東日本ログ-->
                                <!--<v-icon right dark>cloud_upload</v-icon>-->
                            <!--</v-btn>-->

                            <!--<v-btn :loading="loading3" :disabled="loading3"-->
                                    <!--color="blue-grey" class="white&#45;&#45;text"-->
                                    <!--@click="openLogServer(2)"-->
                            <!--&gt;-->
                                <!--西日本ログ-->
                                <!--<v-icon right dark>cloud_upload</v-icon>-->
                            <!--</v-btn>-->

                            <!--<v-btn-->
                                    <!--:loading="loading2"-->
                                    <!--:disabled="loading2"-->
                                    <!--color="success"-->
                                    <!--@click="openLogServer(3)"-->
                            <!--&gt;-->
                                <!--内連環境ログ-->
                                <!--<template v-slot:loader>-->
                                    <!--<span>Loading...</span>-->
                                <!--</template>-->
                                <!--<v-icon right dark>cloud_upload</v-icon>-->
                            <!--</v-btn>-->

                            <!--<v-btn-->
                                    <!--:loading="loading4"-->
                                    <!--:disabled="loading4"-->
                                    <!--color="success"-->
                                    <!--@click="openLogServer(4)"-->
                            <!--&gt;-->
                                <!--内連環境STUB-->
                                <!--<template v-slot:loader>-->
                                    <!--<span class="custom-loader">-->
                                        <!--<v-icon light>cached</v-icon>-->
                                    <!--</span>-->
                                <!--</template>-->
                                <!--<v-icon right dark>launch</v-icon>-->
                            <!--</v-btn>-->
                        <!--</div>-->
                    </v-container>
                </material-card>
            </v-flex>

        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data: () => ({
            qrWidth: '150',
            qrVersion: '1',
            dpr: '3',
            cellWidth: '',
            screenL: '1920',
            screenW: '1080',
            screenSize: '5.5',

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
                let dpi = Math.sqrt(Math.pow(this.screenL,2) + Math.pow(this.screenW,2))/(this.screenSize* 25.4);
                let pxNum = 21+(this.qrVersion-1)*4;
                this.cellWidth = 1 / dpi  * this.qrWidth/pxNum;

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
                    serverLink = "https://sevenPay-stub.scm.azurewebsites.net/DebugConsole/?shell=powershell";
                }
                window.open(serverLink, '_blank', '');
            }
        },
        created() {
            this.changeLink();
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