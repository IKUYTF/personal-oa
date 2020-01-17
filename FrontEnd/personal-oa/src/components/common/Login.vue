<template>
    <div class="login-wrap">
        <div class="ms-login">
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
            <div class="ms-title">勤怠管理システム</div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password"
                              @keyup.enter.native="submitForm('ruleForm')">
                        <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">ログイン</el-button>
                </div>
                <p class="login-tips">Copyright (C); THS Co. Ltd. All Rights Reserved.</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    username: '',
                    password: ''
                },
                admin: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        { required: true, message: 'ユーザーIDを入力してください。', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: 'パスワードを入力してください。', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        localStorage.setItem('ms_username',this.ruleForm.username);
                        this.$router.push('/index');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
        background-image: url(../../assets/img/login-bg.jpg);
        background-size: 100%;
    }
    .ms-title{
        width:100%;
        line-height: 50px;
        text-align: center;
        font-size:20px;
        color: #4f4f6b;
        font-weight: bold;
        border-bottom: 1px solid #ddd;
        padding: 0 0  0 60px;
    }
    .ms-login{
        position: absolute;
        left:50%;
        top:50%;
        width:400px;
        margin:-190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255,255,255, 0.3);
        overflow: hidden;
    }
    .ms-content{
        padding: 30px 50px 20px 50px;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:70%;
        height:36px;
        margin-bottom: 10px;
    }
    .login-tips{
        font-size:12px;
        line-height:30px;
        color:#fff;
        text-align: right;
    }
    .ths {
        float: left;
        font-size: 3em;
        font-family:'Tw Cen MT Condensed';
        margin-top: -5px;
        letter-spacing:-3px;
        padding: 0 0  0 40px;
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