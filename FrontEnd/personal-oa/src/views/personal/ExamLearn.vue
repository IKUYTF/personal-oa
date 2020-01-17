<template>
    <v-container fill-height fluid grid-list-xl>
        <v-snackbar v-model="snackbar" color='success' top>
            {{ alertText }}
            <v-icon dark flat @click="snackbar = false">close</v-icon>
        </v-snackbar>
        <v-layout justify-center wrap>
            <v-flex md12>
                <v-card color="blue-grey " dark>
                    <v-toolbar color="blue-grey lighten-1" class="toolbar-task" dark>
                        <v-toolbar-title>単語学習</v-toolbar-title>
                        <v-divider class="mx-2" inset vertical></v-divider>
                        <v-text-field color="white"
                                      label="検索..."
                                      class="search-text"
                                      append-icon="search" @click:append="searchWord"
                        ></v-text-field>
                        <v-spacer></v-spacer>
                        <v-spacer></v-spacer>
                        <v-spacer></v-spacer>
                        <v-dialog v-model="dialog" max-width="900px" persistent>
                            <template v-slot:activator="{ on }">
                                <v-btn icon v-on="on">
                                    <v-icon large right dark>add_box</v-icon>
                                </v-btn>
                            </template>
                            <v-card>
                                <v-card-title>
                                    <span class="headline">単語</span>
                                </v-card-title>
                                <v-card-text>
                                    <v-form ref="form" lazy-validation>
                                        <v-container grid-list-xl>
                                            <v-layout wrap>
                                                <v-flex xs12 md4>
                                                    <v-text-field v-model="editedItem.word" :counter="40"
                                                                  label="単語" required>
                                                    </v-text-field>
                                                </v-flex>
                                                <v-flex xs12 md4>
                                                    <v-text-field v-model="editedItem.pron" :counter="40"
                                                                  label="発音" required>
                                                    </v-text-field>
                                                </v-flex>
                                                <v-flex xs12 md4>
                                                    <v-text-field v-model="editedItem.type" :counter="40"
                                                                  label="解釈" required>
                                                    </v-text-field>
                                                </v-flex>
                                                <v-flex xs12 md12>
                                                    <v-text-field v-model="editedItem.meaning" :counter="100"
                                                                  label="意味" required>
                                                    </v-text-field>
                                                </v-flex>
                                                <v-flex xs12 md12>
                                                    <v-textarea rows="15" outline name="input-7-4" label="例"
                                                                v-model="editedItem.example"
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
                    <v-container>
                        <v-layout wrap>
                            <v-flex xs12 md5>
                                <v-combobox box v-model="select" color="blue-grey lighten-1" :disabled="isUpdating"
                                            :items="levels" chips label="Level Select" multiple></v-combobox>
                            </v-flex>
                            <v-flex xs12 md7>
                                <v-combobox box v-model="selectTypes" color="blue-grey lighten-1" :disabled="isUpdating"
                                            :items="types" chips label="Word Type Select" multiple></v-combobox>
                            </v-flex>
                        </v-layout>
                    </v-container>
                    <v-divider></v-divider>
                    <v-card-actions>
                        <v-switch v-model="randomQuestion" :disabled="isUpdating"
                                  class="mt-0" color="green lighten-2" hide-details
                                  :label="randomQuestion? '随机题库' :'顺序出题'"
                        ></v-switch>
                        <v-switch v-model="allQuestion" :disabled="isUpdating"
                                  class="mt-0" color="green lighten-2" hide-details
                                  :label="allQuestion? '查看所有题' :'过滤正确题'"
                        ></v-switch>
                        <v-spacer></v-spacer>
                        <v-btn :loading="isUpdating" color="blue-grey darken-3"
                               depressed @click="isUpdating = true">
                            <v-icon left>mdi-update</v-icon>
                            Refresh Now
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
            <v-flex md12>
                <material-card color="blue-grey darken-2" title="単語一覧"
                               :text="'当前记录单词数：' + wordsNum" class="card-tabs card-task">
                    <!--<v-data-table :headers="headers" :items="items" :pagination.sync="pagination">-->
                        <!--<template slot="headerCell" slot-scope="{ header }">-->
                            <!--<span class="subheading font-weight-light text-success text&#45;&#45;darken-3" v-text="header.text"/>-->
                        <!--</template>-->
                        <!--<template v-slot:items="props">-->
                            <!--<tr :active="props.selected" @click="showDetails(props.item, props.index)">-->
                                <!--<td>{{ props.index + 1 }}</td>-->
                                <!--<td>{{ props.item.word }}</td>-->
                                <!--<td>{{ props.item.pron }}</td>-->
                                <!--<td>{{ props.item.type }}</td>-->
                                <!--<td>{{ props.item.meaning }}</td>-->
                            <!--</tr>-->
                        <!--</template>-->
                    <!--</v-data-table>-->
                    <v-container grid-list-xl>
                        <v-layout wrap>
                            <v-flex xs12 md6>
                                <v-text-field v-model="currentItem.word" readonly label="単語" required>
                                </v-text-field>
                            </v-flex>
                            <v-flex xs12 md6>
                                <v-text-field v-model="currentItem.type"readonly label="解釈" required>
                                </v-text-field>
                            </v-flex>

                            <!--<v-flex xs12 md4>-->
                                <!--<v-text-field v-model="editedItem.pron"-->
                                              <!--label="発音" required>-->
                                <!--</v-text-field>-->
                            <!--</v-flex>-->
                            <!--<v-flex xs12 md12>-->
                                <!--<v-text-field v-model="editedItem.meaning" :counter="100"-->
                                              <!--label="意味" required>-->
                                <!--</v-text-field>-->
                            <!--</v-flex>-->
                            <!--<v-flex xs12 md12>-->
                                <!--<v-textarea rows="15" outline name="input-7-4" label="例"-->
                                            <!--v-model="editedItem.example"-->
                                <!--&gt;</v-textarea>-->
                            <!--</v-flex>-->

                            <!--<v-flex xs12 md6>-->
                                <!--<v-btn color="success" class="btn" @click="save">確認-->
                                <!--</v-btn>-->
                            <!--</v-flex>-->
                            <!--<v-flex xs12 md6>-->
                                <!--<v-btn color="warning" class="btn" @click="close">キャンセル</v-btn>-->
                            <!--</v-flex>-->
                        </v-layout>
                    </v-container>
                </material-card>
            </v-flex>


        </v-layout>
    </v-container>

</template>

<script>
    export default {
        data: () => ({
            levels: ['N1', 'N2', 'N3', 'N4', 'N5'],
            types: ['名詞', '動詞', '副詞', '形容詞', '形容動詞', '連体詞', '五十音'],
            randomQuestion: false,
            allQuestion: false,
            select: [],
            selectTypes: [],
            unchecked: false,
            friends: ['Sandra Adams', 'Britta Holt'],
            isUpdating: false,
            name: 'Midnight Crew',
            title: 'The summer breeze',
            n1Word: false,
            dialog: false,
            snackbar: false,
            alertText: '',
            headers: [
                {sortable: false, text: 'ID', value: 'id'},
                {sortable: false, text: '単語', value: 'word'},
                {sortable: false, text: '発音', value: 'pron'},
                {sortable: false, text: '解釈', value: 'type'},
                {sortable: false, text: '意味', value: 'meaning'}
            ],
            items: [],

            currentItem: {
                word: '突合',
                pron: 'とつごう',
                type: '名詞',
                meaning: '突き合わせて調べる',
                example: '突き合わせて調べる'
            },

            pagination: {
                sortBy: 'word',
                rowsPerPage: '10'
            },
            editedIndex: -1,
            editedItem: {
                word: '',
                pron: '',
                type: '',
                meaning: '',
                example: ''
            },
            localUrl: '',
            wordsNum: ''
        }),
        created() {
            this.localUrl = global.backServerEndpoint + '/words';
            this.initialize()
        },
        methods: {
            initialize() {
                const url = this.localUrl + '/words-getAll'
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.items = res.data;
                    this.wordsNum = this.items.length;
                });

            },
            searchWord() {
                alert("开发中");
            },
            checkRules(NUM) {

            },
            save() {
                if (this.$refs.form.validate()) {
                    let url = '';
                    if (this.editedIndex > -1) {
                        url = this.localUrl + '/words-update'
                    } else {
                        url = this.localUrl + '/words-insert'
                    }
                    this.$axios({
                        method: 'post',
                        url: url,
                        data: this.editedItem
                    }).then((res) => {
                        this.snackbar = true;
                        this.alertText = '情報更新済み';
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
            showDetails(item, index) {
                this.editedIndex = index;
                this.editedItem = Object.assign({}, item);
                this.dialog = true;
            },
        }
    }
</script>

<style scoped>

    .btn {
        width: 400px
    }


    .toolbar-task {
        margin-bottom: 0 !important;
        padding-bottom: 0 !important;
    }

    .card-task {
        margin-top: 0 !important;
        padding-top: 0 !important;
    }


</style>