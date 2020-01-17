<template>
    <div class="login-panel">
        <div class="login-title">
            <div class="ths">
                <table>
                    <tbody>
                    <tr>
                        <td class="t">T</td>
                        <td class="h">H</td>
                        <td class="s">S</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="ms-title">社内管理システム</div>
            <v-form ref="form" v-model="valid" lazy-validation class="ms-content">
                <v-text-field
                        v-model="username"
                        :counter="10"
                        label="ユーザー名"
                        required
                        :rules="usernameRules"
                ></v-text-field>
                <v-text-field
                        :append-icon="show3 ? 'visibility_off' : 'visibility'"
                        required
                        :type="show3 ? 'text' : 'password'"
                        name="input-10-2"
                        label="パスワード"
                        hint="任意のパスワード"
                        v-model="password"
                        class="input-group--focused"
                        :rules="passwordRules"
                        @click:append="show3 = !show3"
                ></v-text-field>

                <div class="login-btn">
                    <v-btn @click="submit">登録</v-btn>
                    <v-btn @click="clear">リセット</v-btn>
                </div>
                <p class="login-tips">Copyright (C); THS Co. Ltd. All Rights Reserved.</p>
            </v-form>
        </div>
        <v-snackbar
                v-model="snackbar"
                :timeout="2000"
                :top=true
                color="success"
        >
            正常登録しました。
            <v-btn dark flat @click="snackbar = false">
                Close
            </v-btn>
        </v-snackbar>
    </div>
</template>

<script>

    export default {
        data() {
            return {
                username: '',
                password: '',
                show3: false,
                valid: true,
                snackbar: false,

                usernameRules: [
                    v => !!v || 'ユーザー名を入力してください。'
                ],
                passwordRules: [
                    v => !!v || 'パスワードを入力してください。'
                ]
            }
        },
        metaInfo: {
            title: '个人管理系统'
        },
        methods: {
            submit() {
                if (this.$refs.form.validate()) {
                    this.snackbar = true;
                    localStorage.setItem('ms_username',this.username);
                    localStorage.setItem('permission',this.username==="admin");
                    console.log(this.username==="admin")
                    this.$router.push('/dashboard');
                }
            },
            clear() {
                this.$refs.form.reset()
            }
        }
    }
</script>

<style >
    .login-panel {
        position: absolute;
        width: 100%;
        height: 100%;
        background-image: url(../assets/img/login-bg.jpg);
        background-size: 100%;
    }

    .ms-title {
        position: relative;
        width: 100%;
        line-height: 50px;
        text-align: center;
        font-size: 20px;
        color: #4f4f6b;
        font-weight: bold;
        border-bottom: 1px solid #ddd;
        padding: 0 0 0 60px;
    }

    .login-title {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 400px;
        margin: -190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.3);
        overflow: hidden;
    }

    .ms-content {
        padding: 10px 50px 10px 50px;
    }

    .login-btn {
        text-align: center;
        /*margin-top: 15px;*/
    }

    .login-btn button {
        color: black !important;
        margin-left:30px;
        font-size: 14px;
    }

    .login-tips {
        font-size: 12px;
        line-height: 20px;
        color: #fff;
        text-align: right;
    }

    .ths {
        float: left;
        font-size: 3em;
        font-family: 'Tw Cen MT Condensed', serif;
        margin-top: -5px;
        letter-spacing: -3px;
        padding: 0 0 0 40px;
    }

    .ths .t {
        color: #00B0F0;
    }

    .ths .h {
        color: #92D050;
    }

    .ths .s {
        color: #FFC000;
    }
</style>