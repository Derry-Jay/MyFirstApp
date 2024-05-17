package com.example.myfirstapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.myfirstapp.models.Item
import com.example.myfirstapp.adapters.ItemsAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentFourthBinding
import com.example.myfirstapp.extensions.apiLink
import com.example.myfirstapp.extensions.retrofitInstance
import com.example.myfirstapp.extensions.showSnackBar
import com.example.myfirstapp.extensions.stringFromResource
import com.example.myfirstapp.models.ItemBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FourthFragment : Fragment(){

    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = binding.recyclerview

        binding.textviewSecond.text = "Items"

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        var data: ArrayList<Item>
        try {
            val call = view.stringFromResource(R.string.base_url_2).retrofitInstance.apiLink.getItems()
            call.enqueue(object: Callback<ItemBase>{
                override fun onResponse(call: Call<ItemBase>, response: Response<ItemBase>) {
                    data = (response.body()?.data as ArrayList<Item>?) ?:  ArrayList()

                    recyclerview.adapter = ItemsAdapter(data)

                    return view.refreshDrawableState()
                }

                override fun onFailure(call: Call<ItemBase>, t: Throwable) {
                    return view.showSnackBar(t.message ?: "", 2, t.localizedMessage ?: "", null,null)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}