package `in`.mealpack.composebasics

import java.lang.StringBuilder

sealed class Screen(val route:String){
    object MainScreen:Screen("main_screen")
    object DetailScreen:Screen("detail_screen")

    fun setArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {arg->
               append("/$arg")
            }
        }
    }
}
