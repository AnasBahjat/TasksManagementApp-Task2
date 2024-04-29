import android.content.Context
import android.util.AttributeSet
import androidx.gridlayout.widget.GridLayout

class DynamicGridLayout(context: Context, attrs: AttributeSet) : GridLayout(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val childCount = childCount
        val columnCount = columnCount
        val totalWidth = measuredWidth - paddingLeft - paddingRight
        val columnWidth = totalWidth / columnCount

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val params = child.layoutParams as GridLayout.LayoutParams
            params.width = columnWidth
            child.layoutParams = params
        }
    }
}
