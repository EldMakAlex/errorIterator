package ua.test.testerror

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class Test(val view : MainActivity) {

    private fun getJSArray(): JSONArray {
        val testJS = JSONArray()
        val rowObject1 = JSONObject()
        rowObject1.put("tst1", "12")
        rowObject1.put("tst2", "2")
        rowObject1.put("tst3", true)
        rowObject1.put("tst4", 23f)
        testJS.put(rowObject1)
        val rowObject2 = JSONObject()
        rowObject2.put("tst1", "11")
        rowObject2.put("tst2", "12")
        rowObject2.put("tst3", false)
        rowObject2.put("tst4", 3f)
        testJS.put(rowObject2)

        return testJS
    }


    fun getTaskQtyInfo(){
        launchOnUI {
            val result = getJSArray()

            for(row in result){
                val resultString = " ${row.getStrToInt("tst1")}, ${row.getStrToInt("tst2")}, ${row.getBoolean("tst3")}/${row.getDouble("tst4")}"
                view.test.text = resultString
            }

        }
    }
}