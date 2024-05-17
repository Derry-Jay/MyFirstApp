package com.example.myfirstapp.fragments


import com.example.myfirstapp.adapters.CustomAdapter
import com.example.myfirstapp.models.ListItem
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.net.wifi.WifiManager
//import android.text.format.Formatter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.R
import com.example.myfirstapp.data.isEmulator
import com.example.myfirstapp.databinding.FragmentThirdBinding
import com.example.myfirstapp.extensions.assetFiles
import com.example.myfirstapp.extensions.firstLetterCapitalized
import com.example.myfirstapp.extensions.navCon
import java.io.InputStream


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = binding.recyclerview

        binding.appbar.tooltipText = "Third Fragment"

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ListItem>()

        val lth = view.assetFiles("imgs")?.size ?: 0

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..lth) {
            // get image path
            val pic: String = view.assetFiles("imgs")?.get(i - 1) ?: ""

            val img = "imgs/$pic"

            val nl = pic.split("-", ".").subList(0, 2)
                .joinToString(" ") { it.firstLetterCapitalized }

            try {
                // get input stream
                val ims: InputStream? = context?.assets?.open(img)
                // load image as Drawable
                val d = Drawable.createFromStream(ims, null)
                    ?: Drawable.createFromPath("assets/icon/loader.gif")
                // set image to ImageView
                ims?.close()
                if (d != null) {
                    data.add(ListItem(d, nl))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        binding.floating.setOnClickListener {
            view.navCon.navigate(R.id.action_ThirdFragment_to_fourthFragment)
        }

        binding.appBarImage.setOnClickListener {
//            val wifiManager = view.context.getSystemService(Context.WIFI_SERVICE) as WifiManager
//            for (ScanResult result in wifiManager.scanResults){
            println(if (isEmulator) "Bye" else "Hi")
            view.navCon.navigate(R.id.action_ThirdFragment_to_fourthFragment)
//        }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}