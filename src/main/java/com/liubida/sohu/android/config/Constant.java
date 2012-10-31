/**
 * @desc: constant fields for convinience.
 */
package com.liubida.sohu.android.config;

/**
 * @author liubida Aug 22, 2012 3:15:18 PM
 */
public class Constant {

    public static final String REWORKING = "重构中";
    public static final String DEFAULTPACKAGE = "默认分类";
    public static final String SPLASH_PATH = "splash/";
    public static final Integer BOOT_UP_WAIT_TIME = 3;
    public static final String ROOT_PATH = "/data/data/com.sohu.suishenkan/files/";

    // login
    public static final String SOHU_MAIL_TAIL = "@sohu.com";
    public static final String FORGET_PSW = "\"<u><font color='#707070'>忘记密码</font></u>\"";
    public static final String FORGET_PSW_URL = "https://passport.sohu.com/web/RecoverPwdInput.action";

    // sync
    // 同步数据超时时间_分钟
    public static final Integer SYNC_TIME_OUT = 300;
    public static final Integer SYNC_LIMIT = 1000;
    public static final Integer INCLUDE_RESOURCE = 1;
    public static final Integer INCLUDE_NO_RESOURCE = 0;

    public static final String INTENAL_ACTION_READLIST = "com.sohu.kan.ReadList";
    public static final String ACTION_EXIT = "EXIT";
    public static final String ACTION_STREAM = "STREAM";
    public static final String ACTION_SYNC_DONE = "SYNC_DONE";
    public static final String ACTION_DOWNLOAD_BOOKMARK_DONE = "DOWNLOAD_BOOKMARK_DONE";
    public static final String ACTION_DOWNLOAD_IMAGE_DONE = "DOWNLOAD_IMAGE_DONE";

    // 设备启动完成
    public static final String ACTION_BOOT_UP = "android.intent.action.BOOT_COMPLETED";

    public static final String INFO_STR_1 = "网络连接失败, 请检查网络";
    public static final String INFO_STR_2 = "账号或者密码有误，请核对后重新输入";
    public static final String INFO_STR_3 = "搜狐passport验证失败";
    public static final String INFO_STR_4 = "服务器未成功取得文章，请稍后再试";
    public static final String INFO_STR_5 = "文章下载失败";
    public static final String INFO_STR_6 = "当前无网络, 本次操作为离线操作";
    public static final String ERROR_STR_1 = "<error><code>1000</code><message>User verification failed</message></error>";

    // MSG_AC 是线程发送给Activity的消息的what
    // 0-999是由Activity的基类负责接收处理
    public static final int MSG_AC = 0;
    public static final int MSG_AC_STREAM_SYNC = 1; // AC1 push_stream发送同步命令
    public static final int BROKEN_ICE = 2; // 我的的收藏动画完毕收起透明层

    // 1000-5000是由Activity的各个实例负责接收处理
    public static final int MSG_AC_BOOT_UP_WAIT = 1000; // start 启动画面结束的消息

    // MSG_BG 是线程发送给BackgroundService的消息的what >= 10000
    public static final int MSG_BG = 10000;
    public static final int MSG_BG_PUSH_STREAM_SYNC = 10001; // BG
                                                             // push_stream收到sync的命令
    public static final int MSG_BG_PUSH_STREAM_INTERRUPT = 10002; // BG
                                                                  // push_stream线程被中断
    public static final int MSG_BG_SYNC_DONE = 10003; // BG 同步操作完成
    // BG 生产者线程中断
    public static final int MSG_BG_DOWNLOAD_BG_PRODUCER_INTERRUPT = 10005;
    // BG 消费者线程中断
    public static final int MSG_BG_DOWNLOAD_BG_CONSUMER_INTERRUPT = 10006;
    // BG 有文章下载完成
    public static final int MSG_BG_DOWNLOAD_BOOKMARK_DONE = 10007;
    // BG 有IMAGE下载完成
    public static final int MSG_BG_DOWNLOAD_IMAGE_DONE = 10008;
    // BG wait-检查任务线程中断
    public static final int MSG_BG_WAIT_INTERRUPT = 10009;

    public static final String PASSPORT_TOKEN_URL = "https://passport.sohu.com/mobile/gettoken";

    public static final String SHARED_WALK_THROUGH = "walkthrough";
    public static final String SHARED_WALK_THROUGH_STATUS = "status";
    public static final int SHARED_WALK_THROUGH_STATUS_DEFAULT_VALUE = 0;
    public static final String UCANOTBEHERE = "u can not be here";
    public static final String ROM_SAVE_ADDRESS = "file:///data/data/com.sohu.suishenkan/files/";
    public static final String OS_TYPE = "android";
    // 下载图片的高
    public static final int DOWNLOAD_IMAGE_HEIGHT = 426;
    // 下载图片的宽
    public static final int DOWNLOAD_IMAGE_WIDTH = 500;
    // 下载图片的默认格式
    public static final String DOWNLOAD_IMAGE_FORMAT = "png";

    public static final int BOOKMARK_IS_NOT_READY = 0;
    public static final int BOOKMARK_IS_READY = 1;
    // <<<<<<< HEAD

    // 阅读列表刷新icon
    public static final String READLIST_REFRESH = "u";
    // 阅读列表更多icon
    public static final String READLIST_MORE = "3";
    // 我的收藏添加分类
    public static final String CATEGORY_ADD = "r";
    // 我的收藏编辑分类
    public static final String CATEGORY_EDIT = "Z";
    // 我的收藏删除分类
    public static final String CATEGORY_DELETE = "l";
    // 我的收藏文件夹
    public static final String CATEGORY = "t";

    // 分享按钮
    public static final String SHARE = "h";
    // 返回按钮
    public static final String Back = "q";

    // 阅读列表返回到主list头
    public static final String BACK_HEARDER = "q";
    // 阅读列表返批量编辑
    public static final String BATCH = "4";
    // 阅读列表排序
    public static final String LIST_SORT = "s";
    // 阅读列表搜索
    public static final String LIST_SEARCH = "m";
    // 阅读列表menu中阅读列表icon
    public static final String MENU_READLIST_ICON = "k";
    // 阅读列表menu中我的收藏icon
    public static final String MENU_COLLECTION_ICON = "v";
    // 阅读列表menu中应用设置icon
    public static final String MENU_SETTING_ICON = "j";
    // 阅读列表menu中收藏方法icon
    public static final String MENU_ADD_ICON = "e";
    // delete
    public static final String DELETE = "l";
    // 切换到原网页
    public static final String ORIGINAL = "a";
    // 切换到精简网页
    public static final String STREAMLINE = "b";
    // setting
    public static final String SETTING = "5";
    // 字体减小
    public static final String MINUS = "6";
    // 字体增大
    public static final String PLUS = "7";
    // 白天模式
    public static final String DAY = "2";
    // 夜间模式
    public static final String DARK = "0";
    // 呼延模式
    public static final String EYE = "1";
    // 降亮度
    public static final String LIGHT_DOWN = "w";
    // 升亮度
    public static final String LIGHT_UP = "x";
    // share

    // 阅读列表编辑模式
    public static final String BATCH_MODE = "编辑模式";

    // 黄金分割
    public static final float GOLDEN_SECTION = 0.68f;

    public static final float THUMB_WIDTH_RATE = 0.25f;

    // 剪切方式
    public static final float VERTICAL_CUT = 0;
    public static final float HORIZONTAL_CUT = 1;
    public static final String IMAGE_RULER = "resource-image-\\w{4}-\\d+-\\d+-\\w{40}";

    public static final int MIN_IMAGE_WIDTH = 240;
    public static final int MIN_IMAGE_HEIGHT = 200;

    public static final String MOVE_MULT_OFFLINE = "1000101";// 离线移动文章成功
    public static final String DEL_ARTICLE_MULT_OFFLINE = "1000102";// 离线删除文章
    public static final Integer FEEDBACKNUM = 100;// feedback 输入字数
}
