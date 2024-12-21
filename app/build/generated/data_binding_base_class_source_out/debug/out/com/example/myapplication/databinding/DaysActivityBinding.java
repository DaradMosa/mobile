// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DaysActivityBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final RecyclerView rvActivities;

  @NonNull
  public final TextView tvDate;

  @NonNull
  public final TextView tvDay;

  private DaysActivityBinding(@NonNull CardView rootView, @NonNull RecyclerView rvActivities,
      @NonNull TextView tvDate, @NonNull TextView tvDay) {
    this.rootView = rootView;
    this.rvActivities = rvActivities;
    this.tvDate = tvDate;
    this.tvDay = tvDay;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static DaysActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DaysActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.days_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DaysActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rvActivities;
      RecyclerView rvActivities = ViewBindings.findChildViewById(rootView, id);
      if (rvActivities == null) {
        break missingId;
      }

      id = R.id.tvDate;
      TextView tvDate = ViewBindings.findChildViewById(rootView, id);
      if (tvDate == null) {
        break missingId;
      }

      id = R.id.tvDay;
      TextView tvDay = ViewBindings.findChildViewById(rootView, id);
      if (tvDay == null) {
        break missingId;
      }

      return new DaysActivityBinding((CardView) rootView, rvActivities, tvDate, tvDay);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
