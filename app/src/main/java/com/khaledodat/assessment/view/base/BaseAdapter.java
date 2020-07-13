package com.khaledodat.assessment.view.base;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Generic Base adapter for recycler views
 */
public abstract class BaseAdapter<T extends RecyclerView.ViewHolder, D> extends RecyclerView.Adapter<T>{

    public abstract void setData(List<D> data);
    public abstract void appendData(List<D> data);


}