package dev.trindadedev.anims.ui.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import dev.trindadedev.anims.R;

public class DragView extends LinearLayout {

  private final Paint paint = new Paint();
  private final Point size = new Point();
  private final Point startPosition = new Point();
  private final Rect region = new Rect();

  private Drawable drawable;
  private DirectionLock lock = DirectionLock.FREE;

  public DragView(final Context context) {
    super(context, null);
    initialize();
  }
  
  public DragView(final Context context, final AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }
  
  private final void initialize() {
    drawable = getContext().getDrawable(R.drawable.drag_view);
  }

  @Override
  protected void onDraw(final Canvas canvas) {
    var position = getPosition();
    if (drawable != null) {
      drawable.setBounds(position.x, position.y, position.x + size.x, position.y + size.y);
      drawable.draw(canvas);
    }
  }

  @Override
  public boolean onTouchEvent(final MotionEvent event) {
    if (!region.contains((int) event.getX(), (int) event.getY())) {
      return super.onTouchEvent(event);
    }

    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      startPosition.x = (int) event.getX();
      startPosition.y = (int) event.getY();

      if (getParent() instanceof ViewGroup) {
        ((ViewGroup) getParent()).bringChildToFront(this);
      }

      // onSelected();
      return true;
    } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
      int x = 0, y = 0;

      if (lock == DirectionLock.FREE || lock == DirectionLock.HORIZONTAL_ONLY) {
        x = (int) event.getX() - startPosition.x;
      }

      if (lock == DirectionLock.FREE || lock == DirectionLock.VERTICAL_ONLY) {
        y = (int) event.getY() - startPosition.y;
      }

      region.offset(x, y);
      startPosition.x = (int) event.getX();
      startPosition.y = (int) event.getY();

      invalidate();
      return true;
    } else {
      return super.onTouchEvent(event);
    }
  }

  public final void setPosition(final Point position) {
    region.set(position.x, position.y, position.x + size.x, position.y + size.y);
  }

  public final Point getPosition() {
    return new Point(region.left, region.top);
  }

  public final void setSize(final int width, final int height) {
    size.x = width;
    size.y = height;
    region.set(region.left, region.top, region.left + width, region.top + height);
  }

  public final Point getSize() {
    return size;
  }
  
  public final void setLock(final DirectionLock lock) {
    this.lock = lock;
  }
  
  public final DirectionLock getLock() {
    return lock;
  }
  
  public enum DirectionLock {
    FREE,
    HORIZONTAL_ONLY,
    VERTICAL_ONLY
  }
}
