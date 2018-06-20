package unionlive.com.umqpos.ui.activity.coupon_act;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import unionlive.com.umqpos.R;

/**
 * 
 * 自己定义的对话框
 * 
 * 
 * @author 史伟成 E-mail:495095492@qq.com 电话：15216801944
 * @version 创建时间：2013-10-21 下午5:01:30
 * 
 */
public class MyDialog extends Dialog {
	Context context;
	private TextView title, message;
	private Button ok, no;
	private ProgressBar dialog_pb;

	public Button getOk() {
		return ok;
	}

	public void setOk(Button ok) {
		this.ok = ok;
	}

	public Button getNo() {
		return no;
	}

	public void setNo(Button no) {
		this.no = no;
	}

	public MyDialog(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * 
	 * @param context
	 * @param theme
	 *            自己定义风格
	 */
	public MyDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog);
		title = (TextView) this.findViewById(R.id.dialog_tv01);
		message = (TextView) this.findViewById(R.id.dialog_tv02);
		ok = (Button) this.findViewById(R.id.dialog_but01);
		no = (Button) this.findViewById(R.id.dialog_but02);
		dialog_pb = (ProgressBar) this.findViewById(R.id.dialog_pb);
	}

	/**
	 * 设置标题
	 */
	@Override
	public void setTitle(CharSequence title) {
		this.findViewById(R.id.titleLayout).setVisibility(View.VISIBLE);
		this.title.setText(title);
	}

	@Override
	public void setTitle(int titleId) {
		setTitle(context.getString(titleId));
	}

	/**
	 * 设置带进度条的对话框
	 * 
	 * @param message
	 */
	public void setProgressBar(CharSequence message) {
		this.dialog_pb.setVisibility(View.VISIBLE);
		this.message.setVisibility(View.VISIBLE);
		this.message.setText(message);

	}

	public void setProgressBar(int message) {
		setProgressBar(context.getString(message));
	}

	/**
	 * 设置消息内容
	 * 
	 * @param message
	 */
	public void setMessage(CharSequence message) {
		this.message.setVisibility(View.VISIBLE);
		this.message.setText(message);
	}

	public void setMessage(int message) {
		setMessage(context.getString(message));
	}

	/**
	 * 设置确定按钮
	 * 
	 * @param ok
	 * @param clickListener
	 */
	public void setPositiveButton(CharSequence ok,
			android.view.View.OnClickListener clickListener) {
		((LinearLayout) this.findViewById(R.id.button))
				.setVisibility(View.VISIBLE);
		this.ok.setVisibility(View.VISIBLE);
		this.ok.setText(ok);
		if (clickListener == null)
			this.ok.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(View paramView) {
					MyDialog.this.dismiss();
				}
			});
		else
			this.ok.setOnClickListener(clickListener);
	}

	public void setPositiveButton(int ok,
			android.view.View.OnClickListener clickListener) {
		setPositiveButton(context.getString(ok), clickListener);
	}

	/**
	 * 设置取消按钮
	 *
	 * @param no
	 * @param clickListener
	 */
	public void setNegativeButton(CharSequence no,
			android.view.View.OnClickListener clickListener) {
		((LinearLayout) this.findViewById(R.id.button))
				.setVisibility(View.VISIBLE);
		this.no.setVisibility(View.VISIBLE);
		this.no.setText(no);
		if (clickListener == null)
			this.no.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(View paramView) {
					MyDialog.this.dismiss();
				}
			});
		else
			this.no.setOnClickListener(clickListener);
	}

	public void setNegativeButton(int no,
			android.view.View.OnClickListener clickListener) {
		setNegativeButton(context.getString(no), clickListener);
	}

	/**
	 * 添加View
	 *
	 * @param view
	 */
	public void putView(View view) {
		((LinearLayout) this.findViewById(R.id.dialog_layout)).addView(view);
	}

	/**
	 * 生成对话框
	 *
	 * @param context
	 *
	 * @param title
	 *            标题
	 * @param message
	 *            需要显示的内容
	 * @param pbmessage
	 *            进度条显示信息
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 * @param view
	 *            添加自定义View
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getMessageDialog(Context context,
											CharSequence title, CharSequence message, CharSequence pbmessage,
											android.view.View.OnClickListener okbutton, CharSequence ok,
											android.view.View.OnClickListener nobutton, CharSequence no,
											View view, Boolean cancelable) {
		cancelable = cancelable != null ? cancelable : true;
		MyDialog alerDialog = new MyDialog(context, R.style.MyDialog);
		alerDialog.setCancelable(cancelable);
		alerDialog.show();
		if (title != null) {
			alerDialog.setTitle(title);
		}
		if (message != null) {
			alerDialog.setMessage(message);
		}
		if (pbmessage != null) {
			alerDialog.setProgressBar(pbmessage);
		}
		if (ok != null) {
			alerDialog.setPositiveButton(ok, okbutton);
		}
		if (no != null) {
			alerDialog.setNegativeButton(no, nobutton);
		}
		if (view != null) {
			alerDialog.putView(view);//把试图添加到上下滑动的ScrollView里面
		}
		return alerDialog;
	}

	/**
	 * 生成对话框
	 *
	 * @param context
	 *
	 * @param title
	 *            标题
	 * @param message
	 *            需要显示的内容
	 * @param pbmessage
	 *            进度条显示信息
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 * @param view
	 *            添加自定义View
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getMessageDialog(Context context, Integer title,
											Integer message, Integer pbmessage,
											android.view.View.OnClickListener okbutton, Integer ok,
											android.view.View.OnClickListener nobutton, Integer no, View view,
											Boolean cancelable) {
		return getMessageDialog(context,
				(title != null) ? context.getString(title) : null,
				(message != null) ? context.getString(message) : null,
				(pbmessage != null) ? context.getString(pbmessage) : null,
				okbutton, (ok != null) ? context.getString(ok) : null,
				nobutton, (no != null) ? context.getString(no) : null, view,
				cancelable);
	}

	/**
	 * 生成对话框
	 *
	 * @param context
	 *
	 * @param title
	 *            标题
	 * @param message
	 *            需要显示的内容
	 * @param pbmessage
	 *            进度条显示信息
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 * @param view
	 *            添加自定义View
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getMessageDialog(Context context, Integer title,
											CharSequence message, CharSequence pbmessage,
											android.view.View.OnClickListener okbutton, Integer ok,
											android.view.View.OnClickListener nobutton, Integer no, View view,
											Boolean cancelable) {
		return getMessageDialog(context,
				(title != null) ? context.getString(title) : null, message,
				pbmessage, okbutton, (ok != null) ? context.getString(ok)
						: null, nobutton, (no != null) ? context.getString(no)
						: null, view, cancelable);
	}




	/**
	 * 生成对话框
	 *
	 * @param context
	 *
	 * @param title
	 *            标题
	 * @param message
	 *            需要显示的内容
	 * @param pbmessage
	 *            进度条显示信息
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 * @param view
	 *            添加自定义View
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getMessageDialoga(Context context, Integer title,
											 CharSequence message, CharSequence pbmessage,
											 android.view.View.OnClickListener okbutton, Integer ok,
											 android.view.View.OnClickListener nobutton, Integer no, View view,
											 Boolean cancelable) {
		return getMessageDialog(context,
				(title != null) ? context.getString(title) : null, message,
				pbmessage, okbutton, (ok != null) ? context.getString(ok)
						: null, nobutton, (no != null) ? context.getString(no)
						: null, view, cancelable);
	}

	private static MyDialog alerDialog;

	/**
	 * 错误信息对话框
	 *
	 * @param title
	 *            标题
	 * @param ok
	 *            确定按钮显示信息
	 * @param replyMsg
	 *            错误信息
	 * @param context
	 * @param b
	 *            是否需要关闭Activity
	 */
	public static void getErrorMessageDialog(CharSequence title,
											 CharSequence ok, CharSequence replyMsg, final Context context,
											 final boolean b) {
		alerDialog = MyDialog.getMessageDialog(context, title, replyMsg, null,
				null, null, new android.view.View.OnClickListener() {
					@Override
					public void onClick(View paramView) {
						if (b)
							((Activity) context).finish();
						alerDialog.dismiss();
					}
				}, ok, null, null);
		alerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface paramDialogInterface) {
				if (b)
					((Activity) context).finish();
				alerDialog.dismiss();
			}
		});
	}

	/**
	 * 错误信息对话框
	 *
	 * @param title
	 *            标题
	 * @param ok
	 *            确定按钮显示信息
	 * @param replyMsg
	 *            错误信息
	 * @param context
	 * @param b
	 *            是否需要关闭Activity
	 */
	public static void getErrorMessageDialog(int title, int ok,
											 CharSequence replyMsg, Context context, boolean b) {
		getErrorMessageDialog(context.getString(title), context.getString(ok),
				replyMsg, context, b);
	}

	/**
	 * 错误信息对话框
	 *
	 * @param title
	 *            标题
	 * @param ok
	 *            确定按钮显示信息
	 * @param replyMsg
	 *            错误信息
	 * @param context
	 * @param b
	 *            是否需要关闭Activity
	 */
	public static void getErrorMessageDialog(int title, int ok, int replyMsg,
											 Context context, boolean b) {
		getErrorMessageDialog(context.getString(title), context.getString(ok),
				context.getString(replyMsg), context, b);
	}

	/**
	 * 生成对话框 add view
	 *
	 * @param context
	 * @param title
	 *            标题
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 *            添加自定义View集合
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getViewDialog(Context context, CharSequence title,
										 android.view.View.OnClickListener okbutton, CharSequence ok,
										 android.view.View.OnClickListener nobutton, CharSequence no,
										 List<View> views, Boolean cancelable) {
		cancelable = cancelable != null ? cancelable : true;
		final MyDialog alerDialog = new MyDialog(context, R.style.MyDialog);
		alerDialog.setCancelable(cancelable);
		alerDialog.show();
		if (title != null)
			alerDialog.setTitle(title);
		if (ok != null) {
			alerDialog.setPositiveButton(ok, okbutton);
		}
		if (no != null) {
			alerDialog.setNegativeButton(no, nobutton);
		}
		if (views != null)
			for (View v : views) {
				alerDialog.putView(v);
			}
		if (cancelable)
			alerDialog
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(
								DialogInterface paramDialogInterface) {
							alerDialog.dismiss();
						}
					});
		return alerDialog;

	}

	/**
	 * 生成对话框 add view
	 *
	 * @param context
	 * @param title
	 *            标题
	 * @param okbutton
	 *            确定按钮事件
	 * @param ok
	 *            确定按钮显示内容
	 * @param nobutton
	 *            取消按钮事件
	 * @param no
	 *            取消按钮显示内容
	 *            添加自定义View集合
	 * @param cancelable
	 *            是否可策销
	 * @return
	 */
	public static MyDialog getViewDialog(Context context, Integer title,
										 android.view.View.OnClickListener okbutton, Integer ok,
										 android.view.View.OnClickListener nobutton, Integer no,
										 List<View> views, Boolean cancelable) {
		return getViewDialog(context,
				(title != null) ? context.getString(title) : null, okbutton,
				(ok != null) ? context.getString(ok) : null, nobutton,
				(no != null) ? context.getString(no) : null, views, cancelable);
	}
}
