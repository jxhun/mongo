package com.jxhun.mongo.vo;

/**
 * <B>错误信息代码定义</B>
 * <B>Copyright:本软件源代码版权归农丰时代所有,未经许可不得任意复制与传播.</B>
 * <B>农丰时代科技有限公司</B>
 *
 * @author yuehongbo
 * @date 2019/1/29 14:44
 */
public class ResultCode {

    /**200:成功
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer OK = 200;

    /**401:用户信息验证不通过(未登录、解绑、登录失效等) <br/>
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer CSRF_ERROR = 401;

    /**402:用户信息验证不通过(账户被禁用、密码被强制修改等) <br/>
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer USER_AUTH_ERROR = 402;

    /**
     * 500:数据参数异常，业务系统可以直接展示提示信息
     */
    public static final Integer DATA_PARAM_ERROR = 500;


    /**1000:输入参数错误(输入参数类型、值、null等错误) <br/>
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer PARAMS_ERROR = 1000;


    /**2000：权限错误(未登录，数据权限不满足，功能权限不满足等错误)<br/>
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer NOT_AUTH_ERROR = 2000;

    /**3000:业务逻辑或数据错误(未查询到数据，数据验证不通过，数据发生变化等错误)<br/>
     *  <li>前端是否可直接显示后台返回的message:是</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer DATA_ERROR = 3000;

    /**5000:服务器内部错误(系统错误，代码BUG,系统间调用超时等错误)
     *  <li>前端是否可直接显示后台返回的message:是。此消息内部已做过处理</li>
     *  <li>是否有出现错误的数据返回给前端：无</li>
     */
    public static final Integer APP_ERROR = 5000;
}
