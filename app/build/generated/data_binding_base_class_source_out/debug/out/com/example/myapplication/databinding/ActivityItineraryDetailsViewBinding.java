// Generated by view binder compiler. Do not edit!
package com.example.myapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.myapplication.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityItineraryDetailsViewBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RecyclerView rvDays;

  @NonNull
  public final TextView txtBudget;

  @NonNull
  public final TextView txtDate;

  @NonNull
  public final TextView txtDest;

  @NonNull
  public final TextView txtTitle;

  private ActivityItineraryDetailsViewBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backBtn, @NonNull ConstraintLayout main, @NonNull RecyclerView rvDays,
      @NonNull TextView txtBudget, @NonNull TextView txtDate, @NonNull TextView txtDest,
      @NonNull TextView txtTitle) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.main = main;
    this.rvDays = rvDays;
    this.txtBudget = txtBudget;
    this.txtDate = txtDate;
    this.txtDest = txtDest;
    this.txtTitle = txtTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityItineraryDetailsViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityItineraryDetailsViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_itinerary_details_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityItineraryDetailsViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backBtn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.rvDays;
      RecyclerView rvDays = ViewBindings.findChildViewById(rootView, id);
      if (rvDays == null) {
        break missingId;
      }

      id = R.id.txtBudget;
      TextView txtBudget = ViewBindings.findChildViewById(rootView, id);
      if (txtBudget == null) {
        break missingId;
      }

      id = R.id.txtDate;
      TextView txtDate = ViewBindings.findChildViewById(rootView, id);
      if (txtDate == null) {
        break missingId;
      }

      id = R.id.txtDest;
      TextView txtDest = ViewBindings.findChildViewById(rootView, id);
      if (txtDest == null) {
        break missingId;
      }

      id = R.id.txtTitle;
      TextView txtTitle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitle == null) {
        break missingId;
      }

      return new ActivityItineraryDetailsViewBinding((ConstraintLayout) rootView, backBtn, main,
          rvDays, txtBudget, txtDate, txtDest, txtTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
