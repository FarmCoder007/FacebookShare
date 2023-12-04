package com.allsaints.share;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * author : xuwenbin
 * date : 2023/11/23 11:13
 * description :
 */
public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.setCanceledOnTouchOutside(true);
//        return dialog;
//    }


    private BottomSheetBehavior<View> getBottomSheetBehavior() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            View view = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (view != null) {
                return BottomSheetBehavior.from(view);
            }
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.share_dialog_layout, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.MyBottomSheetDialog3);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        show(activity.getSupportFragmentManager(), "MyBottomSheetDialogFragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        // 应用样式
        getDialog().getWindow().setWindowAnimations(R.style.DialogAnimation);
        BottomSheetBehavior<View> behavior = getBottomSheetBehavior();
        if (behavior != null) {
            // 禁止拖动
            behavior.setDraggable(false);
        }
    }
}
