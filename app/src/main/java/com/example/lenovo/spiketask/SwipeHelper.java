package com.example.lenovo.spiketask;


import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerItemTouchHelperListener listener;
    private String type;

    public SwipeHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener,String type) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
        this.type=type;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
         View foregroundView=null;
        if (viewHolder != null) {
            switch (type){
                case "Movie":
                     foregroundView = ((MAdapter.ViewHolder0) viewHolder).viewForeground;
                    break;
                case "Book":
                    foregroundView = ((MAdapter.ViewHolder2) viewHolder).viewForeground;
                    break;
                case "Music":
                    foregroundView = ((MAdapter.ViewHolder3) viewHolder).viewForeground;
                    break;
                case "Series":
                    foregroundView = ((MAdapter.ViewHolder4) viewHolder).viewForeground;
                    break;
                case "Article":
                    foregroundView = ((MAdapter.ViewHolder1) viewHolder).viewForeground;
                    break;
            }






           getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
         View foregroundView =null;
        switch (type){
            case "Movie":
                foregroundView = ((MAdapter.ViewHolder0) viewHolder).viewForeground;
                break;
            case "Book":
                foregroundView = ((MAdapter.ViewHolder2) viewHolder).viewForeground;
                break;
            case "Music":
                foregroundView = ((MAdapter.ViewHolder3) viewHolder).viewForeground;
                break;
            case "Series":
                foregroundView = ((MAdapter.ViewHolder4) viewHolder).viewForeground;
                break;
            case "Article":
                foregroundView = ((MAdapter.ViewHolder1) viewHolder).viewForeground;
                break;
        }






        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
               actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {



        View foregroundView =null;
        switch (type){
            case "Movie":
                foregroundView = ((MAdapter.ViewHolder0) viewHolder).viewForeground;
                break;
            case "Book":
                foregroundView = ((MAdapter.ViewHolder2) viewHolder).viewForeground;
                break;
            case "Music":
                foregroundView = ((MAdapter.ViewHolder3) viewHolder).viewForeground;
                break;
            case "Series":
                foregroundView = ((MAdapter.ViewHolder4) viewHolder).viewForeground;
                break;
            case "Article":
                foregroundView = ((MAdapter.ViewHolder1) viewHolder).viewForeground;
                break;
        }
       getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        View foregroundView =null;
        switch (type){
            case "Movie":
                foregroundView = ((MAdapter.ViewHolder0) viewHolder).viewForeground;
                break;
            case "Book":
                foregroundView = ((MAdapter.ViewHolder2) viewHolder).viewForeground;
                break;
            case "Music":
                foregroundView = ((MAdapter.ViewHolder3) viewHolder).viewForeground;
                break;
            case "Series":
                foregroundView = ((MAdapter.ViewHolder4) viewHolder).viewForeground;
                break;
            case "Article":
                foregroundView = ((MAdapter.ViewHolder1) viewHolder).viewForeground;
                break;
        }

        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}