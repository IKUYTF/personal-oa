<template>
    <v-container fill-height fluid grid-list-xl grid-list-lg>
        <v-layout justify-center align-center>
            <v-flex xs12>
                <material-card
                        color="green"
                        title="微信接口测试"
                        text="测试"
                        class="card-tabs">

                    <v-container fluid grid-list-md>
                        <v-layout wrap>

                            <v-flex xs12 md12>

                                <v-text-field class="text-normal"
                                              label="结果"
                                              v-model="result"
                                              outline
                                ></v-text-field>
                            </v-flex>

                            <v-flex xs12 md12>
                                <v-text-field class="text-normal"
                                              label="URL"
                                              v-model="url"
                                              outline
                                ></v-text-field>
                            </v-flex>
                        </v-layout>


                        <v-btn color="success" class="btn" @click="test">確認
                        </v-btn>
                    </v-container>
                </material-card>
            </v-flex>

        </v-layout>
    </v-container>
</template>


<script>
    import $ from 'jquery'
    export default {
        data: () => ({
            qrcode:'186E04B2EE6C7526E53C550514C98F17',
            accessPwd:'3b21381fe72021b8e7cb31efa48058a1',
            appKey:'Ri3ouzmL',
            customerCode:'CID19018THCN',
            deviceType:'6',
            genuineCode:'',
            deviceId:'testwx',
            timeZoneRowOffset:'32400000',
            language:'zh_CN',
            httpsServer:'https://www.stag.c2v-c.jp/C2V_WS/TagAuthOpenWebService/',
            wsS10Api:'authNCodeOpen',
            result:'',
            url:''
        }),
        methods: {
            test() {
                this.url = this.httpsServer + this.wsS10Api;
                let requestParam = 'json={"accessPwd":"' + this.accessPwd
                    + '","appKey":"' + this.appKey + '","customerCode":"' + this.customerCode
                    + '","deviceType":"' + this.deviceType + '","genuineCode":"' + this.genuineCode
                    + '","deviceId":"' + this.deviceId + '","language":"' + this.language
                    + '","timeZoneRowOffset":"' + this.timeZoneRowOffset + '","twoDBarCode":"' + this.qrcode + '"}';
                    // let ajax = new XMLHttpRequest();
                $.ajax({
                    url: this.url,
                    type: "post",
                    contentType:'application/json;charset=utf-8',
                    data: requestParam,
                    crossDomain: true,
                    success: function(data)
                    {
                        this.result = data;
                    },
                    error:function(result){
                        console.log(result);
                    }
                });

                // ajax.open( "POST" , this.url);
                // ajax.setRequestHeader( "Content-Type" , "application/x-www-form-urlencoded;charset=UTF-8" );
                // ajax.setRequestHeader( "Access-Control-Allow-Origin" , "https://www.stag.c2v-c.jp" );
                // ajax.onreadystatechange  = function () {
                //     if( ajax.readyState === 4 ) {
                //         if( ajax.status === 200 ) {
                //             this.result = ajax.responseText ;
                //         }
                //         else {
                //             this.result = "HTTP请求错误！错误码："+ajax.status;
                //         }
                //     }
                //     else {
                //         this.result = ajax.responseText ;
                //     }
                // };
                // ajax.send(requestParam);
                //
                // let httpRequest = new XMLHttpRequest();
                // httpRequest.open('POST', this.url, true); //第二步：打开连接
                // httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");//设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
                // httpRequest.send(requestParam);//发送请求 写在send中
                //
                // httpRequest.onreadystatechange = function () {//请求后的回调接口，可将请求成功后要执行的程序写在其中
                //     if (httpRequest.status === 200) {//验证请求是否发送成功 httpRequest.readyState == 4 &&
                //         if (httpRequest.responseText.authResult === "1") {
                //             this.result = httpRequest.responseText;
                //         }
                //         else {
                //             this.result = httpRequest.responseText;
                //         }
                //     }
                //     else {
                //         this.result = httpRequest.responseText;
                //     }
                // };
            }
        }
    }
</script>

<style scoped>

    .btn {
        width: 400px
    }

    .text-normal {
        margin: 2px;
    }

</style>