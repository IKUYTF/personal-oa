/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
    {
        path: '/mainPage',
        name: 'Main Page',
        view: 'personal/Dashboard'
    },
    {
        path: '/learning',
        name: 'J-Learning',
        view: 'personal/Learning'
    },
    {
        path: '/productManage',
        name: '商品查询',
        view: 'personal/ProductManage'
    },
    {
        path: '/orderManage',
        name: '订单管理',
        view: 'personal/OrderManage'
    },
    {
        path: '/examLearn',
        name: 'Exam-Learning',
        view: 'personal/ExamLearn'
    },
    {
        path: '/progressMemo',
        name: '進捗メモ',
        view: 'personal/ProgressMemo'
    },
    {
        path: '/userManage',
        name: 'ユーザー管理',
        view: 'Wechat/UserManage'
    },
    {
        path: '/shopManage',
        name: '防伪管理系统',
        view: 'Wechat/ShopManage'
    },
    {
        path: '/sevenPay',
        name: '7Pay起動',
        view: 'personal/7Pay'
    },
    {
        path: '/7payGuide',
        name: '7Payガイド',
        view: 'personal/7PayGuide'
    },
    {
        path: '/simpleTools',
        name: '簡易ツール',
        view: 'personal/SimpleTools'
    },
    {
        path: '/wechatApiTest',
        name: '微信API',
        view: 'personal/WechatApiTest'
    },
    {
        path: '/index',
        name: '勤怠管理',
        view: 'mobile/Index'
    },
    {
        path: '/timeHistory',
        name: '出勤履歴',
        view: 'mobile/TimeHistory'
    },
    {
        path: '/timeManage',
        name: '出勤管理',
        view: 'mobile/TimeManage'
    },
    {
        path: '/commute',
        name: '交通費',
        view: 'mobile/Commute'
    },
    {
        path: '/userInfo',
        name: 'ユーザー情報',
        view: 'mobile/UserInfo'
    },
    {
        path: '/attendLogin',
        name: '勤怠登録',
        view: 'AttendLogin'
    },
    {
        path: '/login',
        // Relative to /src/views
        view: 'Login'
    },
    {
        path: '/dashboard',
        // Relative to /src/views
        view: 'Dashboard'
    },
    {
        path: '/user-profile',
        name: 'User Profile',
        view: 'UserProfile'
    },
    {
        path: '/table-list',
        name: 'Table List',
        view: 'TableList'
    },
    {
        path: '/typography',
        view: 'Typography'
    },
    {
        path: '/icons',
        view: 'Icons'
    },
    {
        path: '/maps',
        view: 'Maps'
    },
    {
        path: '/notifications',
        view: 'Notifications'
    },
    {
        path: '/upgrade',
        name: 'Upgrade to PRO',
        view: 'Upgrade'
    }
]
