package ua.test.testerror

import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import org.json.JSONArray
import org.json.JSONObject

fun launchOnUI(f: suspend CoroutineScope.() -> Unit) : Job = launch(UI, block = f)

fun launchOn (f: suspend CoroutineScope.() -> Unit) : Job = launch(CommonPool, block = f)

operator fun JSONArray.iterator(): Iterator<JSONObject>
        = (0 until length())
        .asSequence()
        .map { get(it) as JSONObject }.iterator()

@Throws
fun JSONObject.getStrToInt(name : String) : Int{

    val obj = get(name)
    val rInt : Int

    when (obj){
        is Int -> return obj
        is Number -> return obj.toInt()
        is String -> {
            rInt = try {
                obj.replace(',','.').toFloat().toInt()
            } catch(ex : Exception) {
                try{
                    obj.replace('.',',').toFloat().toInt()
                } catch(ex : Exception){
                    throw TypeCastException()
                }
            }
            return rInt
        }
    }
    throw TypeCastException()
}