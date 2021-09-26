package com.hritik.bilancio.ui.common


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(items: List<String>, updateSelectedValue: (index: Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxSize()

            .wrapContentSize(Alignment.TopStart)
            ,
    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clickable(onClick = { expanded = true })
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primaryVariant,
                shape = RoundedCornerShape(20.dp))
            .background(
                color = MaterialTheme.colors.background)) {
            Row(
                modifier= Modifier.fillMaxWidth().padding(horizontal = 15.dp ,vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(items[selectedIndex],
                                          color = MaterialTheme.colors.primaryVariant)
                if(expanded){
                    Icon(Icons.Default.KeyboardArrowUp ,"arrow down", tint = MaterialTheme.colors.primaryVariant)
                }else{
                    Icon(Icons.Default.KeyboardArrowDown ,"arrow down",tint = MaterialTheme.colors.primaryVariant)
                }
            }



        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    updateSelectedValue(index)
                    selectedIndex = index
                    expanded = false
                }) {

                    Text(text = s, color = Color.White)
                }
            }
        }
    }
}