package com.rockex6.app.foodsslots

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.rockex6.app.foodsslots.adapter.FirstTextAdapter
import com.rockex6.app.foodsslots.adapter.ListAdapter
import com.rockex6.app.foodsslots.adapter.ListContentAdapter
import com.rockex6.app.foodsslots.databinding.ActivityMainBinding
import com.rockex6.app.foodsslots.db.ListDataBase
import com.rockex6.app.foodsslots.db.Table
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private val testString = ArrayList<String>()
    private val adapters = ArrayList<FirstTextAdapter>()
    private val recyclerViews = ArrayList<RecyclerView>()
    private var nextRollPosition = ArrayList<Int>() //每一個可以轉動的size
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private var currentTable: Table.ListName? = null
    private var currentListContent: List<Table.ListContent> = listOf()
    private var rollResult: MutableList<String> = mutableListOf("", "", "", "", "", "")
    private val fillStrings = ArrayList<String>()
    private val isRollStopMap = HashMap<Int, Boolean>()
    private var needToStopPosition = 0 //從第一個開始停

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initTestDefaultData()
        initListener()
        getListFromDB()
    }

    private fun init() {
        for (i in 0..5) {
            val recyclerView = RecyclerView(this)
            binding.vSlots.addView(recyclerView)
            recyclerViews.add(recyclerView)
            adapters.add(FirstTextAdapter(i))
        }

        for (i in 0 until recyclerViews.size) {
            recyclerViews[i].addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (isAllStop()) {
                        clearTempData()
                        return
                    }
                    isRollStopMap[i]?.let {
                        if (it) return
                    }
                    if (nextRollPosition.size == 0) return
                    when (newState) {
                        SCROLL_STATE_IDLE -> {
                            recyclerView.adapter?.let {
                                it as FirstTextAdapter
                                for (targetPosition in 0 until it.getUserData().size) {
                                    if (rollResult[needToStopPosition] != "" && it.getUserData()[targetPosition] == rollResult[needToStopPosition] && needToStopPosition == i) {
                                        rollResult[needToStopPosition] = ""
                                        Handler(Looper.myLooper()!!).postDelayed({
                                            Log.d(TAG, "STOP POSITION : $i")
                                            isRollStopMap[i] = true
                                            recyclerView.smoothScrollToPosition(targetPosition)
                                        }, 10)
                                        if (needToStopPosition < 5) needToStopPosition++
                                        return@onScrollStateChanged
                                    }
                                }
                            }
                            if (nextRollPosition[i] >= currentListContent.size) {
                                nextRollPosition[i] = 1
                                recyclerView.scrollToPosition(0)
                                recyclerView.smoothScrollToPosition(nextRollPosition[i])
                            } else {
                                nextRollPosition[i]++
                                recyclerView.smoothScrollToPosition(nextRollPosition[i])
                            }
                        }
                    }
                }
            })

            recyclerViews[i].apply {
                layoutManager =
                    SpeedyLinearLayoutManager(
                        this@MainActivity, LinearLayoutManager.VERTICAL,
                        false
                    )
                adapter = adapters[i]
            }
        }
    }

    private fun initListener() {
        binding.blocking.setOnClickListener { }
        binding.vRoll.setOnClickListener {
            if (currentListContent.size <= 1) {
                showDialog(getString(R.string.notice), "內容小於一筆不能轉哦！")
                return@setOnClickListener
            }
            binding.vRoll.isEnabled = false
            setRollText()
            if (nextRollPosition.size == 0) return@setOnClickListener
            for (i in recyclerViews.size - 1 downTo 0) {
                Handler(Looper.myLooper()!!).postDelayed({
                    recyclerViews[i].scrollToPosition(0)
                    recyclerViews[i].smoothScrollToPosition(nextRollPosition[i])
                }, (i * 200).toLong())
                if (i == 0) {
                    Handler(Looper.myLooper()!!).postDelayed({
                        getRollResult()
                    }, (recyclerViews.size * 1000).toLong())
                }
            }
        }

        binding.vAddNewList.setOnClickListener {
            showDialog("新增表單", "表單名稱") {
                if (it.text.toString() != "") {
                    addNewListToDB(it.text.toString())
                }
            }
        }
        binding.vListName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedList = p1?.tag

                selectedList?.let {
                    currentTable = it as Table.ListName
                    getListContent(it)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.vDeleteList.setOnClickListener {
            showDialog(getString(R.string.notice), getString(R.string.confirm_delete)) {
                currentTable?.let {
                    executorService.execute {
                        ListDataBase.getDataBase(this).getListDao().deleteList(it.id)
                        ListDataBase.getDataBase(this).getListContentDao()
                            .deleteContentWhenListDelete(it.id)
                        getListFromDB()
                    }
                }
            }
        }
        binding.vAddNewListContent.setOnClickListener(addNewListContent)
        binding.vEmptyView.setOnClickListener(addNewListContent)
    }

    private val addNewListContent = View.OnClickListener {
        showDialog("新增食物", "食物名稱") {
            if (it.text.toString().trim().length in 1..6) {
                addNewListContent(it.text.toString())
            } else {
                showDialog(getString(R.string.notice), "不能超過六個字哦~")
            }
        }
    }

    private fun setRollText() {
        for (a in 0 until adapters.size) {
            adapters[a].clearData()
            nextRollPosition.add(0)
        }
        for (element in currentListContent) {
            val fillString = fillTextLengthToSix(element.content_name)
            this.fillStrings.add(fillString)
            for (a in 0 until adapters.size) {
                adapters[a].getUserData().add(fillString[a].toString())
                adapters[a].notifyDataSetChanged()
            }
        }
    }

    private fun getRollResult() {
        val random = (fillStrings.indices).random()
        val result = fillStrings[random]
        Log.d(TAG, result)
        for (i in result.indices) {
            rollResult[i] = result[i].toString()
        }
    }

    private fun initTestDefaultData() {
//        executorService.execute {
//            val item = Table.ListContent("17f23bc2-73d6-48bd-9f05-cba8959e6022", "第一個貢丸湯")
//            val item1 = Table.ListContent("17f23bc2-73d6-48bd-9f05-cba8959e6022", "21世紀")
//            ListDataBase.getDataBase(this).getListContentDao().insertNewItem(item)
//            ListDataBase.getDataBase(this).getListContentDao().insertNewItem(item1)
//            ListDataBase.getDataBase(this).getListContentDao()
//                .getListContent("383780f7-a589-4c4e-8021-c8e921901dc0")
//        }


//        for (a in 0 until adapters.size) {
//            for (i in 0 until testString.size) {
//                val fillString = fillTextLengthToSix(testString[i])
//                adapters[a].getUserData().add(fillString[a].toString())
//            }
//        }
    }

    private fun fillTextLengthToSix(text: String): String {
        var result = "\t"
        if (text.length < 6) {
            result += text
        } else {
            return text
        }
        return fillTextLengthToSix(result)
    }

    private fun addNewListToDB(newName: String) {
        executorService.execute {
            val newListName = Table.ListName(newName)
            ListDataBase.getDataBase(this).getListDao().insertNewList(newListName)
            getListFromDB()
        }
    }

    private fun getListFromDB() {
        executorService.execute {
            val list = ListDataBase.getDataBase(this).getListDao().getList()
            Log.d(TAG, "List : ${list.toString()}")
            runOnUiThread {
                binding.vListName.adapter =
                    ListAdapter(this, list) {
                        vSlots.layoutParams.height = it
                    }
            }
        }
    }

    private fun addNewListContent(newContentName: String) {
        executorService.execute {
            currentTable?.let {
                val newContent = Table.ListContent(it.id, newContentName)
                ListDataBase.getDataBase(this).getListContentDao().insertNewItem(newContent)
                getListContent(it)
            }
        }
    }

    private fun isAllStop(): Boolean {
        var isStop = false
        for (i in recyclerViews.indices) {
            isStop = isRollStopMap[i] == true
        }
        return isStop
    }

    private fun clearTempData() {
        fillStrings.clear()
        isRollStopMap.clear()
        nextRollPosition.clear()
        needToStopPosition = 0
        binding.vRoll.isEnabled = true
    }

    private fun getListContent(currentList: Table.ListName) {
        executorService.execute {
            currentListContent =
                ListDataBase.getDataBase(this).getListContentDao().getListContent(currentList.id)
            Log.d(TAG, "Current List ID : ${currentList.id}")
            Log.d(TAG, "List Content : ${currentListContent.toString()}")
            runOnUiThread {
                if (currentListContent.isEmpty()) {
                    binding.vEmptyView.visibility = View.VISIBLE
                    binding.vListContent.visibility = View.GONE
                    binding.vAddNewListContent.visibility = View.GONE
                } else {
                    binding.vAddNewListContent.visibility = View.VISIBLE
                    binding.vEmptyView.visibility = View.GONE
                    binding.vListContent.visibility = View.VISIBLE
                    binding.vListContent.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = ListContentAdapter(currentListContent) { content ->
                            showDialog(
                                getString(R.string.notice),
                                getString(R.string.confirm_delete),
                                content.content_name
                            ) {
                                executorService.execute {
                                    ListDataBase.getDataBase(this@MainActivity).getListContentDao()
                                        .deleteContent(content.id)
                                    getListFromDB()
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun showDialog(
        title: String,
        message: String,
    ) {
        showDialog(title, message, " ", null)
    }

    private fun showDialog(
        title: String,
        message: String,
        customFunction: (EditText) -> Unit?
    ) {
        showDialog(title, message, "", customFunction)
    }

    private fun showDialog(
        title: String,
        message: String,
        deleteItem: String,
        customFunction: ((EditText) -> Unit?)?
    ) {
        val dialog = Dialog(this, R.style.customDialog)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setContentView(R.layout.dialog_content_view)
        val vSubmit = dialog.findViewById<TextView>(R.id.vSubmit)
        val vNewListName = dialog.findViewById<EditText>(R.id.vName)
        val vTitle = dialog.findViewById<TextView>(R.id.vTitle)
        val vMessage = dialog.findViewById<TextView>(R.id.vMessage)
        val vCancel = dialog.findViewById<TextView>(R.id.vCancel)
        val vDeleteItem = dialog.findViewById<TextView>(R.id.vDeleteItem)
        if (deleteItem.isNotEmpty()) {
            vNewListName.visibility = View.INVISIBLE
            vDeleteItem.visibility = View.VISIBLE
            vDeleteItem.text = deleteItem
        } else {
            vNewListName.visibility = View.VISIBLE
            vDeleteItem.visibility = View.INVISIBLE
        }
        vCancel.setOnClickListener {
            dialog.dismiss()
        }
        vTitle.text = title
        vMessage.text = message
        vSubmit.setOnClickListener {
            if (customFunction != null) {
                customFunction(vNewListName)
            }
            dialog.dismiss()
        }
        if (!isFinishing && !isDestroyed) dialog.show()
    }
}
