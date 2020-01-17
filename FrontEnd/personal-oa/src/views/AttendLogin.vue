<template>
    <v-container fill-height fluid grid-list-xl>
        <v-layout justify-center wrap>
            <v-flex xs12 md11>
                <material-card color="green" :title="name">

                    <v-layout align-center justify-space-between row fill-height>
                    <!--<div class="button-group">-->
                        <!--<div class="button-left">-->
                            <v-btn color="green" dark>
                                <v-icon dark left>arrow_back</v-icon>
                                先月(1月)
                            </v-btn>
                        <!--</div>-->
                        <!--<div class="button-middle">-->
                            <v-btn color="blue darken-2" dark @click="monthPick=true">
                                <v-icon dark left right>calendar_today</v-icon>
                                月を選ぶ - 今月({{currentMonth}})
                            </v-btn>

                        <!--</div>-->
                        <!--<div class="button-right">-->
                            <v-btn color="orange darken-2" dark>
                                来月({{currentMonth}})
                                <v-icon dark right>arrow_forward</v-icon>
                            </v-btn>
                    </v-layout>
                        <!--</div>-->
                    <!--</div>-->
                    <v-form>
                        <v-container py-0>
                            <v-data-table
                                    :headers="headers"
                                    :items="records"
                                    disable-initial-sort
                                    class="elevation-1 table-header table-content"
                                    hide-actions>
                                <template v-slot:headers="props">
                                    <tr>
                                        <th colspan="2">日付</th>
                                        <th>超過勤務事由</th>
                                        <th colspan="4">就 業 時 間</th>
                                        <th colspan="3">超過勤務時間集計欄</th>
                                        <th rowspan="2">摘要</th>
                                    </tr>
                                    <tr>
                                        <th>日</th>
                                        <th>曜</th>
                                        <th>休暇・遅刻の理由</th>
                                        <th>始業時間</th>
                                        <th>始業時間</th>
                                        <th>休憩時間</th>
                                        <th>実稼動時間</th>
                                        <th>残業時間</th>
                                        <th>休日時間</th>
                                        <th>深夜時間</th>
                                    </tr>
                                </template>
                                <template v-slot:items="props">
                                    <tr>
                                        <td class="text-xs-center date-td">{{ props.item.day }}</td>
                                        <td class="text-xs-center date-td">{{ props.item.yobi }}</td>
                                        <td class="text-xs-center">
                                            <v-text-field single-line style="font-size: 14px;"
                                            :value="props.item.reason">
                                            </v-text-field>
                                            <!--<input :value="props.item.reason"/>-->
                                        </td>
                                        <td class="time-select-td">
                                            <!--{{ props.item.startTime }}-->
                                            <!--<v-btn flat icon color="primary" @click="popupTime(props.index, 1)">-->
                                                <!--<v-icon>edit</v-icon>-->
                                            <!--</v-btn>-->
                                            <v-text-field
                                                    v-model="props.item.startTime"
                                                    prepend-icon="access_time"
                                                    readonly
                                                    @click="popupTime(props.index, 1)"
                                            ></v-text-field>
                                        </td>
                                        <td class="time-select-td">
                                            <!--{{ props.item.endTime }}-->
                                            <!--<v-btn flat icon color="primary" @click="popupTime(props.index, 2)">-->
                                                <!--<v-icon>edit</v-icon>-->
                                            <!--</v-btn>-->

                                            <v-text-field
                                                    v-model="props.item.endTime"
                                                    prepend-icon="access_time"
                                                    readonly
                                                    @click="popupTime(props.index, 2)"
                                            ></v-text-field>
                                        </td>
                                        <td class="text-xs-center time-td">{{ props.item.restTime }}</td>
                                        <td class="text-xs-center time-td">{{ props.item.workTime }}</td>
                                        <td class="text-xs-center time-td">{{ props.item.overTime }}</td>
                                        <td class="text-xs-center time-td">{{ props.item.holidayTime }}</td>
                                        <td class="text-xs-center time-td">{{ props.item.nightTime }}</td>
                                        <td class="text-xs-center summary-td">
                                            <v-text-field single-line style="font-size: 13px;"
                                                          :value="props.item.summary">
                                            </v-text-field>
                                        </td>
                                    </tr>
                                </template>
                            </v-data-table>
                        </v-container>
                    </v-form>
                </material-card>
            </v-flex>

            <v-dialog v-model="dialog" persistent max-width="290px">
                <v-card>
                    <v-time-picker v-model="time" format="24hr"></v-time-picker>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" flat @click="closeTime()">Close</v-btn>
                        <v-btn color="blue darken-1" flat @click="saveTime()">Save</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>

            <v-menu
                    v-model="monthPick"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    lazy
                    offset-y
                    right
                    full-width

            >
                <!--<template v-slot:activator="{ on }">-->
                    <!--&lt;!&ndash;<v-text-field&ndash;&gt;-->
                    <!--&lt;!&ndash;v-model="currentMonth"&ndash;&gt;-->
                    <!--&lt;!&ndash;label="Picker without buttons"&ndash;&gt;-->
                    <!--&lt;!&ndash;prepend-icon="event"&ndash;&gt;-->
                    <!--&lt;!&ndash;readonly&ndash;&gt;-->
                    <!--&lt;!&ndash;v-on="on"&ndash;&gt;-->
                    <!--&lt;!&ndash;&gt;</v-text-field>&ndash;&gt;-->

                    <!--<v-btn color="blue darken-2" dark v-on="on">-->
                        <!--今月{{currentMonth}}-->
                        <!--<v-icon dark left right>calendar_today</v-icon>-->
                        <!--月を選ぶ-->
                    <!--</v-btn>-->
                <!--</template>-->
                <!--<v-date-picker v-model="currentMonth" @input="monthPick = false"></v-date-picker>-->
                <v-date-picker
                        v-model="currentMonth"
                        type="month" color="info"
                        year-icon="mdi-calendar-blank"
                        prev-icon="mdi-skip-previous"
                        next-icon="mdi-skip-next"
                        @input="monthPick = false"
                ></v-date-picker>
            </v-menu>

        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data() {
            return {
                name: '',
                dialog: false,
                time: '9:00',
                currentRowNum: '',
                currentType: 1,
                noonRestTime: "12:00",
                noonRestHour: "1:00",
                stdEvenEndTime: "22:00",
                monthPick: false,
                currentMonth: '',

                headers: [],
                records: [
                    {
                        day: "1", yobi: "金", reason: "病院へ行く", startTime: "9:00", endTime: "23:00",
                        restTime: "1:30", workTime: "12:30", overTime: "0:30", holidayTime: "", nightTime: "00:00",
                        summary: "摘要"
                    },
                    {
                        day: "2", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "3", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "4", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "5", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "6", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "7", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "8", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "9", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "10", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "11", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "12", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "13", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "14", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "15", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "16", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "17", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "18", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "19", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "20", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "21", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "22", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "23", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "24", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "25", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "26", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "27", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "28", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "29", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "30", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: ""
                    },
                    {
                        day: "31", yobi: "土", reason: "", startTime: "", endTime: "",
                        restTime: "", workTime: "", overTime: "", holidayTime: "", nightTime: "", summary: "摘要"
                    }
                ],

            }
        },
        metaInfo: {
            title: '勤怠時間登録'
        },
        methods: {
            popupTime(rowNum, type) {
                this.dialog = true;
                this.currentRowNum = rowNum;
                this.currentType = type;
                if (type === 1) {
                    this.time = this.records[rowNum].startTime;
                } else if (type === 2) {
                    this.time = this.records[rowNum].endTime;
                }
            },
            saveTime() {
                if (this.currentType === 1) {
                    this.records[this.currentRowNum].startTime = this.time;
                } else if (this.currentType === 2) {
                    this.records[this.currentRowNum].endTime = this.time;
                }
                let startTime = this.records[this.currentRowNum].startTime;
                let endTime = this.records[this.currentRowNum].endTime;
                if(startTime !== '' && endTime !== '') {
                    console.log(startTime+ ":" +endTime)
                    let begintime_min = this.ChangeStrToMinutes(startTime);
                    let endtime_min = this.ChangeStrToMinutes(endTime);
                    let date = endtime_min - begintime_min;
                    console.log(begintime_min+ ":" +endtime_min)
                    if(date > 0) {
                        if(begintime_min<= 720) {
                            this.records[this.currentRowNum].restTime = '1:00';
                            this.records[this.currentRowNum].workTime = '9:00';
                            this.records[this.currentRowNum].overTime = '2:00';
                            this.records[this.currentRowNum].nightTime = '1:00';
                        }
                    }


                }

                this.dialog = false;
            },
            closeTime() {

                this.dialog = false;
            },
            ChangeStrToMinutes(str) {
                let arrminutes = str.split(":");
                if (arrminutes.length === 2) {
                    return parseInt(arrminutes[0]) * 60 + parseInt(arrminutes[1]);
                }
                else {
                    return 0;
                }
            },
            ChangeHoursToStr(str) {
                if (str !== "0" && str !== "" && str !== null) {
                    return ((Math.floor(str / 60)).toString().length < 2 ? "0" + (Math.floor(str / 60)).toString() :
                        (Math.floor(str / 60)).toString()) + ":" + ((str % 60).toString().length < 2 ? "0" + (str % 60).toString() : (str % 60).toString());
                }
                else
                {
                    return "";
                }
            }
        },
        created() {
            this.name = "氏名：" + localStorage.getItem('ms_username');
        }
    }
</script>
<style scoped>
    .time-td {
        width:70px;
    }
    .date-td {
        width:30px;
    }
    .time-select-td {
        width:100px;
    }
    .summary-td {
        width:120px;
    }
</style>

<style lang="scss" scoped>
    .table-header /deep/ {
        thead tr th {
            border: 1px solid rgb(209, 209, 209);
        }
    }
    .table-content /deep/ {
        tr td {
            border: 1px solid rgb(209, 209, 209);
            padding:0 8px 0 8px!important;
        }
    }
</style>