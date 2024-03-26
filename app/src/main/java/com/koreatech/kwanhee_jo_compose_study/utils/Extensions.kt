package com.koreatech.kwanhee_jo_compose_study.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController

fun showToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun NavHostController.clearStackNavigate(route: String) =
    navigate(route) { popUpTo(graph.id) { inclusive = true } }

fun <VALUE> NavHostController.setArgumentCurrentStack(
    vararg arguments: Map<String, VALUE>,
) = currentBackStackEntry?.savedStateHandle?.apply {
    arguments.forEach { map ->
        for (entry in map.entries) {
            set(entry.key, entry.value)
        }
    }
}