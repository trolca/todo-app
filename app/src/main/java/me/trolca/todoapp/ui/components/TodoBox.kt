package me.trolca.todoapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import me.trolca.todoapp.ui.theme.PurpleGrey80
import org.jetbrains.annotations.ApiStatus.Internal


@Composable
fun TodoBox(modifier: Modifier = Modifier, text: String, checked: Boolean = false, onChangeCallback: ((Boolean) -> Unit)? = null){
    var isDone by rememberSaveable { mutableStateOf(checked) }
    val config =  LocalConfiguration.current
    var width = rememberSaveable { mutableIntStateOf(config.screenWidthDp)}
    var lines = (text.length.dp / (width.intValue-20).dp) + 1

    val color by animateColorAsState(
        if(isDone) Color.Gray else PurpleGrey80,
        label = "color"
    )


    val onChange: (Boolean) -> Unit = {
        isDone = it
        onChangeCallback?.invoke(it)
    }


    InternalTodoBox(text, isDone, onCheckboxChange = onChange,
        modifier =  modifier.size(width.intValue.dp, (lines * 85).dp)
            .clip(RoundedCornerShape(15.dp)).drawBehind { drawRect(color) })
}
/*
isDone, onCheckedChange = onCheckboxChange,
            colors = CheckboxColorsDefault,
            modifier = Modifier.align(Alignment.CenterVertically)
 */

@Composable
@Internal
fun InternalTodoBox(
    todoName: String,
    isDone: Boolean,
    onCheckboxChange: (Boolean) -> Unit,
    modifier: Modifier
    ) {

    val focusManager = LocalFocusManager.current

    Row(modifier.clickable(onClick = {
        focusManager.clearFocus()
        onCheckboxChange.invoke(!isDone)
    }), horizontalArrangement = Arrangement.End) {
        Text(
            todoName,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = TextUnit(5f, TextUnitType.Em),
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.CenterVertically).fillMaxWidth(0.90f)
        )

        RoundCheckBox(
            modifier = Modifier.align(Alignment.CenterVertically)
                .size(20.dp),
            checked = isDone,
            onCheckedChange = onCheckboxChange
        )

    }
}
