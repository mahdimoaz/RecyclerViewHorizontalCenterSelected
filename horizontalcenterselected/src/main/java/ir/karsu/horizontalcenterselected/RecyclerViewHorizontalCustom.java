package ir.karsu.horizontalcenterselected;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewHorizontalCustom extends RecyclerView implements ClickItemAmount {
    public RecyclerViewHorizontalCustom(@NonNull Context context) {
        super(context);

    }

    public RecyclerViewHorizontalCustom(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public RecyclerViewHorizontalCustom(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    boolean isInitial = false;
    AdapterAmount adapterAmount;
    List<String> amountList;
    ViewTreeObserver vtoDate;
    int finalWidthNum, allPixelsNum = 0;
    float itemWidthNum, paddingNum, firstItemWidthNum;

    public boolean isInitial() {
        return isInitial;
    }

    public void setAmountList(List<String> amountList) {
        this.amountList = amountList;
    }

    public void init(final Context context, final ItemListener onClickListener,
                     final int view, final float itemWidth,final int bgItem, final int bgitemSelected) {
        vtoDate = getViewTreeObserver();
        vtoDate.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                RecyclerViewHorizontalCustom.this.getViewTreeObserver().removeOnPreDrawListener(this);
                finalWidthNum = getMeasuredWidth();
                itemWidthNum = itemWidth;
                paddingNum = (finalWidthNum - itemWidthNum) / 2;
                firstItemWidthNum = paddingNum;
                allPixelsNum = 0;
                /*SnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(RecyclerViewHorizontalCustom.this);*/
                addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        synchronized (this) {
                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                                int expectedPositionDate = Math.round((allPixelsNum + paddingNum - firstItemWidthNum) / itemWidthNum);
                                if (expectedPositionDate < 0) {
                                    expectedPositionDate = 0;
                                }

                                // float margin = context.getResources().getDimension(R.dimen.margin);
                                scrollToPosAmount(expectedPositionDate);

                            }
                        }
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        allPixelsNum += dx;
                    }

                });
                adapterAmount = new AdapterAmount(context, bgitemSelected,bgItem,RecyclerViewHorizontalCustom.this, onClickListener, (int) firstItemWidthNum, view);
                setAdapter(adapterAmount);
                adapterAmount.setItems(amountList);

                // adapterUnit.setSelectFirstItem(0);
                return true;
            }
        });


    }


    public AdapterAmount getAdapterAmount() {
        return adapterAmount;
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);


    }

    int posSelected = -1;

    public void scrollToPosAmount(int pos) {

        if (pos != -1 && pos != posSelected) {
            posSelected = pos;
            if (amountList.size() > pos && pos >= 0) {
                adapterAmount.setSelecteditem(pos);
                scrollToPos(pos);
                // smoothScrollToPosition(pos);
            }
        } else {
            //recyclerView.smoothScrollToPosition(0);
        }

    }

    public void scrollToPos(int pos) {
       /* float targetScrollPosDate = pos * itemWidthNum + firstItemWidthNum - paddingNum;
        float missingPxDate = targetScrollPosDate - allPixelsNum;
        if (Math.round(missingPxDate) != 0)
            smoothScrollBy((int) missingPxDate, 0);*/
        ((LinearLayoutManager) this.getLayoutManager()).scrollToPositionWithOffset(pos, (int) paddingNum);

    }

    public void scrollToPosAmount(int pos, RecyclerView recyclerView) {
        float targetScrollPosDate = pos * itemWidthNum + firstItemWidthNum - paddingNum;
        float missingPxDate = targetScrollPosDate - allPixelsNum;
        if (Math.round(missingPxDate) != 0)
            recyclerView.smoothScrollBy((int) missingPxDate, 0);
    }

    public void scrollToX(final int pos) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothScrollToPosition(pos);
            }
        }, 200);

    }

    public void setAllPixels(int allPixels) {
        this.allPixelsNum = allPixels;
    }


    @Override
    public void ClickItem(int positionItem) {
        float targetScrollPosDate = positionItem * itemWidthNum + firstItemWidthNum - paddingNum;
        float missingPxDate = targetScrollPosDate - allPixelsNum;
        if (Math.round(missingPxDate) != 0)
            smoothScrollBy((int) missingPxDate, 0);
        //scrollToPosAmount(positionItem);
    }
}
