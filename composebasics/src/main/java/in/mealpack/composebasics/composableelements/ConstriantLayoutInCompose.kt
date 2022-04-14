package `in`.mealpack.composebasics.composableelements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


// Following Dependency is needed for Constraint Layout in Compose
// implementation "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
@Composable
fun ConstraintLayoutInCompose(){
    val constraints = ConstraintSet {
        val greenBox = createRefFor("GreenBox")
        val redBox = createRefFor("RedBox")
        val guideline1 = createGuidelineFromTop(0.5f)

        constrain(greenBox){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(200.dp)
        }
        constrain(redBox){
            top.linkTo(guideline1)
            start.linkTo(greenBox.start)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(200.dp)
        }
        createHorizontalChain(greenBox,redBox,chainStyle = ChainStyle.Packed)
    }
    ConstraintLayout(constraints,modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("GreenBox"))
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("RedBox"))

    }
}
