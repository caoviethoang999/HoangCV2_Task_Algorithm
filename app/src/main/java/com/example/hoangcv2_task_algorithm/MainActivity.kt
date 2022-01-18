package com.example.hoangcv2_task_algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hoangcv2_task_algorithm.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.min




class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResultTask1.setOnClickListener(this)
        binding.btnResultTask2.setOnClickListener(this)
        binding.btnResultTask3.setOnClickListener(this)
    }

    private fun solution(N: Int, K: Int): Int {
        if (N >= K) return 1
        val sumN = N * (N + 1) / 2
        return if (sumN < K) {
            -1
        } else if (sumN == K) {
            N
        } else {
            var maxCapacity = K
            val usedGlasses: MutableSet<Int> = HashSet()
            while (maxCapacity > 0) {
                val toFill = min(N, maxCapacity)
                for (i in toFill downTo 1) {
                    if (!usedGlasses.contains(i)) {
                        usedGlasses.add(i)
                        maxCapacity -= i
                        break
                    }
                }
            }
            usedGlasses.size
        }
    }

    private fun solution(a: String): Int {

        val a1: ArrayList<String> = ArrayList()

        for (i in a.indices) {
            for (j in i + 1..a.length) {
                if (i != j)
                    a1.add(a.substring(i, j))
            }
        }
        val a2: TreeMap<String, Int> = TreeMap()
        for (s in a1) a2[s] = a2.getOrDefault(s, 0) + 1
        val freshList: ArrayList<String> = ArrayList()
        for (s in a2.keys) {
            if (a2[s] == 1)
                freshList.add(s)
        }
        val dictionary: TreeMap<String, Int> = TreeMap()
        for (s in freshList) {
            dictionary[s] = s.length
        }
        val newList: ArrayList<Int> = ArrayList()
        for (s in dictionary.keys) newList.add(dictionary[s]!!)
        var ans = Int.MAX_VALUE
        for (i in newList) ans = min(ans, i)
        return if (ans == Int.MAX_VALUE) 0 else ans
    }

    private fun solution(a: IntArray, b: IntArray): Boolean {
        var n =0
        if (a.size==b.size){
            n=a.size
        }
        val map = IntArray(n)
        for (i in 0 until n) {
            map[a[i] - 1] = b[i] - 1
        }
        var len = 0
        var curr = 0
        do {
            curr = map[curr]
            len += 1
        } while (curr != 0)
        return len == n
    }


    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btnResultTask1 ->{
                binding.txtResult.text=solution(Integer.parseInt(binding.edtN.text.toString()),Integer.parseInt(binding.edtK.text.toString())).toString()
            }
            R.id.btnResultTask2 ->{
                binding.txtResultTask2.text=solution(binding.edtString.text.toString()).toString()
            }
            R.id.btnResultTask3 ->{
                val a: List<String> = binding.edtArrayOfA.text.toString().split(",")
                val numbersA = IntArray(a.size)
                val b: List<String> = binding.edtArrayOfB.text.toString().split(",")
                val numbersB = IntArray(b.size)
                for (i in a.indices) {
                    numbersA[i] = a[i].toInt()
                }
                for (i in b.indices) {
                    numbersB[i] = b[i].toInt()
                }
                binding.txtResultTask3.text=solution(numbersA,numbersB).toString()
            }


        }
    }
}