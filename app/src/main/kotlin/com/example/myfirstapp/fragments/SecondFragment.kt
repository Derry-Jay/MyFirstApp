package com.example.myfirstapp.fragments

//import androidx.core.view.doOnPreDraw
//import androidx.navigation.fragment.findNavController
//import com.example.myfirstapp.models.City
//import com.example.myfirstapp.models.CityBase
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.launch
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.R
import com.example.myfirstapp.adapters.ProductsAdapter
import com.example.myfirstapp.databinding.FragmentSecondBinding
import com.example.myfirstapp.extensions.apiLink
import com.example.myfirstapp.extensions.navCon
import com.example.myfirstapp.extensions.retrofitInstance
import com.example.myfirstapp.extensions.showSnackBar
import com.example.myfirstapp.extensions.stringFromResource
import com.example.myfirstapp.models.Product
import com.example.myfirstapp.models.ProductBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class City
        var data: ArrayList<Product>
        try {
            val call =
                view.stringFromResource(R.string.base_url_1).retrofitInstance.apiLink.getProducts()
            call.enqueue(object : Callback<ProductBase> {
                override fun onResponse(
                    call: Call<ProductBase>,
                    response: Response<ProductBase>,
                ) {

                    data = response.body()?.products as ArrayList<Product>

                    recyclerview.adapter = ProductsAdapter(data)

                    return view.refreshDrawableState()
                }

                override fun onFailure(call: Call<ProductBase>, t: Throwable) {
                    return view.showSnackBar(
                        t.message ?: "",
                        2,
                        t.localizedMessage ?: "",
                        null,
                        null
                    )
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

//        val j = GlobalScope.launch {
//            val result = ut.api.getCities()
//            data = result.body()?.cities ?: ArrayList<City>()
//        }
//
//        j.invokeOnCompletion {
//        }

        binding.buttonSecond.setOnClickListener {
            it?.navCon?.navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

//        binding.textviewSecond.text = "Hello"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}