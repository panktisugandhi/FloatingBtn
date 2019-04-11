package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CanvasView extends View {

    Context context;
    public ViewGroup.LayoutParams params;
    private Path path = new Path();
    private Paint brush = new Paint();

    public CanvasView(Context context,AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        brush.setAntiAlias(true);
        brush.setColor(Color.BLACK);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(15f);

        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

    }
    public boolean onTouchEvent(MotionEvent event) {
        float pointx = event.getX();
        float pointy = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointx,pointy);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointx,pointy);
                break;
            default:

                return false;
        }
        postInvalidate();
        return false;
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawPath(path,brush);
    }

    public void clearcanvas (){
        path.reset();
        invalidate();
    }


}
