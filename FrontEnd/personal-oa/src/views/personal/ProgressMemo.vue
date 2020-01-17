<template>

    <v-container fill-height fluid grid-list-xl>
        <v-layout wrap>
            <v-flex md12>
                <v-toolbar flat color="white" class="toolbar-task">
                    <v-toolbar-title>タスク管理</v-toolbar-title>
                    <v-divider class="mx-2" inset vertical></v-divider>
                    <v-text-field
                            label="検索..."
                            class="search-text"
                            append-icon="search"
                            @click:append="searchWord"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-spacer></v-spacer>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="900px" persistent>
                        <template v-slot:activator="{ on }">
                            <v-btn color="#1867c0" dark class="mb-2" v-on="on">タスク追加</v-btn>
                        </template>
                        <v-card>
                            <v-card-title>
                                <span class="headline">タスク詳細情報</span>
                            </v-card-title>
                            <v-card-text>
                                <v-form ref="form" lazy-validation>
                                    <v-container grid-list-xl>
                                        <v-layout wrap>
                                            <v-flex xs12 md5>
                                                <v-text-field v-model="editedItem.name" :counter="20"
                                                              :rules="checkRules(1)" label="タスク名"
                                                              :error-messages="errorMsgTask" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md7>
                                                <v-text-field v-model="editedItem.summary" :counter="40"
                                                              :rules="checkRules(2)" label="内容概要"
                                                              :error-messages="errorMsgTask" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md3>
                                                <v-select
                                                        :items="taskTypes"
                                                        v-model="editedItem.type"
                                                        label="タスク種別"
                                                ></v-select>
                                            </v-flex>
                                            <v-flex xs12 md3>
                                                <v-select
                                                        :items="status"
                                                        v-model="editedItem.status"
                                                        label="進捗状況"
                                                ></v-select>
                                            </v-flex>
                                            <v-flex xs12 md3>
                                                <v-text-field v-model="editedItem.projectDate" :counter="40"
                                                              :rules="checkRules(3)" label="予定完了日"
                                                              :error-messages="errorMsgTask" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md3>
                                                <v-text-field v-model="editedItem.realDate" :counter="40"
                                                              :rules="checkRules(4)" label="実際完了日"
                                                              :error-messages="errorMsgTask" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md12>
                                                <v-textarea rows="10"
                                                            outline
                                                            name="input-7-4"
                                                            label="詳細"
                                                            v-model="editedItem.details"
                                                ></v-textarea>
                                            </v-flex>

                                            <v-flex xs12 md6>
                                                <v-btn color="success" class="btn" @click="save">確認
                                                </v-btn>
                                            </v-flex>
                                            <v-flex xs12 md6>
                                                <v-btn color="warning" class="btn" @click="close">キャンセル</v-btn>
                                            </v-flex>
                                        </v-layout>
                                    </v-container>
                                </v-form>
                            </v-card-text>
                        </v-card>
                    </v-dialog>
                </v-toolbar>

            </v-flex>
            <v-flex md12 lg12>
                <material-card class="card-tabs card-task" color="green">
                    <v-flex slot="header">
                        <v-tabs v-model="tabs" color="transparent" slider-color="white">
                            <span class="subheading font-weight-light mr-3" style="align-self: center">Tasks:</span>
                            <v-tab class="mr-3">
                                <v-icon class="mr-2">mdi-bug</v-icon>
                                未完成：{{items.length}}件
                            </v-tab>
                            <v-tab class="mr-3">
                                <v-icon class="mr-2">mdi-code-tags</v-icon>
                                完了：{{finishedItems.length}}件
                            </v-tab>
                            <v-tab class="mr-3">
                                <v-icon class="mr-2">assignment</v-icon>
                                メモ
                            </v-tab>
                        </v-tabs>
                    </v-flex>
                    <v-tabs-items v-model="tabs">
                        <v-tab-item key="1">
                            <v-data-table :headers="headers" :items="items" :pagination.sync="pagination"
                                          class="elevation-1 table-def" no-data-text="データがなし">
                                <template slot="headerCell" slot-scope="{ header }">
                                    <span class="font-weight-light text-warning text--darken-3" v-text="header.text"/>
                                </template>
                                <template v-slot:items="props">
                                    <tr :active="props.selected" @click="showDetails(props.item, props.index)">
                                        <td>{{ props.index + 1 }}</td>
                                        <td class="text-xs-center">{{ props.item.name }}</td>
                                        <td class="text-xs-center">{{ props.item.summary}}</td>
                                        <td class="text-xs-center">{{ props.item.projectDate}}</td>
                                        <td class="text-xs-center">{{ props.item.realDate }}</td>
                                        <td class="text-xs-center">{{ props.item.status }}</td>
                                    </tr>
                                </template>
                                <template v-slot:no-data>
                                    <v-btn color="primary" @click="initialize">Reset</v-btn>
                                </template>
                            </v-data-table>
                        </v-tab-item>
                        <v-tab-item key="2">
                            <v-data-table :headers="headers" :items="finishedItems" :pagination.sync="pagination"
                                          class="elevation-1 table-def">
                                <template slot="headerCell" slot-scope="{ header }">
                                    <span class="font-weight-light text-warning text--darken-3" v-text="header.text"/>
                                </template>
                                <template v-slot:items="props">
                                    <tr :active="props.selected" @click="showDetails(props.item, props.index)">
                                        <td>{{ props.index + 1 }}</td>
                                        <td class="text-xs-center">{{ props.item.name }}</td>
                                        <td class="text-xs-center">{{ props.item.summary}}</td>
                                        <td class="text-xs-center">{{ props.item.projectDate}}</td>
                                        <td class="text-xs-center">{{ props.item.realDate }}</td>
                                        <td class="text-xs-center">{{ props.item.status }}</td>
                                    </tr>
                                </template>
                                <template v-slot:no-data>
                                    <v-btn color="primary" @click="initialize">Reset</v-btn>
                                </template>
                            </v-data-table>
                        </v-tab-item>
                        <v-tab-item key="3">
                            <v-data-table :headers="memoHeaders" :items="memoItems" :pagination.sync="pagination"
                                          class="elevation-1 table-def">
                                <template slot="headerCell" slot-scope="{ header }">
                                    <span class="font-weight-light text-warning text--darken-3" v-text="header.text"/>
                                </template>
                                <template v-slot:items="props">
                                    <tr :active="props.selected" @click="showDetails(props.item, props.index)">
                                        <td>{{ props.index + 1 }}</td>
                                        <td class="text-xs-center">{{ props.item.name }}</td>
                                        <td class="text-xs-center">{{ props.item.summary}}</td>
                                    </tr>
                                </template>
                                <template v-slot:no-data>
                                    <v-btn color="primary" @click="initialize">Reset</v-btn>
                                </template>
                            </v-data-table>
                        </v-tab-item>
                    </v-tabs-items>

                    <v-snackbar v-model="snackbar" color='success' top>
                        {{ alertText }}
                        <v-icon dark flat @click="snackbar = false">close</v-icon>
                    </v-snackbar>
                </material-card>

            </v-flex>

        </v-layout>
    </v-container>

</template>

<script>
    export default {
        data() {
            return {
                headers: [
                    {sortable: false, text: 'ID', value: 'id'},
                    {sortable: false, text: 'タスク名', value: 'name', align: 'center'},
                    {sortable: false, text: '内容', value: 'summary', align: 'center'},
                    {sortable: false, text: '予定完了日', value: 'projectDate', align: 'center'},
                    {sortable: false, text: '実際完了日', value: 'realDate', align: 'center'},
                    {sortable: false, text: '進捗状況', value: 'status', align: 'center'}
                ],
                memoHeaders: [
                    {sortable: false, text: 'ID', value: 'id'},
                    {sortable: false, text: 'メモ名', value: 'name', align: 'center'},
                    {sortable: false, text: '内容', value: 'summary', align: 'center'}
                ],

                pagination: {
                    sortBy: 'id',
                    rowsPerPage: '10'
                },
                snackbar: false,
                dialog: false,
                alertText: '测试',
                finishItems: [],
                errorMsgTask: '',
                items: [],
                finishedItems: [],
                memoItems: [],
                editedIndex: -1,
                editedItem: {
                    name: '',
                    summary: '',
                    details: '',
                    projectDate: '',
                    realDate: '',
                    status: '',
                    type: ''
                },
                defaultItem: {
                    name: '',
                    summary: '',
                    details: '',
                    projectDate: '',
                    realDate: '',
                    status: '',
                    type: ''
                },
                status: ['未着手', '作成中', '完了', '保留'],
                taskTypes: ['Bug調査', 'テスト', '設計書', 'メモ', 'その他'],
                tabs: 0,
                localUrl: '',
            }
        },
        created() {
            this.localUrl = global.backServerEndpoint + '/task';
            this.initialize()
        },
        methods: {
            initialize() {
                this.word = '';
                const url = this.localUrl + '/task-getUnfinish'
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.items = res.data;

                });
                const url1 = this.localUrl + '/task-getFinish'
                this.$axios({
                    method: 'get',
                    url: url1
                }).then((res) => {
                    this.finishedItems = res.data;

                });
                const url2 = this.localUrl + '/task-getMemo'
                this.$axios({
                    method: 'get',
                    url: url2
                }).then((res) => {
                    this.memoItems = res.data;

                });

            },
            showDetails(item, index) {
                this.editedIndex = index;
                this.editedItem = Object.assign({}, item);
                this.dialog = true;
            },
            save() {
                if (this.$refs.form.validate()) {
                    let url = '';
                    if (this.editedIndex > -1) {
                        url = this.localUrl + '/task-update'
                    } else {
                        url = this.localUrl + '/task-insert'
                    }
                    this.$axios({
                        method: 'post',
                        url: url,
                        data: this.editedItem
                    }).then((res) => {
                        this.snackbar = true;
                        this.alertText = 'タスク情報更新済み';
                        this.initialize();
                        this.close()
                    }).catch(function (response) {
                        console.log(response)
                    });
                }
            },
            close() {
                this.dialog = false;
                this.$refs.form.resetValidation();
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            },
            checkRules(num) {
                if (num === 1) { //　タスク名

                }
            },
            searchWord() {

            }
        }
    }
</script>

<style>

    .btn {
        width: 170px;
    }

    .toolbar-task {
        margin-bottom: 0!important;
        padding-bottom: 0!important;
    }

    .card-task {
        margin-top: 0!important;
        padding-top: 0!important;
    }
</style>
