package com.xinyu.mwp.activity;

import android.os.Handler;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import com.xinyu.mwp.R;
import com.xinyu.mwp.activity.base.BaseControllerActivity;
import com.xinyu.mwp.exception.CheckException;
import com.xinyu.mwp.helper.CheckHelper;
import com.xinyu.mwp.util.Utils;
import com.xinyu.mwp.view.WPEditText;

import org.xutils.view.annotation.ViewInject;

/**
 * @author : created by chuangWu
 * @version : 0.01
 * @email : chuangwu127@gmail.com
 * @created time : 2017-01-11 11:31
 * @description : none
 * @for your attention : none
 * @revise : none
 */
public class ResetUserPwdActivity extends BaseControllerActivity {

    @ViewInject(R.id.phoneEditText)
    private WPEditText phoneEditText;
    @ViewInject(R.id.msgEditText)
    private WPEditText msgEditText;
    @ViewInject(R.id.pwdEditText1)
    private WPEditText pwdEditText1;
    @ViewInject(R.id.pwdEditText2)
    private WPEditText pwdEditText2;
    @ViewInject(R.id.okButton)
    private Button okButton;

    private CheckHelper checkHelper = new CheckHelper();

    @Override
    protected int getContentView() {
        return R.layout.activity_reset_user_pwd;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("重置密码");
        phoneEditText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        checkHelper.checkButtonState(okButton, phoneEditText, msgEditText, pwdEditText1, pwdEditText2);
        checkHelper.checkVerificationCode(msgEditText.getRightText(),phoneEditText);
    }

    @Override
    protected void initListener() {
        super.initListener();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckException exception = new CheckException();
                if (checkHelper.checkMobile(phoneEditText.getEditTextString(), exception)
                        && checkHelper.checkMobile(phoneEditText.getEditTextString(), exception)
                        && checkHelper.checkPassword(pwdEditText1.getEditTextString(), exception)
                        && checkHelper.checkPassword2(pwdEditText1.getEditTextString(), pwdEditText2.getEditTextString(), exception)) {
                    Utils.closeSoftKeyboard(v);
                    showLoader("正在修改...");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            closeLoader();
                            showToast("哈哈哈");
                        }
                    }, 2000);
                } else {
                    showToast(exception.getErrorMsg());
                }
            }
        });
    }
}
