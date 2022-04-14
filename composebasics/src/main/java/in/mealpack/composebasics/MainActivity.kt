package `in`.mealpack.composebasics

import `in`.mealpack.composebasics.composableelements.ConstraintLayoutInCompose
import `in`.mealpack.composebasics.composableelements.ListInComposable
import `in`.mealpack.composebasics.composableelements.SideEffectsAndEffectHandler
import `in`.mealpack.composebasics.composableelements.SnackbarWithScaffold
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import `in`.mealpack.composebasics.ui.theme.JetpackComposeLearningTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme {
                Navigation()
            }
        }
    }



    @Composable
    fun RowComposable() {
        Row(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .background(Color.Green),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = "Hello")
            Text(text = "World")
        }
    }

    @Composable
    fun ColumnComposable() {
        Column(
            modifier = Modifier
                .height(500.dp)
                .width(500.dp)
                .background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "Hello")
            Text(text = "World")
        }
    }

    @Composable
    fun ModifiersUses() {
        Column(
            modifier = Modifier
                .background(Color.Green)
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello", modifier = Modifier.offset(0.dp, 20.dp))
            Spacer(modifier = Modifier.height(200.dp))
            Text(text = "World")
        }
    }

    @Composable
    fun ImageCard(
        painter: Painter,
        title: String,
        contentDescription: String,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp
        ) {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 400f,
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Text(
                        text = title,
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }
            }


        }
    }

    @Composable
    fun TextStyling() {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 24.sp
                    )
                ) {
                    append("J")
                }
                append("etpack ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 24.sp
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color.Green,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
    }

    @Composable
    fun ColorBox(modifier: Modifier) {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }

        Box(modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat()
                )
            }
        )
    }

    @Composable
    fun ChangeColorColorBox(
        modifier: Modifier,
        changeColor: (Color) -> Unit
    ) {

        Box(modifier = modifier
            .background(Color.Cyan)
            .clickable {
                changeColor(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat()
                    )
                )
            }
        )
    }

    @Composable
    fun TestingColorCards(){
        Column(modifier = Modifier.fillMaxSize()) {

            val color = remember {
                mutableStateOf(Color.Yellow)
            }
            ChangeColorColorBox(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                color.value = it
            }
            Box(modifier = Modifier
                .background(color.value)
                .weight(1f)
                .fillMaxWidth())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        JetpackComposeLearningTheme {
            ImageCard(
                painter = painterResource(id = R.drawable.car),
                title = "Car vroom vroom gooooooooooo!",
                contentDescription = "Car"
            )
        }
    }
}