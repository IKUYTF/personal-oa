<template>
    <v-container>
        <v-layout justify-center align-center>
            <v-flex xs5>
                <v-card class="mx-auto" height="667px" color="#FFFFFF" dark max-width="375">
                    <v-toolbar dark color="#778899" class="no-margin">
                        <v-btn flat icon>
                            <v-icon>search</v-icon>
                        </v-btn>
                        <v-toolbar-title>{{screenName}}<br/>{{screenId}}</v-toolbar-title>

                    </v-toolbar>
                    <v-card class="paymentWrap" color="#FF7200" v-if="screenId === 'PY_001_001'">
                        <p class="mainTxt">この画面を従業員にお見せください</p>
                        <div class="codeArea">
                            <div class="barCodeArea">
                                <div class="zoomButton">
                                    <v-btn flat icon class="btn-img" @click="forward('PY_001_002')">
                                        <img src="../../assets/images/payment/ico_zoom.png" alt="ズーム">
                                    </v-btn>
                                </div>
                                <div class="barCode">
                                    <img src="../../assets/images/payment/barcode.gif" alt="バーコード"
                                         @click="forward('PY_001_002')">
                                </div>
                            </div>
                            <div class="qrCode">
                                <img src="../../assets/images/payment/qrcode.gif" alt="QRコード"
                                     @click="forward('PY_001_002')">
                            </div>
                            <div class="codeRemainingTime">
                                <v-btn flat icon color="#FF7200" class="btn">
                                    <v-icon>cached</v-icon>
                                </v-btn>
                                <span>04:59</span>
                            </div>
                        </div>
                    </v-card>
                    <v-card class="paymentWrap scroll-card" color="#FFFFFF" v-if="screenId === 'PY_001_002'">
                        <div class="titleArea">
                            <div class="backButton">
                                <v-btn flat icon class="btn-img2" @click="forward('PY_001_001')">
                                    <img src="../../assets/images/common/ico_back.png" alt="">
                                </v-btn>
                            </div>
                            <div class="titleTxt">
                                <p>支払いバーコード</p>
                            </div>

                            <v-btn round class="changeButton" color="#FF7200" @click="forward('PY_001_003')">
                                番号を表示
                            </v-btn>
                        </div>
                        <div class="codeAreaBig">
                            <div class="barCodeBig">
                                <img src="../../assets/images/payment/barcode_b.gif" alt="バーコード">
                                <div class="codeRemainingTime">
                                    <v-btn flat icon color="#FF7200" class="btn">
                                        <v-icon>cached</v-icon>
                                    </v-btn>
                                    <span>04:59</span>
                                </div>
                            </div>
                            <div class="qrCodeBig">
                                <img src="../../assets/images/payment/qrcode_b.gif" alt="QRコード">
                            </div>
                        </div>
                    </v-card>
                    <v-card class="paymentWrap" color="#FFFFFF" v-if="screenId === 'PY_001_003'">
                        <div class="titleArea">
                            <div class="backButton">
                                <v-btn flat icon class="btn-img2" @click="forward('PY_001_002')">
                                    <img src="../../assets/images/common/ico_back.png" alt="">
                                </v-btn>
                            </div>
                            <div class="titleTxt">
                                <p>支払いバーコード番号</p>
                            </div>

                            <v-btn round class="changeButton" color="#FF7200" @click="forward('PY_001_002')">
                                バーコード表示
                            </v-btn>
                        </div>
                        <div class="codeAreaBig">
                            <div class="barCodeNumber">
                                <p><span>1234 5678 9012</span><br><span>3456 7890</span></p>
                                <div class="codeRemainingTime">
                                    <v-btn flat icon color="#FF7200" class="btn">
                                        <v-icon>cached</v-icon>
                                    </v-btn>
                                    <span>04:59</span>
                                </div>
                            </div>
                        </div>
                    </v-card>
                    <div class="chargeWrap wrap bgWhite closeWrap" v-if="screenId === 'CH_001_001'">

                        <div class="chargeSelectArea">
                            <div class="chargeSelectTitle">
                                <p>チャージ方法を選択</p>
                            </div>
                            <ul>
                                <li class="creditCard"><a href="#">クレジット/デビットカード</a></li>
                                <li class="cashCard"><a href="#">セブン銀行口座（デビット）</a></li>
                                <li class="sevenAtm"><a href="#">セブン銀行ATM</a></li>
                                <li class="nanacoPoint"><a href="#">nanacoポイントでチャージ</a></li>
                                <li class="registerCharge"><a href="#">レジで現金チャージ</a></li>
                            </ul>
                        </div>
                    </div>
                    <v-bottom-nav :active.sync="bottomNav" :color="color" :value="true" absolute dark shift>
                        <v-btn dark @click="forward('PY_001_001')">
                            <span>支払い</span>
                            <v-icon>payment</v-icon>
                        </v-btn>
                        <v-btn dark @click="forward('CH_001_001')">
                            <span>チャージ</span>
                            <v-icon>control_point</v-icon>
                        </v-btn>
                        <v-btn dark>
                            <span>残高・履歴</span>
                            <v-icon>account_balance_wallet</v-icon>
                        </v-btn>
                        <v-btn dark>
                            <span>メニュー</span>
                            <v-icon>settings</v-icon>
                        </v-btn>
                    </v-bottom-nav>

                </v-card>
            </v-flex>

            <v-flex xs7>
                <v-card class="mx-auto" height="667px" width="530">
                    <v-toolbar dark color="#778899" class="no-margin">
                        <v-btn flat icon>
                            <v-icon>search</v-icon>
                        </v-btn>
                        <v-toolbar-title>{{screenName}}<br/>画面説明</v-toolbar-title>
                    </v-toolbar>
                    <v-card class="paymentWrap scroll-card" v-if="screenId === 'PY_001_001'">
                        <sevenPay-py001001 />
                    </v-card>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>

</template>

<script>
    export default {
        data: () => ({
            tab: null,
            text: 'テスト',
            bottomNav: 0,
            screenId: 'PY_001_001',
            screenName: '支払いバーコード画面'
        }),
        methods: {
            forward(id) {
                this.screenId = id;
                switch (id) {
                    case 'PY_001_001':
                        this.screenName = "支払いバーコード画面";
                        break;
                    case 'PY_001_002':
                        this.screenName = "支払いバーコード拡大表示画面";
                        break;
                    case 'PY_001_003':
                        this.screenName = "支払いバーコード番号表示";
                        break;
                    case 'CH_001_001':
                        this.screenName = "チャージ方法選択画面";
                        break;
                }

            }
        },
        created() {
        },
        watch: {},
        computed: {
            color() {
                switch (this.bottomNav) {
                    case 0:
                        return '#FF7200'
                    case 1:
                        return '#34A763'
                    case 2:
                        return '#919191'
                    case 3:
                        return '#919191'
                }
            }
        }
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