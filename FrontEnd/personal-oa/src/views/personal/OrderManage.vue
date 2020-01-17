<template>
    <v-container fill-height fluid grid-list-xl>

        <v-snackbar v-model="snackbar" color='success' top>
            {{ alertText }}
            <v-icon dark flat @click="snackbar = false">close</v-icon>
        </v-snackbar>
        <v-layout justify-center wrap>
            <v-flex md12>
                <v-toolbar color="primary" class="toolbar-task" dark>
                    <v-toolbar-title>订单查询</v-toolbar-title>
                    <v-divider class="mx-2" inset vertical></v-divider>
                    <v-text-field color="white" v-model="keyword"
                                  label="查询..."
                                  class="search-text"
                                  append-icon="search" @keyup.native.enter="searchWord"
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-spacer></v-spacer>
                    <v-spacer></v-spacer>
                    <v-dialog v-model="dialog"  max-width="1300px" persistent>
                        <template v-slot:activator="{ on }">
                            <v-btn icon v-on="on">
                                <v-icon large right dark>add_box</v-icon>
                            </v-btn>
                        </template>
                        <v-card class="word-card">
                            <v-card-title>
                                <span class="headline">订单明细</span>
                            </v-card-title>
                            <v-card-text>
                                <v-form ref="form" lazy-validation>
                                    <v-container grid-list-xl>
                                        <v-layout wrap>
                                            <v-flex xs12 md4>
                                                <v-text-field v-model="editedItem.name" :counter="40"
                                                              label="名称" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-select :items="deliveryTypes" v-model="editedItem.deliveryType"
                                                        label="快递方式">
                                                </v-select>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.deliveryNum" :counter="16"
                                                              label="快递单号" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.startDay" :counter="10"
                                                              label="发货日" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.endDay" :counter="10"
                                                              label="收货日" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.cost" :counter="10"
                                                              label="成本（日元）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.costRmb" :counter="10"
                                                              label="成本（人民币）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.received" :counter="10"
                                                              label="收款（人民币）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.exchangeRate" :counter="10"
                                                              label="当日利率" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.weight" :counter="10"
                                                              label="重量（g）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md1>
                                                <v-text-field v-model="editedItem.weightCost" :counter="10"
                                                              label="运费（日元）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.profit" :counter="10"
                                                              label="订单利润（人民币）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.profitPoint" :counter="10"
                                                              label="返积分（日元）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md2>
                                                <v-text-field v-model="editedItem.profitTotal" :counter="10"
                                                              label="总利润（人民币）" required>
                                                </v-text-field>
                                            </v-flex>
                                            <v-flex xs12 md12>
                                                <v-toolbar flat color="white">
                                                    <v-toolbar-title></v-toolbar-title>
                                                    <v-spacer></v-spacer>
                                                    <v-spacer></v-spacer>
                                                    <v-spacer></v-spacer>
                                                    <v-dialog v-model="detailsOrder" max-width="1300px" persistent>
                                                        <template v-slot:activator="{ on }">
                                                            <v-btn color="#1867c0" dark class="mb-2" v-on="on">商品追加</v-btn>
                                                        </template>
                                                        <v-card>
                                                            <v-card-title>
                                                                <span class="headline">商品明细</span>
                                                            </v-card-title>
                                                            <v-card-text>
                                                                <v-form ref="form" lazy-validation>
                                                                    <v-container grid-list-xl>
                                                                        <v-layout wrap>
                                                                            <v-flex xs12 md4>
                                                                                <v-text-field v-model="editedItem.name" :counter="40"
                                                                                              label="名称" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-select :items="deliveryTypes" v-model="editedItem.deliveryType"
                                                                                          label="快递方式">
                                                                                </v-select>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.deliveryNum" :counter="16"
                                                                                              label="快递单号" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.startDay" :counter="10"
                                                                                              label="发货日" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.endDay" :counter="10"
                                                                                              label="收货日" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.cost" :counter="10"
                                                                                              label="成本（日元）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.costRmb" :counter="10"
                                                                                              label="成本（人民币）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.received" :counter="10"
                                                                                              label="收款（人民币）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.exchangeRate" :counter="10"
                                                                                              label="当日利率" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.weight" :counter="10"
                                                                                              label="重量（g）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md1>
                                                                                <v-text-field v-model="editedItem.weightCost" :counter="10"
                                                                                              label="运费（日元）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.profit" :counter="10"
                                                                                              label="订单利润（人民币）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.profitPoint" :counter="10"
                                                                                              label="返积分（日元）" required>
                                                                                </v-text-field>
                                                                            </v-flex>
                                                                            <v-flex xs12 md2>
                                                                                <v-text-field v-model="editedItem.profitTotal" :counter="10"
                                                                                              label="总利润（人民币）" required>
                                                                                </v-text-field>
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
                                                <div style="max-height: 360px; overflow-y:auto">
                                                <v-data-table :headers="detailsHeaders" :items="detailsItems" class="elevation-1 table-def" :pagination.sync="pagination">
                                                    <template slot="headerCell" slot-scope="{ header }">
                                                        <span
                                                                class="font-weight-light text-warning text--darken-3"
                                                                v-text="header.text"
                                                        />
                                                    </template>
                                                    <template v-slot:items="props">
                                                        <tr>
                                                            <td>{{ props.index + 1 }}</td>
                                                            <td>{{ props.item.name }}</td>
                                                            <td>{{ props.item.cost }}</td>
                                                            <td>{{ props.item.costRmb }}</td>
                                                            <td>{{ props.item.received }}</td>
                                                            <td>{{ props.item.profit }}</td>
                                                            <td>{{ props.item.profitPoint }}</td>
                                                            <td>{{ props.item.profitTotal }}</td>
                                                            <td>{{ props.item.weight }}</td>
                                                            <td>{{ props.item.buySpot }}</td>
                                                            <td>{{ props.item.deliveryNum }}</td>
                                                        </tr>
                                                    </template>
                                                </v-data-table>
                                                </div>
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
            <v-flex md12>
                <material-card color="light-blue darken-2" title="订单一览"
                               :text="'当前订单数：' + productNum" class="card-tabs card-task">
                    <v-data-table :headers="headers" :items="items" :pagination.sync="pagination">
                        <template
                                slot="headerCell"
                                slot-scope="{ header }"
                        >
              <span
                      class="subheading font-weight-light text-success text--darken-3"
                      v-text="header.text"
              />
                        </template>
                        <template v-slot:items="props">
                            <tr :active="props.selected" @click="showDetails(props.item, props.index)">
                                <td>{{ props.index + 1 }}</td>
                                <td>{{ props.item.name }}</td>
                                <td>{{ props.item.cost }}</td>
                                <td>{{ props.item.received }}</td>
                                <td>{{ props.item.profit }}</td>
                                <td>{{ props.item.weight }}</td>
                                <td>{{ props.item.deliveryType }}</td>
                                <td>{{ props.item.deliveryNum }}</td>
                                <td>{{ props.item.startDay }}</td>
                                <td>{{ props.item.endDay }}</td>
                            </tr>
                        </template>
                    </v-data-table>
                </material-card>
            </v-flex>


        </v-layout>
    </v-container>

</template>

<script>
    export default {
        data: () => ({
            dialog: false,
            snackbar: false,
            detailsOrder: false,
            alertText: '',
            keyword: '',
            headers: [
                {sortable: false, text: 'ID', value: 'id'},
                {sortable: true, text: '订单名', value: 'name'},
                {sortable: true, text: '成本（日元）', value: 'cost'},
                {sortable: true, text: '收款（RMB）', value: 'received'},
                {sortable: true, text: '总利润（RMB）', value: 'profitTotal'},
                {sortable: true, text: '重量', value: 'weight'},
                {sortable: true, text: '快递', value: 'deliveryType'},
                {sortable: true, text: '快递单号', value: 'deliveryNum'},
                {sortable: true, text: '发货日', value: 'startDay'},
                {sortable: true, text: '收货日', value: 'endDay'}
            ],
            items: [
                {id: 1, name: "广州阿姨铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", weightCost: "1,920", costRmb: "3,125",
                    deliveryType:"EMS", deliveryNum:"EG692746530JP", exchangeRate:"6.65", profitPoint: "300", profitTotal: "395",
                    startDay: "2019/10/31", endDay: "2019/11/3"}],

            detailsHeaders: [
                {sortable: false, text: 'ID', value: 'id'},
                {sortable: true, text: '商品名', value: 'name'},
                {sortable: true, text: '成本（日元）', value: 'cost'},
                {sortable: true, text: '成本（RMB）', value: 'costRmb'},
                {sortable: true, text: '卖价（RMB）', value: 'received'},
                {sortable: true, text: '利润（RMB）', value: 'profit'},
                {sortable: true, text: '返点（日元）', value: 'profitPoint'},
                {sortable: true, text: '总利润（RMB）', value: 'profitTotal'},
                {sortable: true, text: '重量', value: 'weight'},
                {sortable: true, text: '购买店', value: 'buySpot'}
            ],
            detailsItems: [
                {id: 1, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                     profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 2, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 3, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 4, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 5, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 6, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 7, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 8, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 9, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"},
                {id: 10, name: "万宝路铅笔", cost: "50,000", received: "3,500", profit: "375", weight: "800g", costRmb: "3,125",
                    profitPoint: "300", profitTotal: "395", buySpot: "友多八喜"}
                     ],
            deliveryTypes:['EMS','航空','SAL', '船运'],

            pagination: {
                sortBy: 'name',
                rowsPerPage: '10'
            },
            editedIndex: -1,
            editedItem: {
                name: '',
                cost: '',
                costRmb: '',
                received: '',
                profit: '',
                profitPoint: '',
                profitTotal: '',
                exchangeRate: '',
                weight: '',
                weightCost: '',
                deliveryType: '',
                startDay: '',
                endDay: ''
            },
            editedDetailItem: {
                name: '',
                imageUrl: '',
                cost: '',
                costRmb: '',
                received: '',
                profit: '',
                profitPoint: '',
                profitTotal: '',
                weight: '',
                weightCost: '',
                spot: '',
                remark: ''
            },
            localUrl: '',
            productNum: ''  // 商品总数
        }),
        created() {
            this.localUrl = global.backServerEndpoint + '/words';
            // this.initialize()
        },
        methods: {
            initialize() {
                const url = this.localUrl + '/words-getAll'
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.items = res.data;
                    this.productNum = this.items.length;
                });

            },
            searchWord() {
                if(this.keyword === '')
                    this.initialize();
                const url = this.localUrl + '/words-get/' + this.keyword
                this.$axios({
                    method: 'get',
                    url: url
                }).then((res) => {
                    this.items = res.data;
                    this.productNum = this.items.length;
                });
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
    .search-text {
        width: 300px
    }

    .btn {
        width: 500px
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