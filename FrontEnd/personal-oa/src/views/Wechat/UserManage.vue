<template>
    <div class="data-table">
        <v-toolbar flat color="white">
            <v-toolbar-title>ユーザー管理</v-toolbar-title>
            <v-divider class="mx-2" inset vertical></v-divider>
            <v-text-field label="検索..." append-icon="search" v-model="word" @click:append="searchWord"></v-text-field>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-dialog v-model="dialog" max-width="400px">
                <template v-slot:activator="{ on }">
                    <v-btn color="#1867c0" dark class="mb-2" v-on="on">ユーザーを追加</v-btn>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container grid-list-md>
                            <v-layout wrap>
                                <v-form ref="form" v-model="valid" lazy-validation>
                                    <v-text-field v-model="editedItem.username" :counter="20"
                                                  :error-messages="errorMessages"
                                                  label="ユーザー名" required :disabled="editedIndex > -1 ? true : false"
                                                  :rules="[ v => !!v || 'ユーザー名を入力してください',
                                                            v => (v && v.length >= 4 && v.length <= 20) || 'ユーザー名を4~20桁以上入力してください',
                                                            nameCheck
                                                  ]"></v-text-field>
                                    <v-text-field v-model="editedItem.password"
                                                  :append-icon="show ? 'visibility' : 'visibility_off'"
                                                  :type="show ? 'text' : 'password'" :counter="16"
                                                  :rules="passwordRules" label="パスワード"
                                                  @click:append="show = !show" required></v-text-field>
                                    <v-checkbox v-model="editedItem.shopAuthFlg" label="店舗管理権限"></v-checkbox>
                                    <v-checkbox v-model="editedItem.userAuthFlg" label="ユーザー管理権限"></v-checkbox>
                                    <v-btn :disabled="!valid" color="success" class="btn" @click="save">確認</v-btn>
                                    <v-btn color="error" class="btn" @click="reset">リセット</v-btn>
                                    <v-btn color="warning" class="btn" @click="close">キャンセル</v-btn>
                                </v-form>
                            </v-layout>
                        </v-container>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-toolbar>
        <v-snackbar
                v-model="snackbar"
                color='success'
                top
        >
            {{ alertText }}
            <v-icon dark flat @click="snackbar = false">close</v-icon>
        </v-snackbar>
        <v-data-table :headers="headers" :items="userInfo" :pagination.sync="pagination" class="elevation-1">
            <template v-slot:items="props">
                <td>{{ props.item.username }}</td>
                <td class="text-xs-right">{{ props.item.password }}</td>
                <td class="text-xs-right">{{ props.item.shopAuthFlg === true ? "〇" : "×"}}</td>
                <td class="text-xs-right">{{ props.item.userAuthFlg === true ? "〇" : "×"}}</td>
                <td class="text-xs-right">{{ props.item.createTimeStr }}</td>
                <td class="text-xs-right">{{ props.item.updateTimeStr }}</td>
                <td class="justify-center layout px-0">
                    <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
                    <v-icon small @click="deleteItem(props.item)">delete</v-icon>
                </td>
            </template>
            <template v-slot:no-data>
                <v-btn color="primary" @click="initialize">Reset</v-btn>
            </template>
        </v-data-table>
    </div>
</template>

<script>
    import Icons from "../Icons";

    export default {
        components: {Icons},
        data: () => ({
            dialog: false,
            valid: false,
            show: false,
            snackbar: false,
            alertText: '',
            headers: [
                {text: 'ユーザー名', align: 'left', sortable: false, value: 'username'},
                {text: 'パスワード', align: 'right', value: 'password'},
                {text: 'ユーザー管理権限', align: 'right', value: 'userAuthFlg'},
                {text: '店舗管理権限', align: 'right', value: 'shopAuthFlg'},
                {text: '登録時間', align: 'right', value: 'createTimeStr'},
                {text: '更新時間', align: 'right', value: 'updateTimeStr'},
                {text: '操作', align: 'center', value: 'action', sortable: false}
            ],
            pagination: {
                sortBy: 'username',
                rowsPerPage: '10'
            },
            passwordRules: [
                v => !!v || 'パスワードを入力してください',
                v => (v && v.length >= 4 && v.length <= 16) || 'パスワードを6~16桁入力してください'
            ],
            userInfo: [],
            editedIndex: -1,
            editedItem: {
                username: '',
                password: '',
                shopAuthFlg: false,
                userAuthFlg: false,
                createTimeStr: '',
                updateTimeStr: ''
            },
            defaultItem: {
                username: '',
                password: '',
                shopAuthFlg: false,
                userAuthFlg: false,
                createTimeStr: '',
                updateTimeStr: ''
            },
            localUrl: 'http://localhost:8200/user',
            errorMessages: '',
            word:''
        }),

        computed: {
            formTitle() {
                return this.editedIndex === -1 ? '新ユーザー追加' : 'ユーザー情報変更'
            }
        },

        watch: {
            dialog(val) {
                val || this.close()
            }
        },

        created() {
            this.initialize()
        },

        methods: {
            initialize() {
                this.word = '';
                const url = this.localUrl + '/user-getAll'
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.userInfo = res.data;
                });

            },

            editItem(item) {
                this.editedIndex = this.userInfo.indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },

            deleteItem(item) {
                const index = this.userInfo.indexOf(item)
                confirm('削除しますか。') && this.userInfo.splice(index, 1)
            },

            close() {
                this.dialog = false;
                this.$refs.form.resetValidation();
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            },

            save() {
                if (this.$refs.form.validate()) {
                    let url = '';
                    if (this.editedIndex > -1) {
                        url = this.localUrl + '/user-update'
                    } else {
                        url = this.localUrl + '/user-insert'
                    }
                    this.$axios({
                        method: 'post',
                        url: url,
                        data: this.editedItem
                    }).then((res) => {
                        this.snackbar = true;
                        this.alertText = 'ユーザー更新済み';
                        this.userInfo = res.data;
                    }).catch(function (response) {
                        console.log(response)
                    });
                    this.close()
                }

            },
            searchWord() {

                const url = this.localUrl + '/user-get/' + this.word
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.userInfo = res.data;
                });
            },
            reset() {
                this.$refs.form.reset()
            },
            close1() {
                this.$refs.form.resetValidation()
            },
            nameCheck() {
                if(this.editedIndex > -1 || this.editedItem.username === '')
                    return true;
                const url = this.localUrl + '/user-check/' + this.editedItem.username;
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    if (res.data === 1) {
                        this.errorMessages = 'ユーザーが存在しました';
                        return false;
                    } else {
                        this.errorMessages = '';
                        return true
                    }
                });
                return true;
            },
        }
    }
</script>

<style scoped>
    .data-table {
        margin: 10px;
    }

    .btn {
        width:107px;
    }

</style>