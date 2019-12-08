package com.assignment.ui.celebritycars

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.assignment.AssignmentApp
import com.assignment.R
import com.assignment.repositoties.AssignmentRepo
import com.assignment.repositoties.AssignmentRepoImpl
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException

class CelebrityCarsRepoImpl : AssignmentRepoImpl(), AssignmentRepo {

    var responseCall: Call<ResponseBody>? = null

    var response = MutableLiveData<Boolean>()


    public fun getCelebrityCars() {
        AssignmentApp.mAppComponent.inject(this)

        responseCall = assignmentService.getCelebritiesCars()

        responseCall?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {

                if (response.isSuccessful) {
                    this@CelebrityCarsRepoImpl.response.value = true
                } else {
                    response.message().let {
                        Toast.makeText(mApp, it, Toast.LENGTH_LONG)
                            .show()
                    }
                    this@CelebrityCarsRepoImpl.response.value = false
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                if (t is UnknownHostException) {
                    Toast.makeText(mApp, mApp.getString(R.string.no_network), Toast.LENGTH_LONG)
                        .show()
                }
                response.value = false
            }
        })

    }

    override fun onCancel() {
        responseCall?.cancel()
    }
}