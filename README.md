# CustomDialog
一个万能的通用支持多功能的Dialog
![CustomDialog](/images/dialog.gif)
## 依赖
```
implementation 'com.github.SiberiaDante:CustomDialog:v1.1.1'
```
![1](/images/1.png)
![2](/images/2.png)
![3](/images/3.png)
![4](/images/4.png)
![5](/images/5.png)
![6](/images/6.png)
![7](/images/7.png)
![8](/images/8.png)
![9](/images/9.png)

#### [我的博客园](http://www.cnblogs.com/shen-hua/)
#### [我的AndroidUtilLib](https://github.com/SiberiaDante/AndroidUtilLib)


## 说明
### [CustomDialog]：
一个万能的通用支持多功能的Dialog,简单两步即可实现

* 只需要传入自定义的CustomDialog布局和需要设置点击事件的控件的id即可
```
    CustomDialog customDialog=new CustomDialog(this,
                                    R.layout.dialog_layout,
                                    new int[]{R.id.dialog_sure});
```
* CustomDialog按钮的监听
```
    customDialog.setOnDialogItemClickListener(new CustomDialog.OnCustomDialogItemClickListener() {
            @Override
            public void OnCustomDialogItemClick(CustomDialog dialog, View view) {
                Toast.makeText(MainActivity.this, "确定按钮被点击了", Toast.LENGTH_LONG).show();
            }
        });
```
* CustomDialog显示与隐藏

    ```customDialog.show();```

    ```customDialog.dismiss();```

#### 如果你想改变CustomDialog的显示位置、动画、控制按钮点击是否取消显示CustomDialog，可以使用一下构造
* 改变CustomDialog动画
```
    /**
     * @param context
     * @param layoutResID    自己定义的布局
     * @param listenedItems  需要设置点击事件的资源id
     * @param animationResId dialog 动画，如果不使用自己的，则传0
     */
    public CustomDialog(Context context, int layoutResID, int[] listenedItems, int animationResId) {
        super(context, R.style.Custom_Dialog_Style); //dialog的样式
        this.context = context;
        this.mLayoutResId = layoutResID;
        this.mListenedItems = listenedItems;
        this.mAnimationResId = animationResId;
    }
```
* 控制点击CustomDialog后是否自动取消显示CustomDialog
```
    /**
     * @param context
     * @param layoutResID   自己定义的布局
     * @param listenedItems 需要设置点击事件的资源id
     * @param isDismiss     按钮点击后是否默认取消dialog {@code true}取消 {@code false} 不取消
     */
    public CustomDialog(Context context, int layoutResID, int[] listenedItems, boolean isDismiss) {
        super(context, R.style.Custom_Dialog_Style); //dialog的样式
        this.context = context;
        this.mLayoutResId = layoutResID;
        this.mListenedItems = listenedItems;
        this.mIsDismiss = isDismiss;
    }
```
* 改变CustomDialog的显示位置，同时可以控制点击CustomDialog后是否自动取消显示CustomDialog
```
    /**
     * @param context
     * @param layoutResID   自己定义的布局
     * @param listenedItems 需要设置点击事件的资源id
     * @param isDismiss     按钮点击后是否默认取消dialog {@code true}取消 {@code false} 不取消
     * @param position      dialog 显示位置，默认CENTER,不设置时可以传0
     */
    public CustomDialog(Context context, int layoutResID, int[] listenedItems, boolean isDismiss, int position) {
        super(context, R.style.Custom_Dialog_Style); //dialog的样式
        this.context = context;
        this.mLayoutResId = layoutResID;
        this.mListenedItems = listenedItems;
        this.mPosition = position;
    }
```
* 可以同时改变CustomDialog动画、显示位置、按钮点击是否取消显示CustomDialog
```
    /**
     * 满足你各种需求
     *
     * @param context
     * @param layoutResID    自己定义的布局
     * @param listenedItems  需要设置点击事件的资源id
     * @param animationResId dialog 动画，如果不使用自己的，则传0
     * @param isDismiss      按钮点击后是否默认取消dialog {@code true}取消 {@code false} 不取消
     * @param position       dialog 显示位置，默认CENTER,不设置时可以传0
     */
    public CustomDialog(Context context, int layoutResID, int[] listenedItems, int animationResId, boolean isDismiss, int position) {
        super(context, R.style.Custom_Dialog_Style); //dialog的样式
        this.context = context;
        this.mLayoutResId = layoutResID;
        this.mListenedItems = listenedItems;
        this.mAnimationResId = animationResId;
        this.mIsDismiss = isDismiss;
        this.mPosition = position;
    }
```

### 其他几个常用Dialog

* [EnsureDialog] :确认Dialog
```
EnsureDialog ensureDialog = new EnsureDialog(this).builder()
                .setGravity(Gravity.CENTER)//默认居中，可以不设置
                .setTitle("这里是一个标题", getResources().getColor(R.color.black))//可以不设置标题颜色，默认系统颜色
                .setCancelable(false)
                .setSubTitle("这是一个副标题")
                .setIncon(R.mipmap.tip_icon)//不设置图标，默认没有图标
                .setNegativeButton("取消", new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setPositiveButton("确认", getResources().getColor(R.color.red), new View.OnClickListener() {//可以选择设置颜色和不设置颜色两个方法
                    @Override
                    public void onClick(View view) {
                        ensureDialog.dismiss();

                    }
                });
        ensureDialog.show();
```
* [BottomPopupWindow] :仿IOS底部弹窗Dialog
```
new BottomPopupWindow(this).builder()
                .setTitle("选择").setCancelable(false).setCanceled(true)
                .addSheetItem("相机", BottomPopupWindow.SheetItemColor.Blue, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                    }
                })
                ...//可以addSheetItem多个item
                .addSheetItem("相册", BottomPopupWindow.SheetItemColor.Blue, new BottomPopupWindow.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                    }
                }).show();
```

* [EditDialog] :带EditText确认Dialog
```
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
```
### 报错指南
*
```
Error:Execution failed for task ':app:processDebugResources'. > com.android.ide.common.process.ProcessException: Failed to execute aapt
```
解决方法：COMPILE_SDK_VERSION>=26
### 版本更新说明：
* v1.0.3：
    * BottomPopupWindow支持Item颜色、字体大小设置；支持底部取消按钮隐藏；支持Item超过7条时滚动显示或者全部显示
* v1.0.2：
    * CustomDialog 增加支持动态设置View属性

[CustomDialog]:https://github.com/SiberiaDante/CustomDialog/blob/0e3849999b7ed3398255b1312fa28d7d38b23306/customdialoglib/src/main/java/com/siberiadante/customdialoglib/CustomDialog.java
[EditDialog]:https://github.com/SiberiaDante/CustomDialog/blob/0e3849999b7ed3398255b1312fa28d7d38b23306/customdialoglib/src/main/java/com/siberiadante/customdialoglib/EditDialog.java
[BottomPopupWindow]:https://github.com/SiberiaDante/CustomDialog/blob/0e3849999b7ed3398255b1312fa28d7d38b23306/customdialoglib/src/main/java/com/siberiadante/customdialoglib/BottomPopupWindow.java
[EnsureDialog]:https://github.com/SiberiaDante/CustomDialog/blob/0e3849999b7ed3398255b1312fa28d7d38b23306/customdialoglib/src/main/java/com/siberiadante/customdialoglib/EnsureDialog.java
