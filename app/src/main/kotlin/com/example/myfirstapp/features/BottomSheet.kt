package com.example.myfirstapp.features

//import com.example.myfirstapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfirstapp.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = BottomSheetBinding.inflate(inflater, container, false)

        arguments?.let {
            it.apply {
                with(binding) {
                    title.text = getString("title")
                    desc.text = getString("desc")
                    price.text = getString("price")
                    rating.text = getString("rating")
                    stock.text = getString("stock")
                }

            }

        }


        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        binding.bottomSheet.minimumHeight = 600
        behavior.isDraggable = false
        return binding.root

    }

//    override fun getTheme(): Int {
//        return R.style.NoBackgroundDialogTheme
//    }

}