/**
 * @desc: constant fields for convinience.
 */
package com.liubida.sohu.android.wuhan;

import com.liubida.sohu.android.config.Configuration;
import com.liubida.sohu.android.config.ConfigurationFactory;

/**
 * @author Leon
 */
public class HttpConstant {

    private static final Configuration config = ConfigurationFactory.getConfiguration();

    public static String DOMAIN = config.getDomain();

    public static int HTTP_TIMEOUT = 5000;		// 30 seconds to timeout, adjust whenever.
    public static String FORMAT = "xml";
    public static String APIVERSION = "/api/2";

    public static String ARTICLE_LIST = "/bookmarks/list/";
    public static String ARTICLE_COUNT = "/bookmarks/count/";
    public static String ARTICLE_UPDATE_PRO = "/bookmarks/update-read-progress/";
    public static String ARTICLE_CREATE = "/bookmarks/add/";
    public static String ARTICLE_DELETE = "/bookmarks/delete";
    public static String ARTICLE_GETTEXT = "/bookmarks/get-text";
    public static String ARTICLE_GETRES = "/bookmarks/get-resource";
    public static String ARTICLE_READ = "/bookmarks/view";
    public static String ARTICLE_MOVE = "/bookmarks/move";
    public static String ARTICLE_UPDATE = "/bookmarks/update";

    public static String ARTICLE_STAR = "/bookmarks/star";
    public static String ARTICLE_UNSTAR = "/bookmarks/unstar";
    public static String ARTICLE_ARC = "/bookmarks/archive";
    public static String ARTICLE_UNARC = "/bookmarks/unarchive";

    public static String ARTICLE_MULTI_STATUS = "/bookmarks/get-multi-status";

    public static String ARTICLE_SYNC = "/sync/pull";

    public static String FOLDER_LIST = "/folders/list";
    public static String FOLDER_ADD = "/folders/add";
    public static String FOLDER_UPDATE = "/folders/update";
    public static String FOLDER_DELETE = "/folders/delete";
    public static String FOLDER_ORDER = "/folders/set-order";

    public static String IMG_GETRAW = "/resources/image/get-raw";

    public static String FEED_BACK = "/feedback";

    public static String ACC_TOKEN = "/account/access-token";
    public static String ACC_VERIFY = "/account/verify-credentials";

    public static String CHK_VERSION = "/client/check-version";
    public static String UPLOAD_DUMP = "/client/upload-dump";
    public static String HOLIDAY_SPLASH = "/client/get-holiday-splash";
    public static String MOVE_ARTICLE_MULT = "/api/2/bookmarks/move-multi/";
    public static String DELETE_ARTICLE_MULT = "/api/2/bookmarks/delete-multi/";
    public static String PUSH_CLIENT = "/sync/stream/";

    public static final String GET = "GET";
    public static final String POST = "POST";

    public static final int DEFAULT_LIMIT = 20;

    public static final String UTF_8 = "utf-8";

}
