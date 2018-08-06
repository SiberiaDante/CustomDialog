package com.siberiadante.customdialoglib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * @Created SiberiaDante
 * @Describe： 仿IOS实现底部弹窗，可拓展多个item
 * @Time: 2017/6/14
 * @Email: 994537867@qq.com
 * @GitHub: https://github.com/SiberiaDante
 */
public class BottomPopupWindow {

    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    private LinearLayout lLayout_content;
    private ScrollView sLayout_content;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;
    private View view_line, view_line_bottom;
    private boolean isScroll = true;

    public BottomPopupWindow(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            display = windowManager.getDefaultDisplay();
        }
    }

    public BottomPopupWindow builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_window_bottom_layout, null);
        view.setMinimumWidth(display.getWidth());
        sLayout_content = view.findViewById(R.id.scroll_view_layout_content);
        lLayout_content = view.findViewById(R.id.layout_popup_window_content);
        txt_title = view.findViewById(R.id.tv_popup_window_title);
        view_line = view.findViewById(R.id.view_line);
//        view_line_bottom = view.findViewById(R.id.bottom_item_dialog_view_bottom_line);
        txt_cancel = view.findViewById(R.id.tv_popup_window_cancel);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog = new Dialog(context, R.style.ActionGeneralDialog);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.START | Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            dialogWindow.setAttributes(lp);
        }
        return this;
    }

    public BottomPopupWindow setTitle(String title) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        view_line.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public BottomPopupWindow setCancelView(String content, int color, float size) {
        txt_cancel.setText(content);
        txt_cancel.setTextColor(color);
        txt_cancel.setTextSize(size);
        return this;
    }

    public BottomPopupWindow setCancelViewGone() {
        txt_cancel.setVisibility(View.GONE);
//        view_line_bottom.setVisibility(View.GONE);
        return this;
    }

    public BottomPopupWindow setTitle(String title, int color, float size) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        view_line.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        txt_title.setTextColor(color);
        txt_title.setTextSize(size);
        return this;
    }

    public BottomPopupWindow setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public BottomPopupWindow setCanceled(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public BottomPopupWindow addSheetItem(String strItem, int color,
                                          OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }
    public BottomPopupWindow addSheetItem(String strItem, int color, int textSize,
                                           OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color, textSize, listener));
        return this;
    }
    public void show() {
        setSheetItems();
        dialog.show();
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }
        int size = sheetItemList.size();
        if (size >= 7 && isScroll) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) sLayout_content.getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }
        for (int i = 0; i <= size - 1; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i);
            String strItem = sheetItem.name;
            int color = sheetItem.color;
            final OnSheetItemClickListener listener = sheetItem.itemClickListener;
            TextView textView = new TextView(context);
            textView.setTextColor(color);
            textView.setText(strItem);
            if (sheetItem.mTextSize == 0) {
                textView.setTextSize(18);
            } else {
                textView.setTextSize(sheetItem.mTextSize);
            }
            textView.setGravity(Gravity.CENTER);
            if (size - 1 == 0) {
                if (showTitle) {
                    textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                }
            } else {
                if (showTitle) {
                    if (i >= 0 && i < size - 1) {
                        textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                    }
                } else {
                    if (i == 0) {
                        textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                    } else if (i < size - 1) {
                        textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);
                    }
                }
            }
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, height));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });
            lLayout_content.addView(textView);
            if ((i >= 0) && (i < size - 1)) {//此if语句是我加的使每一小项之间有分割线
                View v = new View(context);
                v.setBackgroundColor(Color.parseColor("#c6c6c6"));
                v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
                lLayout_content.addView(v);
            }
        }
    }

    /**
     * @param isScroll true:大于7条时页面高度为屏幕高度一半，内容可滑动
     */
    public BottomPopupWindow setIsScroll(boolean isScroll) {
        this.isScroll = isScroll;
        return this;
    }
//    public enum SheetItemColor {
//        Blue("#037BFF"), Red("#FD4A2E"), Gray("#5b5b5b");
//
//        private String name;
//
//        private SheetItemColor(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//    }

    public interface OnSheetItemClickListener {
        void onClick(int which);
    }

    public class SheetItem {
        String name;
        OnSheetItemClickListener itemClickListener;
        int color;
        int mTextSize = 0;

        public SheetItem(String name, int color,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }

        public SheetItem(String name, int color, int textSize,
                         OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.mTextSize = textSize;
            this.itemClickListener = itemClickListener;
        }
    }
}
