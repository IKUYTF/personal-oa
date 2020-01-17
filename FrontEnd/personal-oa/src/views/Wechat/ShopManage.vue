<template>
    <div class="data-table">
        <v-toolbar flat color="white">
            <v-toolbar-title>店舗管理</v-toolbar-title>
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
            <v-dialog v-model="dialog" max-width="600px">
                <template v-slot:activator="{ on }">
                    <v-btn color="#1867c0" dark class="mb-2" v-on="on">店舗追加/変更</v-btn>
                </template>
                <v-card>
                    <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                    </v-card-title>
                    <v-card-text>
                        <v-form ref="form" v-model="valid" lazy-validation>
                            <v-container grid-list-xl>
                                <v-layout wrap>
                                    <v-flex xs12 md6>
                                        <v-text-field v-model="editedItem.shopName" :counter="50"
                                                      label="店舗名" required></v-text-field>
                                        <v-text-field v-model="editedItem.shopCode" :counter="20"
                                                      label="店舗コード" required></v-text-field>
                                        <v-text-field v-model="editedItem.address" :counter="100"
                                                      label="アドレス"></v-text-field>
                                        <v-text-field v-model="editedItem.details" :counter="100"
                                                      label="明細"></v-text-field>

                                    </v-flex>
                                    <v-flex xs12 md6>
                                        <v-card>
                                            <v-img class="black--text" height="200px"
                                                   @click="enlarge(editedItem.certPath)"
                                                   :src="editedItem.certPath">
                                                <v-container fill-height fluid>
                                                    <v-layout fill-height>
                                                        <v-flex xs12 align-end flexbox>
                                                            <span v-if="editedItem.certPath === ``"
                                                                  class="headline cert-background">証書未認証</span>
                                                        </v-flex>
                                                    </v-layout>
                                                </v-container>
                                            </v-img>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn v-if="editedItem.certPath !== ``" flat color="orange"
                                                       @click="enlarge(editedItem.certPath)">拡大表示
                                                </v-btn>
                                                <v-btn flat color="orange" @click="certSelect=true">証書選択</v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-flex>

                                    <v-flex xs12 md4>
                                        <v-btn :disabled="!valid" color="success" class="btn" @click="save">確認</v-btn>
                                    </v-flex>

                                    <v-flex xs12 md4>
                                        <v-btn color="error" class="btn" @click="reset">リセット</v-btn>
                                    </v-flex>

                                    <v-flex xs12 md4>
                                        <v-btn color="warning" class="btn" @click="close">キャンセル</v-btn>
                                    </v-flex>
                                </v-layout>
                            </v-container>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-toolbar>
        <v-dialog v-model="certPicture">
            <v-card name="cert">
                <v-img :src="currentPic"
                       lazy-src="https://picsum.photos/10/6?image=15"
                       aspect-ratio="1.33" contain
                       class="grey lighten-2">
                    <template v-slot:placeholder>
                        <v-layout fill-height align-center justify-center ma-0>
                            <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                        </v-layout>
                    </template>
                </v-img>
            </v-card>
        </v-dialog>

        <v-dialog v-model="certSelect">
            <v-layout wrap>

                <v-flex xs4>

                    <v-card>
                        <v-img
                                :src="certPic1"
                                height="330"
                                class="grey darken-4"
                        >
                          <span class="headline cert-background">証書テンプレート1</span>
                        </v-img>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-text-field label="認証文字" v-model="certWord1"></v-text-field>
                            <v-btn flat color="orange" @click="">選択
                            </v-btn>
                            <v-btn flat color="orange" @click="enlarge(certPic1)">プレビュー</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>

                <v-flex xs4>
                    <v-card>
                        <v-img
                                :src="certPic2"
                                height="330"
                                contain
                                class="grey darken-4"
                        >
                            <span class="headline cert-background">証書テンプレート2</span></v-img>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-text-field label="認証文字" v-model="certWord2"></v-text-field>
                            <v-btn flat color="orange" @click="">選択
                            </v-btn>
                            <v-btn flat color="orange" @click="enlarge(certPic2)">プレビュー</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-flex>

                <v-flex xs4>
                    <v-card>
                        <v-img
                                :src="certPic3"
                                height="330"
                                class="grey darken-4"
                        >
                            <span class="headline cert-background">証書テンプレート3</span></v-img>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-text-field label="認証文字" v-model="certWord3"></v-text-field>
                                <v-btn flat color="orange" @click="">選択
                                </v-btn>
                                <v-btn flat color="orange" @click="enlarge(certPic3)">プレビュー</v-btn>
                            </v-card-actions>
                    </v-card>
                </v-flex>
            </v-layout>
        </v-dialog>
        <v-data-table :headers="headers" :items="shopInfo" class="elevation-1">
            <template v-slot:items="props">
                <td>{{ props.item.shopName }}</td>
                <td class="text-xs-right">{{ props.item.shopCode }}</td>
                <td class="text-xs-right">{{ props.item.address }}</td>
                <td class="text-xs-right">{{ props.item.details }}</td>
                <td class="text-xs-right">
                    <v-icon v-if="props.item.certFlag===`0`" color="green" @click="certView(props.item.certPath)">
                        pageview
                    </v-icon>
                    <div v-else-if="props.item.certFlag===`1`">未認証</div>
                </td>
                <td class="text-xs-right">{{ props.item.createTime }}</td>
                <td class="text-xs-right">{{ props.item.updateTime }}</td>
                <td class="justify-center layout px-0">
                    <v-icon small color="indigo" class="mr-2" @click="editItem(props.item)">edit</v-icon>
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
    export default {
        data: () => ({
            dialog: false,
            currentPic: '',
            certPicture: false,
            certSelect: false,
            certGroup: 1,
            certWord1: '',
            certWord2: '',
            certWord3: '',
            certPic1: 'https://ugcorigin.s-microsoft.com/100/488459bc-c120-4e7b-a179-377f9bb41fc8/200/v1/image.jpg',
            certPic2: 'https://public-media.interaction-design.org/certificates/course/20/MGLBHs9GK.jpg',
            certPic3: 'https://cdn-images-1.medium.com/max/1600/1*iEefaTrbHtIODMAcLC4r_g.jpeg',
            valid: false,
            headers: [
                {text: '店舗名', align: 'left', value: 'shopName'},
                {text: '店舗コード', align: 'right', value: 'shopCode'},
                {text: 'アドレス', align: 'right', value: 'address'},
                {text: '明細', align: 'right', value: 'details'},
                {text: '証書', align: 'right', value: 'certPath', sortable: false},
                {text: '登録時間', align: 'right', value: 'createTime'},
                {text: '更新時間', align: 'right', value: 'updateTime'},
                {text: '操作', align: 'center', value: 'action', sortable: false}
            ],
            shopInfo: [],
            editedIndex: -1,
            editedItem: {
                shopName: '',
                shopCode: '',
                address: '',
                certPath: '',
                details: '',
                createTime: '',
                updateTime: ''
            },
            defaultItem: {
                shopName: '',
                shopCode: '',
                address: '',
                certPath: '',
                details: '',
                createTime: '',
                updateTime: ''
            },
            localUrl: 'http://localhost:8200/manage'
        }),

        computed: {
            formTitle() {
                return this.editedIndex === -1 ? '店舗追加' : '店舗変更'
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
                const url = this.localUrl + '/shop-getAll'
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.shopInfo = res.data;
                });

            },

            editItem(item) {
                this.editedIndex = this.shopInfo.indexOf(item)
                this.editedItem = Object.assign({}, item)
                this.dialog = true
            },

            deleteItem(item) {
                const index = this.shopInfo.indexOf(item)
                confirm('Are you sure you want to delete this item?') && this.shopInfo.splice(index, 1)
            },

            close() {
                this.dialog = false
                setTimeout(() => {
                    this.editedItem = Object.assign({}, this.defaultItem)
                    this.editedIndex = -1
                }, 300)
            },

            save() {
                if (this.editedIndex > -1) {
                    Object.assign(this.shopInfo[this.editedIndex], this.editedItem)
                } else {
                    this.shopInfo.push(this.editedItem)
                }
                this.close()
            },
            searchWord() {

            },
            reset() {
                this.$refs.form.reset()
            },
            certView(path) {
                this.certPicture = true;
                this.currentPic = path;
            },
            enlarge(pic) {
                if (pic === '')
                    return;
                this.certPicture = true;
                this.currentPic = pic;

            }
        }
    }
</script>

<style scoped>
    .data-table {
        margin: 10px;
    }

    .btn {
        width: 183px;
    }

    .cert-background {
        background: white;
    }

</style>