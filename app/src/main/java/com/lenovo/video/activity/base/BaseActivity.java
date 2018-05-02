package com.lenovo.video.activity.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.lenovo.video.R;
import com.lenovo.video.app.ActivityManager;
import com.lenovo.video.app.MyApplication;
import com.lenovo.video.utils.LogUtils;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.map.geolocation.TencentPoi;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketClientReceivingDelegate;
import com.vilyever.socketclient.helper.SocketClientSendingDelegate;
import com.vilyever.socketclient.helper.SocketPacket;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * 类名称：com.publicnum.commission.base
 * 类描述：
 * 创建人：yd_10
 * 创建时间：2017/5/9 14:45
 * 修改人：
 * 修改时间：2017/5/9 14:45
 * 修改备注：
 */
public abstract class BaseActivity extends MPermissionsActivity implements SocketClientDelegate , SocketClientSendingDelegate
        , SocketClientReceivingDelegate, TencentLocationListener {

    private final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    private Context mContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mContext = this;
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mLocationManager = TencentLocationManager.getInstance(this);
        // 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
        //mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);

        initStatusBar();
        initPresenter();
        initData();
        initTools();
        initView(savedInstanceState);
        //添加activity入栈
        ActivityManager.getScreenManager().pushActivity(this);
    }

    @Subscribe
    public void onEvent(String expand) {

    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }

    /**
     * 初始化layout
     * @return
     */
    protected abstract int getLayoutId();
    /**
     * 初始化沉浸式布局
     */
    protected abstract void initStatusBar();
    /**
     * 初始化view
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 初始化P层
     */
    protected abstract void initPresenter();
    /**
     * 初始化kits
     */
    protected abstract void initTools();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 取消绑定服务
         */
        Unbinder bind = ButterKnife.bind(this);
        bind.unbind();
        /**
         * 停止定位服务
         */
        stopLocation(null);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * 通过Class跳转界面
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivityBroughtToFront(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
//        overridePendingTransition(R.anim.new_push_left_in, R.anim.new_push_left_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//        overridePendingTransition(R.anim.new_push_left_in, R.anim.new_push_left_out);
    }

    /**
     * 含有Bundle通过Class跳转界面
     * @param cls
     * @param bundle
     * @param b_ani  true 动画 false 无动画
     */
    public void startActivity(Class<?> cls, Bundle bundle, boolean b_ani) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (b_ani) {
//            overridePendingTransition(R.anim.new_push_left_in, R.anim.new_push_left_out);
        }
    }

    /**
     * 退出app方法
     * 关闭长连接
     * 关闭服务
     */
    public void isExit() {
        try {
            new AlertDialog.Builder(mContext).setTitle(getResources().getString(R.string.Confirm))
                    .setNeutralButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyApplication.getInstants().exit();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络请求的毁掉
     * @param client
     * @param responsePacket
     */
    protected abstract void getMessage(SocketClient client, @NonNull SocketResponsePacket responsePacket);

    /**
     * 常用回调配置
     */
    /**
     * 连接上远程端时的回调
     */
    @Override
    public void onConnected(SocketClient client) {

    }

    /**
     * 与远程端断开连接时的回调
     */
    @Override
    public void onDisconnected(SocketClient client) {
        // 可在此实现自动重连
        client.connect();
    }

    /**
     * 接收到数据包时的回调
     */
    @Override
    public void onResponse(final SocketClient client, @NonNull SocketResponsePacket responsePacket) {
        getMessage(client, responsePacket);
    }

    /**
     * 发送状态回调配置
     */
    /**
     * 数据包开始发送时的回调
     */
    @Override
    public void onSendPacketBegin(SocketClient client, SocketPacket packet) {
        LogUtils.d("onSend", "SocketServerClient: onSendPacketBegin: " + packet.hashCode() + "   " + Arrays.toString(packet.getData()));
    }

    /**
     * 数据包取消发送时的回调
     * 取消发送回调有以下情况：
     * 1. 手动cancel仍在排队，还未发送过的packet
     * 2. 断开连接时，正在发送的packet和所有在排队的packet都会被取消
     */
    @Override
    public void onSendPacketCancel(SocketClient client, SocketPacket packet) {
        LogUtils.d("onSend", "SocketServerClient: onSendPacketCancel: " + packet.hashCode());
    }

    /**
     * 数据包发送的进度回调
     * progress值为[0.0f, 1.0f]
     * 通常配合分段发送使用
     * 可用于显示文件等大数据的发送进度
     */
    @Override
    public void onSendingPacketInProgress(SocketClient client, SocketPacket packet, float progress, int sendedLength) {
        LogUtils.d("onSend", "SocketServerClient: onSendingPacketInProgress: " + packet.hashCode() + " : " + progress + " : " + sendedLength);
    }


    /**
     * 接收状态回调配置
     */
    /**
     * 数据包完成发送时的回调
     */
    @Override
    public void onSendPacketEnd(SocketClient client, SocketPacket packet) {
        LogUtils.d("onSend", "SocketServerClient: onSendPacketEnd: " + packet.hashCode());
    }

    /**
     * 开始接受一个新的数据包时的回调
     */
    @Override
    public void onReceivePacketBegin(SocketClient client, SocketResponsePacket packet) {
        LogUtils.d("onReceive", "SocketServerClient: onReceivePacketBegin: " + packet.hashCode());
    }

    /**
     * 完成接受一个新的数据包时的回调
     */
    @Override
    public void onReceivePacketEnd(SocketClient client, SocketResponsePacket packet) {
        LogUtils.d("onReceive", "SocketServerClient: onReceivePacketEnd: " + packet.hashCode());
    }

    /**
     * 取消接受一个新的数据包时的回调
     * 在断开连接时会触发
     */
    @Override
    public void onReceivePacketCancel(SocketClient client, SocketResponsePacket packet) {
        LogUtils.d("onReceive", "SocketServerClient: onReceivePacketCancel: " + packet.hashCode());
    }

    /**
     * 接受一个新的数据包的进度回调
     * progress值为[0.0f, 1.0f]
     * 仅作用于ReadStrategy为AutoReadByLength的自动读取
     * 因AutoReadByLength可以首先接受到剩下的数据包长度
     */
    @Override
    public void onReceivingPacketInProgress(SocketClient client, SocketResponsePacket packet, float progress, int receivedLength) {
        LogUtils.d("onReceive", "SocketServerClient: onReceivingPacketInProgress: " + packet.hashCode() + " : " + progress + " : " + receivedLength);
    }


    /**
     * tencent lbs
     * @param view
     */
    private static final int[] LEVELS = new int[] {
            TencentLocationRequest.REQUEST_LEVEL_GEO
            , TencentLocationRequest.REQUEST_LEVEL_NAME
            , TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA
            , TencentLocationRequest.REQUEST_LEVEL_POI};
    private static final int DEFAULT = 2;
    private int mLevel = LEVELS[DEFAULT];
    private TencentLocationManager mLocationManager;

    // 响应点击"开始"
    public void startLocation(View view) {
        // 创建定位请求
        TencentLocationRequest request = TencentLocationRequest.create()
                .setInterval(5*1000) // 设置定位周期
                .setAllowGPS(true)  //当为false时，设置不启动GPS。默认启动
                .setQQ("10001")
                .setRequestLevel(mLevel); // 设置定位level
        // 开始定位
        mLocationManager.requestLocationUpdates(request, this,getMainLooper());
    }

    // 响应点击"停止"
    public void stopLocation(View view) {
        mLocationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(TencentLocation location, int error, String reason) {
        String msg = null;
        if (error == TencentLocation.ERROR_OK) {
            // 定位成功
            msg = toString(location, mLevel);
        } else {
            // 定位失败
            msg = "定位失败: " + reason;
        }
    }

    @Override
    public void onStatusUpdate(String name, int status, String desc) {
        String message = "{name=" + name + ", new status=" + status + ", desc=" + desc + "}";
        if (status == STATUS_DENIED) {
			/* 检测到定位权限被内置或第三方的权限管理或安全软件禁用, 导致当前应用**很可能无法定位**
			 * 必要时可对这种情况进行特殊处理, 比如弹出提示或引导
			 */
            Toast.makeText(this, "定位权限被禁用!", Toast.LENGTH_SHORT).show();
        }
    }

    // ===== util method
    private static String toString(TencentLocation location, int level) {
        StringBuilder sb = new StringBuilder();
        sb.append("latitude=").append(location.getLatitude()).append(",");
        sb.append("longitude=").append(location.getLongitude()).append(",");
        sb.append("altitude=").append(location.getAltitude()).append(",");
        sb.append("accuracy=").append(location.getAccuracy()).append(",");
        switch (level) {
            case TencentLocationRequest.REQUEST_LEVEL_GEO:
                break;

            case TencentLocationRequest.REQUEST_LEVEL_NAME:
                sb.append("name=").append(location.getName()).append(",");
                sb.append("address=").append(location.getAddress()).append(",");
                break;

            case TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA:

            case TencentLocationRequest.REQUEST_LEVEL_POI:

            case 7:
                sb.append("nation=").append(location.getNation()).append(",");
                sb.append("province=").append(location.getProvince()).append(",");
                sb.append("city=").append(location.getCity()).append(",");
                sb.append("district=").append(location.getDistrict()).append(",");
                sb.append("town=").append(location.getTown()).append(",");
                sb.append("village=").append(location.getVillage()).append(",");
                sb.append("street=").append(location.getStreet()).append(",");
                sb.append("streetNo=").append(location.getStreetNo()).append(",");
                if (level == TencentLocationRequest.REQUEST_LEVEL_POI) {
                    List<TencentPoi> poiList = location.getPoiList();
                    int size = poiList.size();
                    for (int i = 0, limit = 3; i < limit && i < size; i++) {
                        sb.append("\n");
                        sb.append("poi[" + i + "]=").append(toString(poiList.get(i))).append(",");
                    }
                }
                break;

            default:
                break;
        }
        return sb.toString();
    }

    private static String toString(TencentPoi poi) {
        StringBuilder sb = new StringBuilder();
        sb.append("name=").append(poi.getName()).append(",");
        sb.append("address=").append(poi.getAddress()).append(",");
        sb.append("catalog=").append(poi.getCatalog()).append(",");
        sb.append("distance=").append(poi.getDistance()).append(",");
        sb.append("latitude=").append(poi.getLatitude()).append(",");
        sb.append("longitude=").append(poi.getLongitude()).append(",");
        return sb.toString();
    }

    /**
     * 权限成功回调函数
     * @param requestCode
     */
    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);

    }


    /**
     * 请求权限
     */
    public void requestPermission(String[] permissionArr, int result){
        requestPermission(permissionArr, result);
    }

}
