package com.example.hoangcv2_task_algorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.hoangcv2_task_algorithm.databinding.ActivityMainBinding
import kotlin.math.min

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResult.setOnClickListener(this)
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

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btnResult ->{
                binding.txtResult.text=solution(Integer.parseInt(binding.edtN.text.toString()),Integer.parseInt(binding.edtK.text.toString())).toString()
            }


        }
    }
}