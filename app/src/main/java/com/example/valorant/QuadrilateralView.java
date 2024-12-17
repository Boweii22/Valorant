package com.example.valorant;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class QuadrilateralView extends View {

    private Paint paint;
    private Random random;
    private Path path;

    public QuadrilateralView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuadrilateralView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF6200EE); // Purple color
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        random = new Random();
        path = new Path();
    }

    private void generateRandomQuadrilateral() {
        path.reset();
        int width = getWidth();
        int height = getHeight();

        // Randomly pick a shape type
        int shapeType = random.nextInt(5); // 0: Square, 1: Rectangle, 2: Parallelogram, 3: Trapezoid, 4: Rhombus

        switch (shapeType) {
            case 0: // Square
                generateSquare(width, height);
                break;
            case 1: // Rectangle
                generateRectangle(width, height);
                break;
            case 2: // Parallelogram
                generateParallelogram(width, height);
                break;
            case 3: // Trapezoid
                generateTrapezoid(width, height);
                break;
            case 4: // Rhombus
                generateRhombus(width, height);
                break;
        }
    }

    private void generateSquare(int width, int height) {
        int size = random.nextInt(width / 4) + 100; // Size of the square
        int left = random.nextInt(width - size);
        int top = random.nextInt(height - size);

        path.moveTo(left, top);
        path.lineTo(left + size, top); // Top-right
        path.lineTo(left + size, top + size); // Bottom-right
        path.lineTo(left, top + size); // Bottom-left
        path.close();
    }

    private void generateRectangle(int width, int height) {
        int rectWidth = random.nextInt(width / 3) + 100;
        int rectHeight = random.nextInt(height / 3) + 100;

        int left = random.nextInt(width - rectWidth);
        int top = random.nextInt(height - rectHeight);

        path.moveTo(left, top);
        path.lineTo(left + rectWidth, top); // Top-right
        path.lineTo(left + rectWidth, top + rectHeight); // Bottom-right
        path.lineTo(left, top + rectHeight); // Bottom-left
        path.close();
    }

    private void generateParallelogram(int width, int height) {
        int base = random.nextInt(width / 3) + 100;
        int heightOffset = random.nextInt(height / 3) + 100;
        int slant = random.nextInt(base / 2); // Slant offset

        int left = random.nextInt(width - base - slant);
        int top = random.nextInt(height - heightOffset);

        path.moveTo(left, top);
        path.lineTo(left + base, top); // Top-right
        path.lineTo(left + base - slant, top + heightOffset); // Bottom-right
        path.lineTo(left - slant, top + heightOffset); // Bottom-left
        path.close();
    }

    private void generateTrapezoid(int width, int height) {
        int topBase = random.nextInt(width / 3) + 100;
        int bottomBase = random.nextInt(width / 2) + topBase; // Bottom base wider than top base
        int heightOffset = random.nextInt(height / 3) + 100;

        int left = random.nextInt(width - bottomBase);
        int top = random.nextInt(height - heightOffset);

        path.moveTo(left + (bottomBase - topBase) / 2, top); // Top-left
        path.lineTo(left + (bottomBase - topBase) / 2 + topBase, top); // Top-right
        path.lineTo(left + bottomBase, top + heightOffset); // Bottom-right
        path.lineTo(left, top + heightOffset); // Bottom-left
        path.close();
    }

    private void generateRhombus(int width, int height) {
        int diagonal1 = random.nextInt(width / 3) + 100;
        int diagonal2 = random.nextInt(height / 3) + 100;

        int centerX = random.nextInt(width - diagonal1);
        int centerY = random.nextInt(height - diagonal2);

        path.moveTo(centerX, centerY - diagonal2 / 2); // Top
        path.lineTo(centerX + diagonal1 / 2, centerY); // Right
        path.lineTo(centerX, centerY + diagonal2 / 2); // Bottom
        path.lineTo(centerX - diagonal1 / 2, centerY); // Left
        path.close();
    }

    public void redrawQuadrilateral() {
        generateRandomQuadrilateral();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }
}
