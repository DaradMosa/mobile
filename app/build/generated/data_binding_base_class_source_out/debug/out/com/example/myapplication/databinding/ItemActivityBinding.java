// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemActivityBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView tvActivityDescription;

  @NonNull
  public final TextView tvActivityName;

  @NonNull
  public final TextView tvActivityTime;

  private ItemActivityBinding(@NonNull LinearLayout rootView,
      @NonNull TextView tvActivityDescription, @NonNull TextView tvActivityName,
      @NonNull TextView tvActivityTime) {
    this.rootView = rootView;
    this.tvActivityDescription = tvActivityDescription;
    this.tvActivityName = tvActivityName;
    this.tvActivityTime = tvActivityTime;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tvActivityDescription;
      TextView tvActivityDescription = ViewBindings.findChildViewById(rootView, id);
      if (tvActivityDescription == null) {
        break missingId;
      }

      id = R.id.tvActivityName;
      TextView tvActivityName = ViewBindings.findChildViewById(rootView, id);
      if (tvActivityName == null) {
        break missingId;
      }

      id = R.id.tvActivityTime;
      TextView tvActivityTime = ViewBindings.findChildViewById(rootView, id);
      if (tvActivityTime == null) {
        break missingId;
      }

      return new ItemActivityBinding((LinearLayout) rootView, tvActivityDescription, tvActivityName,
          tvActivityTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
