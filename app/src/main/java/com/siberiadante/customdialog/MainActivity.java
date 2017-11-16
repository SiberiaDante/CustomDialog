package com.siberiadante.customdialog;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siberiadante.customdialoglib.BottomPopupWindow;
import com.siberiadante.customdialoglib.CustomDialog;
import com.siberiadante.customdialoglib.EditDialog;
import com.siberiadante.customdialoglib.EnsureDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EnsureDialog ensureDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_general_dialog_one).setOnClickListener(this);
        findViewById(R.id.btn_general_dialog_two).setOnClickListener(this);
        findViewById(R.id.btn_general_dialog_three).setOnClickListener(this);
        findViewById(R.id.btn_general_dialog_four).setOnClickListener(this);
        findViewById(R.id.btn_bottom_popup_widow).setOnClickListener(this);
        findViewById(R.id.btn_edit_dialog).setOnClickListener(this);
        findViewById(R.id.btn_custom_dialog).setOnClickListener(this);
        findViewById(R.id.btn_sd_custom_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_general_dialog_one:
                showEnsureDialogOne();
                break;
            case R.id.btn_general_dialog_two:
                showEnsureDialogTwo();
                break;
            case R.id.btn_general_dialog_three:
                showEnsureDialogThree();
                break;
            case R.id.btn_general_dialog_four:
                showEnsureDialogFour();
                break;
            case R.id.btn_bottom_popup_widow:
                showBottomPopupWindow();
                break;
            case R.id.btn_edit_dialog:
                showEditDialog();
                break;
            case R.id.btn_custom_dialog:
                showCustomDialog();
                break;
            case R.id.btn_sd_custom_dialog:
                showSDCustomEditDialog();
                break;
        }
    }

    /**
     * EditDialog
     */
    private void showEditDialog() {
        final EditDialog dialog = new EditDialog(this).builder();
        dialog.setTitle("可编辑Dialog");
        dialog.setCancelable(false);
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String msg = dialog.getMsg();
                Toast.makeText(MainActivity.this, "输入内容为：" + msg, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * BottomPopupWindow
     */
    private void showBottomPopupWindow() {
        new BottomPopupWindow(this).builder()
                .setTitle("选择").setCancelable(false).setCanceled(true)
                .addSheetItem("相机", BottomPopupWindow.SheetItemColor.Blue, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        //需要对相机进行运行时权限的申请
                    }
                })
                .addSheetItem("相册", BottomPopupWindow.SheetItemColor.Blue, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        //调用手机相册的方法,该方法在下面有具体实现
                    }
                })
                .addSheetItem("时光机", BottomPopupWindow.SheetItemColor.Blue, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {

                    }
                })
                .show();
    }

    /**
     * EnsureDialog
     */
    private void showEnsureDialogOne() {
        ensureDialog = new EnsureDialog(this).builder()
                .setGravity(Gravity.CENTER)//默认居中，可以不设置
                .setTitle("这里是一个标题", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setCancelable(false)
                .setNegativeButton("取消", new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setPositiveButton("确认", getResources().getColor(R.color.sd_color_red), new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {
                        ensureDialog.dismiss();

                    }
                });
        ensureDialog.show();

    }

    private void showEnsureDialogTwo() {
        ensureDialog = new EnsureDialog(this).builder()
                .setGravity(Gravity.CENTER)//默认居中，可以不设置
                .setTitle("这里是一个标题", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setSubTitle("这是一个副标题")
                .setCancelable(false)
                .setNegativeButton("取消", new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setPositiveButton("确认", getResources().getColor(R.color.sd_color_red), new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {
                        ensureDialog.dismiss();

                    }
                });
        ensureDialog.show();

    }

    private void showEnsureDialogThree() {
        ensureDialog = new EnsureDialog(this).builder()
                .setGravity(Gravity.CENTER)//默认居中，可以不设置
                .setTitle("这里是一个标题", getResources().getColor(R.color.sd_color_black))//可以不设置标题颜色，默认系统颜色
                .setIncon(R.mipmap.tip_icon)//不设置图标，默认没有图标
                .setCancelable(false)
                .setNegativeButton("取消", new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setPositiveButton("确认", getResources().getColor(R.color.sd_color_red), new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {
                        ensureDialog.dismiss();

                    }
                });
        ensureDialog.show();

    }

    private void showEnsureDialogFour() {
        ensureDialog = new EnsureDialog(this).builder()
                .setGravity(Gravity.CENTER)//默认居中，可以不设置
                .setTitle("这里是一个标题", R.color.sd_color_black)//可以不设置标题颜色，默认系统颜色
                .setCancelable(false)
                .setCenterButton("取消", new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {

                    }
                })
        ;
        ensureDialog.show();
    }

    private void showCustomDialog() {
         CustomDialog customDialog = new CustomDialog(this, R.layout.dialog_sd_custom);
        customDialog.show();
    }

    /**
     * 这种方式使用中的缺陷就是需要资源id需要一一对应
     */
    private void showSDCustomEditDialog() {
        final CustomDialog customDialog = new CustomDialog(this,
                R.layout.dialog_sd_custom,
                new int[]{R.id.btn_cancel, R.id.btn_sure, R.id.edt_content, R.id.edt_sub_content},
                0,
                false,
                false,
                Gravity.CENTER);
        customDialog.setOnDialogItemClickListener(new CustomDialog.OnCustomDialogItemClickListener() {
            @Override
            public void OnCustomDialogItemClick(CustomDialog dialog, View view) {
                switch (view.getId()) {
                    case R.id.btn_cancel:
                        customDialog.dismiss();
                        break;
                    case R.id.btn_sure:

                        break;
                }
            }
        });

        customDialog.show();
        final List<View> viewList = customDialog.getViews();
        final Button btnSure = (Button) viewList.get(1);
        btnSure.setTextColor(ContextCompat.getColor(this, R.color.colorGray));
        final EditText edtContent = (EditText) viewList.get(2);
        final EditText edtSubContent = (EditText) viewList.get(3);
        edtContent.setHint("这是一个主内容");
        edtSubContent.setHint("这是一个副内容");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
