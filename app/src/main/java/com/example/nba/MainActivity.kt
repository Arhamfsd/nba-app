package com.example.nba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nba.ui.theme.NBATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBATheme {
                Surface(
                ) {
                    nbapreview()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun nbapreview() {
    nbapage()
}

@Composable
fun nbapage() {
    var current by remember { mutableStateOf(0) }
    var commentask by remember {
        mutableStateOf(false)
    }
    var comment by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())

            ,
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

            Text(
                text = "Top Teams in NBA",
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .weight(0.12f) // Minimal weight to keep the text at the top
                    .padding(top = 40.dp) // Adjust this padding as needed

            )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.88f) // Larger weight to center the content
        ) {
            showimage(current)
            Spacer(modifier = Modifier.height(15.dp))
            showtext(current)
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(
                    onClick = { current--
                              if(current==-1)
                              current=2},
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .padding(20.dp)
                        .width(110.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,       // Background color of the button
                        contentColor = Color.White         // Color of the text/content
                    )
                ) {
                    Text(
                        text = "Previous",
                        fontSize = 15.sp

                    )
                }
                Button(
                    onClick = { current++
                                if(current==3)
                                    current=0
                              },
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier
                        .padding(20.dp)
                        .width(110.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,       // Background color of the button
                        contentColor = Color.White         // Color of the text/content
                    )
                ) {
                    Text(
                        text = "Next",
                        fontSize = 20.sp

                    )
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 65.dp),
                horizontalArrangement = Arrangement.End
                )
            {

                ask_comment(choice = commentask, whenclicked = { commentask = it })
            }
            if(commentask==true) {
                take_comment(choice = commentask, comment = comment, { comment = it })
            }
        }
    }
}

@Composable
fun take_comment(choice: Boolean,comment:String,commentfunction:(String)->Unit)
{
    if(choice==true)
    {
        TextField(
            value = comment,
            onValueChange = commentfunction,
            label = {Text("Comment")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done

            ),
            singleLine = false

        )
    }
}


@Composable
fun showtext(current: Int) {
var team= stringResource(id = R.string.bc)
var title= stringResource(id = R.string.bctitle)
    when (current) {
        0 -> {
            team = stringResource(id = R.string.bc)
            title = stringResource(id = R.string.bctitle)
        }
        1 -> {
            team = stringResource(id = R.string.la)
            title = stringResource(id = R.string.latitle)
        }
        2 ->  {
            team = stringResource(id = R.string.gsw)
            title = stringResource(id = R.string.gswtitle)
        }
        else -> {
            print("x is neither 1 nor 2")
        }
    }
    Text(
        text = team,
        color = Color.White,
        fontSize = 35.sp
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp
    )
}

@Composable
fun showimage(current:Int) {
    var team= painterResource(id = R.drawable.bc)
    when (current) {
        0 -> {
            team = painterResource(id = R.drawable.bc)
        }
        1 -> {
            team = painterResource(id = R.drawable.lakers)
        }
        2 ->  team = painterResource(id = R.drawable.gsw)
        else -> {
            print("x is neither 1 nor 2")
        }
    }
    Image(
        painter = team,
        contentDescription = null,
        modifier = Modifier
            .width(310.dp)
            .height(290.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.Yellow),
        contentScale =ContentScale.Crop// Adjust to fit content in the area
        )
}

@Composable
fun ask_comment(choice:Boolean,
                 whenclicked:(Boolean)->Unit)
{
    Switch( modifier = Modifier, checked = choice, onCheckedChange = whenclicked)
}





