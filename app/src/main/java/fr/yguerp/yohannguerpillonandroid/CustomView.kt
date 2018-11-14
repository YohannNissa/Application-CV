package fr.yguerp.yohannguerpillonandroid

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.graphics.Canvas
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.content_realisations.view.*

class CustomView : View, SensorEventListener {

    var paint = Paint()

    lateinit var ballArray: Array<MagicCircle>
    var dX = 0
    var dY = 0

    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attr: AttributeSet?) : this(ctx, attr, 0)

    constructor(ctx: Context, attr: AttributeSet?, styleAttr: Int) : super(ctx, attr, styleAttr) {
        init()
    }

    private fun init() {
        paint.textSize = 25F
        val sensorManager: SensorManager = context.getSystemService(SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (mCircle in ballArray) {
            with(mCircle) {
                move()
                paint.color = color
                canvas?.drawCircle(cx, cy, rad, paint)
            }
        }
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        ballArray = Array(10) { MagicCircle(width, height) }
    }

    override fun onAccuracyChanged(sensor: Sensor?, newAccuracy: Int) {
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        if (sensorEvent?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            dX = sensorEvent.values[0].toInt()
            dY = sensorEvent.values[1].toInt()
        }
    }

}