package com.example.mypiano.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mypiano.R;
import com.example.mypiano.model.Key;
import com.example.mypiano.utils.SoundManager;

import java.util.ArrayList;

public class PianoView extends View {

    public static final int NUMBER_OF_KEYS = 14;
    private ArrayList<Key> whites;
    private ArrayList<Key> blacks;

    private int keyWidth, keyHeight;

    Paint blackPen, whitePen, yellowPen;

    private SoundManager soundManager;

    public PianoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        whites = new ArrayList<>();
        blacks = new ArrayList<>();

        whitePen = new Paint();
        whitePen.setColor(Color.WHITE);
        whitePen.setStyle(Paint.Style.FILL);

        blackPen = new Paint();
        blackPen.setColor(Color.BLACK);
        blackPen.setStyle(Paint.Style.FILL);

        yellowPen = new Paint();
        yellowPen.setColor(Color.YELLOW);
        yellowPen.setStyle(Paint.Style.FILL);

        soundManager = SoundManager.getInstance();
        soundManager.init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        keyWidth = w / NUMBER_OF_KEYS;
        keyHeight = h;

        int blackCount = 15;
        //tạo mảng phím trắng
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            int left = i * keyWidth;
            int right = left + keyWidth;

            RectF rect = new RectF(left, 0, right, h);
            whites.add(new Key(i + 1, rect, false));

            //tạo mảng phím đen
            if (i != 0 && i != 3 && i != 7 && i != 10) {
                rect = new RectF((float) (i - 1) * keyWidth + 0.75f * keyWidth,
                        0,
                        (float) i * keyWidth + 0.25f * keyWidth,
                        0.67f * keyHeight);
                blacks.add(new Key(blackCount, rect, false));
                blackCount++;
            }
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for (Key k : whites) {
            canvas.drawRect(k.rect, k.down ? yellowPen : whitePen);
        }

        for (int i = 1; i < NUMBER_OF_KEYS; i++) {
            canvas.drawLine(i * keyWidth, 0, i * keyWidth, keyHeight, blackPen);
        }

        for (Key k : blacks) {
            canvas.drawRect(k.rect, k.down ? yellowPen : blackPen);
        }
    }

    public void releaseKey(final Key key) {
        key.down = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN ||
                action == MotionEvent.ACTION_MOVE;

        for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
            float x = event.getX(touchIndex);
            float y = event.getY(touchIndex);

            boolean isBlackDown = false;

            for (Key k : blacks) {
                if (k.rect.contains(x, y)) {
                    k.down = isDownAction;
                    switch (k.sound) {
                        case 15:
                            soundManager.playSound(R.raw.key02);
                            break;
                        case 16:
                            soundManager.playSound(R.raw.key04);
                            break;
                        case 17:
                            soundManager.playSound(R.raw.key07);
                            break;
                        case 18:
                            soundManager.playSound(R.raw.key09);
                            break;
                        case 19:
                            soundManager.playSound(R.raw.key11);
                            break;
                        case 20:
                            soundManager.playSound(R.raw.key14);
                            break;
                        case 21:
                            soundManager.playSound(R.raw.key16);
                            break;
                        case 22:
                            soundManager.playSound(R.raw.key19);
                            break;
                        case 23:
                            soundManager.playSound(R.raw.key21);
                            break;
                        case 24:
                            soundManager.playSound(R.raw.key23);
                            break;
                    }
                    isBlackDown = true;
                } else {
                    releaseKey(k);
                }
            }

            for (Key k : whites) {
                if (k.rect.contains(x, y) && !isBlackDown) {
                    k.down = isDownAction;
                    switch (k.sound) {
                        case 1:
                            soundManager.playSound(R.raw.key01);
                            break;
                        case 2:
                            soundManager.playSound(R.raw.key03);
                            break;
                        case 3:
                            soundManager.playSound(R.raw.key05);
                            break;
                        case 4:
                            soundManager.playSound(R.raw.key06);
                            break;
                        case 5:
                            soundManager.playSound(R.raw.key08);
                            break;
                        case 6:
                            soundManager.playSound(R.raw.key10);
                            break;
                        case 7:
                            soundManager.playSound(R.raw.key12);
                            break;
                        case 8:
                            soundManager.playSound(R.raw.key13);
                            break;
                        case 9:
                            soundManager.playSound(R.raw.key15);
                            break;
                        case 10:
                            soundManager.playSound(R.raw.key17);
                            break;
                        case 11:
                            soundManager.playSound(R.raw.key18);
                            break;
                        case 12:
                            soundManager.playSound(R.raw.key20);
                            break;
                        case 13:
                            soundManager.playSound(R.raw.key22);
                            break;
                        case 14:
                            soundManager.playSound(R.raw.key24);
                            break;
                    }
                } else {
                    releaseKey(k);
                }
            }
        }
        invalidate();
        return true;
//        return super.onTouchEvent(event);
    }
}
