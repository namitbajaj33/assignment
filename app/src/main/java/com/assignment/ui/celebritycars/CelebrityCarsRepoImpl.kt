package com.assignment.ui.celebritycars

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.assignment.R
import com.assignment.model.CelebrityCar
import com.assignment.model.CelebrityCarContainer
import com.assignment.repositoties.AssignmentRepo
import com.assignment.repositoties.AssignmentRepoImpl
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException


class CelebrityCarsRepoImpl : AssignmentRepoImpl(), AssignmentRepo {

    var responseCall: Call<Any>? = null

    var response = MutableLiveData<ArrayList<CelebrityCarContainer>>()

    fun getCelebrityCars() {
        responseCall = assignmentService.getCelebritiesCars()

        responseCall?.enqueue(object : Callback<Any> {
            override fun onResponse(
                call: Call<Any>,
                response: Response<Any>
            ) {

                val result = ArrayList<CelebrityCarContainer>()

                if (response.isSuccessful) {

                    val successResponse = mGson.toJson(response.body())

                    val successJson=JSONObject(successResponse)

                    parseCelebs(successJson, result)

                    parseCars(successJson, result)

                    this@CelebrityCarsRepoImpl.response.value = result

                } else {
                    response.message().let {
                        Toast.makeText(mApp, it, Toast.LENGTH_LONG)
                            .show()
                    }
                    this@CelebrityCarsRepoImpl.response.value = ArrayList()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                if (t is UnknownHostException) {
                    Toast.makeText(mApp, mApp.getString(R.string.no_network), Toast.LENGTH_LONG)
                        .show()
                }
                response.value = ArrayList()
            }
        })

    }

    private fun parseCelebs(
        successJson: JSONObject,
        result: ArrayList<CelebrityCarContainer>
    ) {

        result.add(
            CelebrityCarContainer(
                "Celebrities",
                getHeaderObject(),
                CelebrityCarContainer.HEADER
            )
        )

        val celebritiesObject = successJson.getJSONObject("celebrities")

        val celebKeys = celebritiesObject.keys()

        celebKeys.forEach {
            val tempCeleb = mGson.fromJson(
                celebritiesObject.getJSONObject(it).toString(),
                CelebrityCar::class.java
            )
            result.add(
                CelebrityCarContainer(
                    it, tempCeleb,
                    CelebrityCarContainer.CELEBRITY
                )
            )
        }
    }

    private fun parseCars(
        successJson: JSONObject,
        result: ArrayList<CelebrityCarContainer>
    ) {
        val carsObject = successJson.getJSONObject("cars")

        result.add(
            CelebrityCarContainer(
                "Cars",
                getHeaderObject(),
                CelebrityCarContainer.HEADER
            )
        )

        val carKeys = carsObject.keys()

        carKeys.forEach {
            val tempCar = mGson.fromJson(
                carsObject.getJSONObject(it).toString(),
                CelebrityCar::class.java
            )
            result.add(
                CelebrityCarContainer(
                    it, tempCar,
                    CelebrityCarContainer.CAR
                )
            )
        }
    }

    private fun getHeaderObject(): CelebrityCar {
        return CelebrityCar(0, "", "", 0)
    }

    override fun onCancel() {
        responseCall?.cancel()
    }
}