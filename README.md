## 【android进阶篇】MVP+Retrofit+RxJava框架结合的demo
###一、前言
MVP模式是当前比较主流的框架，主要是由它的优点来决定的吧。本文结合了MVP+Retrofit+RxJava三大主流框架(MVP应该叫模式吧)写了一个demo【里面从简单的“**登录**”例子，之后到“**IP地址查询**”，再到相对贴近实际项目的“**获取豆瓣TOP250的电影**”】，一开始可能会觉得不容易理解,但是真正理解了之后，开发、维护就不是那么痛苦的事情了。
###二、Demo介绍
####2.1、需要具备哪些知识
读这篇文章之前，你需要了解的知识有：
>1、**MVP**【[点这里看百度百科介绍](http://baike.baidu.com/link?url=3P55zapQRZuEqEKwQP3SC5tD5GoGmfSSi-_ufHiY8vfWwD13y6JCP6NYqZYgBOp9sZNO7BtNsric9kwYqiG2ua)】
2、**Retrofit 2.0**
3、**RxJava**([点击这里看---不错的一个Retrofit+RxJava结合的例子](http://blog.csdn.net/u012184539/article/details/51043236))
4、**Butterknife的使用**
5、**状态栏一体化**([android状态栏一体化](http://blog.csdn.net/qq137722697/article/details/52139473))
6、**CallBack回调思想**(说白了就是:**成功还是失败了,你总的告诉我一声,我好通知V层来处理**[这里可以不用回调,在代码中使用EventBus或者传递一个Handler过来也可以,不建议这样操作])
####2.2、Demo中使用到的工具类、技术

 - **MVP** 模式
 - **Retrofit **  网络请求
 - **RxJava**  异步
 - **Klog** 花样日志
 - **CallBack** 回调思想 丢弃Handler
 - **RecycleView** 高效地显示大量数据--->未来替代listview  gridview的控件
 - **CommonAdapter**  鸿洋大神的RecycleView通用适配器 
 - **Loadmore**  加载更多   也是借助鸿洋大神的RecycleView通用适配器(添加FootHeader)实现的 
 - **Glide** 高效的图片加载库
 - **android-Ultra-Pull-To-Refresh**  下拉刷新
 -  **ClearEditText**  自定义"带清除所有内容"的输入框控件
 - **状态栏一体化**(设置状态栏的颜色与app主题色一致  没有过渡感)
####2.3、优势
1、MVP  高度解耦  提高代码质量
2、Retrofit+RxJava最强的网络访问组合
3、功能易扩展 易维护  易测试

####2.4、数据源
>豆瓣TOP250的电影  https://api.douban.com/v2/movie/top250?start=0&count=10
查询IP地址 http://ip.taobao.com/service/getIpInfo.php?ip=1.1.1.1
####2.5、MVP浅析
1）、MVP的优点：

>**1、**模型与视图完全分离，我们可以修改视图而不影响模型
**2、**可以更高效地使用模型，因为所有的交互都发生在一个地方——Presenter内部
**3、**我们可以将一个Presenter用于多个视图，而不需要改变Presenter的逻辑。这个特性非常的有用，因为视图的变化总是比模型的变化频繁。
**4、**如果我们把逻辑放在Presenter中，那么我们就可以脱离用户接口来测试这些逻辑（单元测试）【以上优点参考了百度百科的“[MVP模式](http://baike.baidu.com/link?url=3P55zapQRZuEqEKwQP3SC5tD5GoGmfSSi-_ufHiY8vfWwD13y6JCP6NYqZYgBOp9sZNO7BtNsric9kwYqiG2ua)“】

2）、以登录为例的MVP自理解图
![这里写图片描述](http://img.blog.csdn.net/20160815164604370)
####2.6、当下最快的网络访问(Retrofit +RxJava)
>用熟悉之后你会发现,网络访问就是这么简单,这么快.优点我就不一一说了,网上太多的介绍.

###三、效果图与分包说明
####3.1、效果图
不多说，先来看效果图
![这里写图片描述](http://img.blog.csdn.net/20160815164859178)
####3.2、分包说明
分包有很多种分法，我这里用了我常用的分包方法，仅供参考。
![这里写图片描述](http://img.blog.csdn.net/20160815165052117)
**说明**：adapter-->适配器的类，base-->基础的配置类，bean-->实体对象，exception-->异常处理对象，http-->网络相关，ui-->所有的页面（包括fragment【/ui/fragment】），utils-->工具类，widget-->自定义控件类。
####3.3、其他说明
>**Demo阅读指南 **:先看登录的Demo(入门),理解之后再看IP地址查询(深入),最后看获取豆瓣TOP250的电影(浅出)
**使用第三方库说明** :为了快速写出这个demo,在一些方便走了捷径(有时间的可以自己用原生的写法),比如RecycleView的适配器,我就用了鸿洋大神的通用适配器(CommonAdapter),还有使用Butterknife来代替findviewbyid,但是看过郭霖大神写的性能优化一篇博客,说是实际开发中最好慎重使用依赖注入,会降低app的性能和效率,我在这里为了方便就直接使用了.

###四、登录demo
####4.1、布局
>布局简单就不贴了
####4.2、接口类
为什么要使用接口，之前已经说过了，这里之所以写在一个类中，也是为了便于管理，不至于项目看上去有太多的类。
```
/**
 * 登录的关联类
 * Created by HDL on 2016/7/22.
 */
public class LoginContract {
    /**
     * V视图层
     */
    interface ILoginView {
        Context getCurContext();//获取上下文对象,用于保存数据等
        void showProgress();//可以显示进度条
        void hideProgress();//可以隐藏进度条
        void showInfo(String info);//提示用户,提升友好交互
        void showErrorMsg(String msg);//发生错误就显示错误信息
        void toMain();//跳转到主页面
        void toRegister();//跳转到注册页面
        UserInfo getUserLoginInfo();//获取用户登录信息
    }
    /**
     * P视图与逻辑处理的连接层
     */
    interface ILoginPresenter {
        void login();//桥梁就是登录了
    }
    /**
     * 逻辑处理层
     */
    interface ILoginModel {
        void login(UserInfo userInfo, OnHttpCallBack<TokenResult> callBack);//登录
        void saveUserInfo(Context context, UserInfo user,String token);//登录成功就保存用户信息
    }
```
####4.3、View层（LoginActivity）
展示数据,处理用户交互
```
/**
 * 登录页面
 * (看例子之前看一遍下面直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 */
public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {
    //省略ButterKnife绑定页面控件的代码
    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        tvTitle.setText("用户登录");
        mLoginPresenter = new LoginPresenter(this);
    }
    @Override
    public Context getCurContext() {
        return mActivity;
    }
    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }
    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(mActivity, info);
    }
    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.showToast(mActivity, msg);
    }
    @Override
    public void toMain() {
        startActivity(new Intent(mActivity, QueryIPActivity.class));
    }
    @Override
    public void toRegister() {
        startActivity(new Intent(mActivity, RegisterActivity.class));
    }
    @Override
    public UserInfo getUserLoginInfo() {
        return new UserInfo(etLoginUserName.getText().toString().trim(), etLoginPwd.getText().toString().trim());
    }
    public void onRegister(View view) {
        toRegister();
    }
    @OnClick({R.id.iv_title_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.btn_login:
                mLoginPresenter.login();
                break;
        }
    }
}
```
这样看来,View层是不是就少写了很多代码了!
####4.4、Presenter层
获取V层数据,交给M层处理,将处理结果通知给V层
```
/**
 * 登录的桥梁(View和Model的桥梁)-------P通过将V拿到的数据交给M来处理  P又将处理的结果回显到V
 * 说白点就是P中new出M和V 然后通过调用它们两个的方法来完成操作
 * <p/>
 * (看例子之前看一遍下面的直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * Created by HDL on 2016/7/22.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginView mLoginView;
    private LoginContract.ILoginModel mLoginModel;
    public LoginPresenter(LoginContract.ILoginView mLoginView) {
        this.mLoginView = mLoginView;
        mLoginModel = new LoginModel();
    }
    /**
     * 登录
     */
    @Override
    public void login() {
        mLoginView.showProgress(); //显示登录进度条
        final int i = 0;
        mLoginModel.login(mLoginView.getUserLoginInfo(), new OnHttpCallBack<TokenResult>() {
            @Override
            public void onSuccessful(TokenResult tokenResult) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.toMain();//跳转到主页面
                mLoginModel.saveUserInfo(mLoginView.getCurContext(), mLoginView.getUserLoginInfo(), tokenResult.getToken());//保存用户数据
                mLoginView.showInfo("登录成功,您的用户toekn为:" + tokenResult.getToken());//提示用户登录成功
            }
            @Override
            public void onFaild(String errorMsg) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.showErrorMsg(errorMsg);//登录失败  显示错误信息
            }
        });
    }
}
```
####4.5、Model层
具体的业务处理
```
/**
 * 登录的业务处理类
 * Created by  on 2016/7/22.
 */
public class LoginModel implements LoginContract.ILoginModel {
    /**
     * 登录的具体业务处理--------网络请求都在这里做喽
     * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
     * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
     * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
     *
     * @param userInfo P层传递过来的数据
     * @param callBack P层传递过来的回调对象----------说白了就是成功还是失败了,你总的告诉我一声,我好通知V层来处理[这里可以不用回调,在代码中使用EventBus或者传递一个Handler过来也可以,不建议这样操作]
     */
    @Override
    public void login(final UserInfo userInfo, final OnHttpCallBack<TokenResult> callBack) {
        //登录的网络请求
        RetrofitUtils.newInstence(GlobalField.BASEURL)//实例化Retrofit对象
                .create(APIService.class)//创建Rxjava---->LoginService对象
                .userLogin(userInfo.getUserName(), userInfo.getPwd())//调用登录的接口
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Subscriber<UserHttpResult<TokenResult>>() {//网络(登录)请求回调
                    @Override
                    public void onCompleted() {
                        //完成的时候调用
                    }
                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e.getMessage() + "--");
                        e.printStackTrace();
                        //失败的时候调用-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e("Myloy", e.getMessage());
                        }
                    }
                    @Override
                    public void onNext(UserHttpResult<TokenResult> loginResultUserHttpResult) {
                        if (loginResultUserHttpResult.getResultCode() == 0) {
                            callBack.onSuccessful(loginResultUserHttpResult.getData());//登录成功------获取完数据,返回给P---P获取到数据之后就将数据交回给V
                        } else {
                            callBack.onFaild("用户名或密码错误!");//登录失败
                        }
                    }
                });
    }
    /**
     * 将用户信息保存在sp中
     *
     * @param context
     * @param user
     */
    @Override
    public void saveUserInfo(Context context, UserInfo user, String token) {
        KLog.e("开始保存用户信息" + user.toString());
        context.getSharedPreferences(GlobalField.USERINFO_SP_NAME, Context.MODE_PRIVATE).edit()
                .putString("userName", user.getUserName())
                .putString("pwd", user.getPwd())
                .putString("address", user.getAddress())
                .putString("phone", user.getPhone())
                .putString("token", token)
                .commit();
    }
}
```
####4.6、Retofit网络请求工具
>Retrofit使用注意事项，baseurl要以“/”结束 在Service中Get("---")/Post("----")不能以“/”开始

```
/**
 * Retofit网络请求工具类
 * Created by HDL on 2016/7/29.
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒
    private static Retrofit mRetrofit;
    private RetrofitUtils() {
    }
    public static Retrofit newInstence(String url) {
        mRetrofit = null;
        OkHttpClient client = new OkHttpClient();//初始化一个client,不然retrofit会自己默认添加一个
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.MINUTES);//设置读取时间为一分钟
        client.setConnectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS);//设置连接时间为12s
        mRetrofit = new Retrofit.Builder()
                .client(client)//添加一个client,不然retrofit会自己默认添加一个
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
}
```
####4.7、APIService公共请求接口类
```
/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 * Created by HDL on 2016/8/3.
 */
public interface APIService {
    /**
     * 用户登录的接口
     *
     * @param username 用户名
     * @param pwd      密码
     * @return RxJava 对象
     */
    @POST("okhttp/UserInfoServlert")
    Observable<UserHttpResult<TokenResult>> userLogin(@Query("username") String username, @Query("pwd") String pwd);
    /**
     * 查询ip地址信息的接口
     *
     * @param ip 需查询的ip
     * @return RxJava 对象
     */
    @GET("service/getIpInfo.php")
    Observable<IPHttpResult<IpInfo>> queryIp(@Query("ip") String ip);
    /**
     * 查询豆瓣排名前250的电影
     *
     * @param start 从第几部开始
     * @param count 几页(一页有12部)
     * @return
     */
    @GET("v2/movie/top250")
    Observable<Movies> getMovies(@Query("start") int start, @Query("count") int count);
}
```
###五、IP查询
>为了减少文章的篇幅这里就不贴查询IP的了:如果你对这个demo感兴趣,请下载demo去自己看,代码里面的注释自认为挺清楚的.(Demo下载地址在最后)
###六、获取豆瓣TOP250的电影
####6.1、效果图
![这里写图片描述](http://img.blog.csdn.net/20160815171101066)
####6.2、布局
总布局：
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:fitsSystemWindows="true"
    tools:context=".ui.movie.MovieActivity">
    <include layout="@layout/custom_actionbar" />
    <in.srain.cube.views.ptr.PtrFrameLayout xmlns:cube_ptr="http://schemas.android.com/apk/res-auto"
        android:id="@+id/store_house_ptr_frame"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        cube_ptr:ptr_duration_to_close="300"
        cube_ptr:ptr_duration_to_close_header="2000"
        cube_ptr:ptr_keep_header_when_refresh="true"
        cube_ptr:ptr_pull_to_fresh="false"
        cube_ptr:ptr_ratio_of_header_height_to_refresh="1.2"
        cube_ptr:ptr_resistance="1.7">
        <android.support.v7.widget.RecyclerView
            android:id="@+id/rv_movie_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
    </in.srain.cube.views.ptr.PtrFrameLayout>
</LinearLayout>
```
>item布局比较简单就不贴了，需要的可到demo中查看

####6.3、接口类
```
/**
 * 获取豆瓣top250的关联类---------这里主要是抽出MVP中三层的接口,好处就是改需求很方便,使得代码结构更加清晰
 * Created by HDL on 2016/7/22.
 */
public class MovieContract {
    /**
     * V视图层
     */
    interface IMovieView {
        void showBottom(int lastIndex);
        Context getCurContext();//获取上下文对象
        void showProgress();//显示进度条
        void hideProgress();//隐藏进度条
        void showData(List<Movies.SubjectsBean> movies);//显示数据到View上
        void showInfo(String info);//提示用户,提升友好交互
    }
    /**
     * P视图与逻辑处理的连接层
     */
    interface IMoviePresenter {
        void getMovie();//获取数据
        void loadMoreMovie();//加载更多
    }
    /**
     * M逻辑(业务)处理层
     */
    interface IMovieModel {
        void getMovie(int start, int count, OnHttpCallBack<Movies> callBack);//获取信息
    }
}
```
####6.4、View层
```
/**
 * 豆瓣排名前250名
 */
public class MovieActivity extends BaseActivity implements MovieContract.IMovieView {
    MoviePresenter mMoviePresenter;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    private ProgressDialog mProgressDialog;
    @InjectView(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @InjectView(R.id.store_house_ptr_frame)
    PtrFrameLayout storeHousePtrFrame;
    private TextView load_more;//加载更多的按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mMoviePresenter = new MoviePresenter(this);
        mMoviePresenter.getMovie();//启动软件时默认加载
    }
    /**
     * 初始化布局
     */
    private void initView() {
        setContentView(R.layout.activity_movie);
        ButterKnife.inject(this);
        tvTitle.setText("豆瓣Top250的电影");
        initPtr();
        rvMovieList.setLayoutManager(new LinearLayoutManager(mActivity));//设置为listview的布局
        rvMovieList.setItemAnimator(new DefaultItemAnimator());//设置动画
        rvMovieList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
    }
    /**
     * 初始化(配置)下拉刷新组件
     */
    private void initPtr() {
        //下面是一些基础的配置,直接拿来用就可以 不用深究
        storeHousePtrFrame.setResistance(1.7f);
        storeHousePtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHousePtrFrame.setDurationToClose(200);
        storeHousePtrFrame.setDurationToCloseHeader(1000);
        storeHousePtrFrame.setPullToRefresh(false);
        storeHousePtrFrame.setKeepHeaderWhenRefresh(true);
        StoreHouseHeader header = new StoreHouseHeader(this);
        float scale = getResources().getDisplayMetrics().density;
        header.setPadding(0, (int) (15 * scale + 0.5f), 0, (int) (15 * scale + 0.5f));
        header.initWithString("HDL");//自定义头显示的字样,设置图片的话看另外的api
        header.setTextColor(Color.RED);
        header.setBackgroundColor(Color.parseColor("#11000000"));
        storeHousePtrFrame.setHeaderView(header);//添加头
        storeHousePtrFrame.addPtrUIHandler(header);//同时也要加上这一句
        storeHousePtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mMoviePresenter.loadMoreMovie();//下拉刷新的时候加载更多数据
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHousePtrFrame.refreshComplete();
                    }
                }, 150);//为了增加用户体验 延迟0.15s再通知刷新结束
            }
        });
    }
    @Override
    public void showBottom(int lastIndex) {
        load_more.setText("点击加载更多");//设置最底下的加载更多显示的内容    加载中-->点击加载更多
        rvMovieList.scrollToPosition(lastIndex);
    }
    @Override
    public Context getCurContext() {
        return this;
    }
    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this, "提示", "正在获取中,请稍后...");
    }
    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }
    /**
     * 显示数据
     *
     * @param movies
     */
    @Override
    public void showData(List<Movies.SubjectsBean> movies) {
        //鸿洋大神的通用适配器(真的很好用哦)
        CommonAdapter<Movies.SubjectsBean> commonAdapter = new CommonAdapter<Movies.SubjectsBean>(this, R.layout.movie_item, movies) {
            @Override
            protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
                String title = (position + 1) + "、" + subjectsBean.getTitle() + "/" + subjectsBean.getOriginal_title();
                holder.setText(R.id.tv_movie_title, title);//设置电影名
                String doc = "";
                for (Movies.SubjectsBean.DirectorsBean directorsBean : subjectsBean.getDirectors()) {
                    doc += directorsBean.getName() + "  ";
                }
                holder.setText(R.id.tv_movie_doc, "导演:" + doc);
                String casts = "";
                for (Movies.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
                    casts += castsBean.getName() + "  ";
                }
                holder.setText(R.id.tv_movie_art, "主演:" + casts);
                String genres = "";
                for (String genre : subjectsBean.getGenres()) {
                    genres += genre + " ";
                }
                holder.setText(R.id.tv_movie_type, subjectsBean.getYear() + " / " + genres);//年份+分级
                holder.setText(R.id.tv_movie_grade, subjectsBean.getRating().getAverage() + "");//评分
                ImageView iv_pic = holder.getView(R.id.iv_movie_pic);
                Glide.with(mActivity)
                        .load(subjectsBean.getImages().getSmall())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//加快显示速度---缓存在本地磁盘
                        .into(iv_pic);//图片
            }
        };
        /**
         * 配置加载更多(通用适配器里面的类哦)
         */
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(commonAdapter);//加载更多的包装器(传入通用适配器)
        View view = View.inflate(mActivity, R.layout.load_more, null);
        //要设置一下的布局参数,因为布局填充到包装器的时候,自己的一些属性会无效
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(mLayoutParams);
        load_more = (TextView) view.findViewById(R.id.tv_load_more);
        //监听点击加载更多事件
        load_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_more.setText("加载中...");//点击加载更多-->加载中
                mMoviePresenter.loadMoreMovie();
            }
        });
        mLoadMoreWrapper.setLoadMoreView(view);
        rvMovieList.setAdapter(mLoadMoreWrapper);//注意  这里添加的是包装器 不是适配器哦
    }
    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(this, info);
    }
    @OnClick(R.id.iv_title_back)
    public void onClick() {
        finish();
    }
}
```
####6.5、Presenter层
```
/**
 * P层
 * Created by HDL on 2016/8/3.
 */
public class MoviePresenter implements MovieContract.IMoviePresenter {
    MovieContract.IMovieModel mIMovieModel;//M层
    MovieContract.IMovieView mIMovieView;//V层
    public  int start = 0;//从第几个开始
    public  int count = 4;//请求多少个
    List<Movies.SubjectsBean> mMovies = new ArrayList<>();//请求到的电影信息对象集合
    public MoviePresenter(MovieContract.IMovieView mIMovieView) {
        this.mIMovieView = mIMovieView;
        mIMovieModel = new MovieModel();
    }
    @Override
    public void getMovie() {
        mIMovieView.showProgress();//通知V层显示对话框
        //每次刷新加载4个
        mIMovieModel.getMovie(start, count, new OnHttpCallBack<Movies>() {//有一个请求结果的回调,即我调用请求电影信息的方法了,M层要返回一个成功还是失败的信息给我
            @Override
            public void onSuccessful(Movies movies) {//获取电影信息成功了,返回movies对象
                mIMovieView.hideProgress();//通知V层隐藏对话框
                mMovies.addAll(movies.getSubjects());//追加数据
                mIMovieView.showData(mMovies);//将获取到的信息显示到界面之前
                mIMovieView.showBottom(start - 5);//实现换页效果
            }
            @Override
            public void onFaild(String errorMsg) {
                mIMovieView.hideProgress();//通知V层隐藏对话框
                mIMovieView.showInfo(errorMsg);//通知V层显示错误信息
            }
        });
        start = start + 4;//改变请求的起点
    }
    /**
     * 加载更多
     */
    @Override
    public void loadMoreMovie() {
        getMovie();
    }
}
```

####6.6、Model层
```
/**
 * 具体的逻辑(业务)处理了
 * Created by HDL on 2016/8/3.
 */
public class MovieModel implements MovieContract.IMovieModel {
    @Override
    public void getMovie(int start, int count, final OnHttpCallBack<Movies> callBack) {
        RetrofitUtils.newInstence(GlobalField.MOVIE_TOP250_URL)
                .create(APIService.class)
                .getMovies(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Movies>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //失败的时候回调-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e(e.getMessage());
                        }
                    }
                    @Override
                    public void onNext(Movies movies) {
                        callBack.onSuccessful(movies);//请求成功---回调
                        KLog.e(movies.toString());
                    }
                });
    }
}

```
###七、Demo下载
>有一些简单的代码就没有贴出来,可以下demo去看一下

这是demo的GitHub的地址 https://github.com/huangdali/newkjdemo/ 
如果没有翻墙的话:[点击这里下载Demo](http://download.csdn.net/detail/qq137722697/9603662)

代码有误的地方请多多指出，共同进步，有问题的请留言、评论。
