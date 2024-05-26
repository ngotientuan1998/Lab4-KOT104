package com.duyle.lab4_kot104_md18306

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class Bai2Activity : ComponentActivity() {
    override fun onBackPressed() {

        val data = intent
        data.putExtra(KEY_NHANVIEN_MODEL, "data tu man hinh 2")

        setResult(RESULT_OK, data)

        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val username = intent.getStringExtra(KEY_USERNAME)
        val nhanvienModel = intent.getSerializableExtra(KEY_NHANVIEN_MODEL) as NhanvienModel

        setContent {

            Page2(nhanvienModel, rememberNavController())
        }
    }
}

@Composable
fun Page2(nhanvienModel: NhanvienModel, navController: NavHostController) {

    val listImg = listOf(R.drawable.images1, R.drawable.images2, R.drawable.images3)

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Icon(
            painter = painterResource(id =
            R.drawable.ic_launcher_foreground),
            contentDescription = "Logo"
        )

        Text(text = nhanvienModel.username,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable {

                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "msg",
                        "data tu man hinh 2"
                    )
                    navController.popBackStack()
                },)

        HorizontalImageList(imageList = listImg)
        VerticalImageList(imageList = listImg)
    }
}

@Composable
fun HorizontalImageList(imageList: List<Int>) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .horizontalScroll(scrollState)
        .padding(16.dp)) {
        imageList.forEachIndexed { index, image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image Description",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(
                        start = if (index == 0) 0.dp else 8.dp,
                        end = 8.dp
                    )
            )
        }
    }
}

@Composable
fun VerticalImageList(imageList: List<Int>) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(16.dp)) {
        imageList.forEachIndexed { index, image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image Description",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(
                        top = if (index == 0) 0.dp else 8.dp,
                        bottom = 8.dp
                    )
            )
        }
    }
}