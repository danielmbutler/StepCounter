package com.dbtechprojects.stepcounter.ui.screens

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.dbtechprojects.stepcounter.R
import com.dbtechprojects.stepcounter.ui.theme.Teal200


@Composable
fun PermissionDeniedScreen(){
    Scaffold( content = { padding ->
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.home_screen_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_accessibility_24),
                contentDescription = stringResource(R.string.accessibility),
                Modifier.size(120.dp),
            )
            PermissionsMessage(LocalContext.current)
        }
    })
}

@Composable
fun PermissionsMessage(context: Context) {
    Text(
        text = stringResource(R.string.permission_denied_description),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.size(6.dp))
    Text(
        text = "Click here to enable in settings.",
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = Teal200,
        modifier = Modifier.clickable {
            startActivity(context, Intent(Settings.ACTION_SETTINGS), null)
        }
    )


}

