package com.example.myfirstapp.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.myfirstapp.extensions.Extensions
//import com.example.myfirstapp.models.Product
//import com.example.myfirstapp.middleware.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
//    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _productsList: MutableStateFlow<ProductsListState> = MutableStateFlow(
        ProductsListState.Initial
    )
    val productsList: StateFlow<ProductsListState> = _productsList

    suspend fun getList() {
        viewModelScope.launch(Dispatchers.IO) {

//           when( val response = productsUseCase.execute()) {
//               is Extensions.Scuess -> _productsList.value = ProductsListState.Success(response.data as List<Product>)
//               is Extensions.Error -> _productsList.value = ProductsListState.Error("Something went wrong")
//               is Extensions.Initial -> _productsList.value = ProductsListState.Initial
//           }
//            try {
//                val response = RetrofitHelper.retrofit.getProductList()
//                if(response.isSuccessful){
//                    response.body()?.products?.let {
//                        _productsList.value = ProductsListState.Success(it)
//                    }
//                }
//
//            }catch (e : IOException){
//                _productsList.value = ProductsListState.Error("Something went wrong")
//
//            }catch (e : Exception){
//                _productsList.value = ProductsListState.Error("Something went wrong")
//
//            }

        }

    }
}