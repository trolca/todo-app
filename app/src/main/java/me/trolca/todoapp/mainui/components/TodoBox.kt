package me.trolca.todoapp.mainui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.trolca.todoapp.ui.theme.CheckboxColorsDefault
import me.trolca.todoapp.ui.theme.PurpleGrey80
import org.jetbrains.annotations.ApiStatus.Internal


@Composable
fun TodoBox(text : String){
    var isDone by rememberSaveable { mutableStateOf(false) }
    val config =  LocalConfiguration.current
    var width = rememberSaveable { mutableIntStateOf(config.screenWidthDp)}
    var lines = (text.length.dp / (width.intValue-20).dp) + 1

    var color: Color by remember { mutableStateOf(PurpleGrey80) }


    val onChange: (Boolean) -> Unit = {
        isDone = it
        color = if(isDone)
            Color.Gray
        else
            PurpleGrey80
    }


    InternalTodoBox(text, isDone, onCheckboxChange = onChange, backgroundColor = color,
        modifier =  Modifier.size(width.intValue.dp, (lines * 85).dp)
            .clip(RoundedCornerShape(15.dp)))
}

@Composable
@Internal
fun InternalTodoBox(
    todoName: String,
    isDone: Boolean,
    backgroundColor: Color,
    onCheckboxChange: (Boolean) -> Unit,
    modifier: Modifier
    ) {

    Row(modifier.background(backgroundColor), horizontalArrangement = Arrangement.End) {
        Text(
            todoName,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = TextUnit(5f, TextUnitType.Em),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically).fillMaxWidth(0.9f)
        )

        Checkbox(
            isDone, onCheckedChange = onCheckboxChange,
            colors = CheckboxColorsDefault,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

    }
}
