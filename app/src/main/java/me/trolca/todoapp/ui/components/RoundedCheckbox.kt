package me.trolca.todoapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.trolca.todoapp.ui.theme.CheckboxColorsDefault


@Composable
fun RoundCheckBox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier, colors: CheckboxColors = CheckboxColorsDefault){

    val checkColor by animateColorAsState(
        if(checked) colors.checkedCheckmarkColor else colors.uncheckedCheckmarkColor,
        label = "checkColor"
    )

    val backgroundColor by animateColorAsState(
        if(checked) colors.checkedBoxColor else colors.uncheckedBoxColor,
        label = "backgroundColor"
    )

    val borderColor by animateColorAsState(
        if(checked) colors.checkedBorderColor else colors.uncheckedBorderColor,
        label = "borderColor"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .clickable { onCheckedChange.invoke(!checked) }
            .drawBehind { drawRect(backgroundColor) }
            .border(2.dp, borderColor, RoundedCornerShape(50))
    ){

        Icon(
            Icons.Rounded.Check,
            "check",
            tint = checkColor,
            modifier = Modifier.fillMaxSize()
        )



    }

}
