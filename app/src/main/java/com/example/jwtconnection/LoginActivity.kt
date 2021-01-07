package com.example.jwtconnection

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jwtconnection.models.AuthModel
import com.example.jwtconnection.models.ResponseModel
import com.example.jwtconnection.services.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        GlobalScope.launch {
            withContext(Dispatchers.IO) {


                apiClient.getApiService().login(AuthModel(username = "Test", password = "Test"))
                    .enqueue(object : Callback<ResponseModel> {
                        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                            Log.d("tett", "failure")
                        }

                        override fun onResponse(
                            call: Call<ResponseModel>,
                            response: Response<ResponseModel>
                        ) {

                            if (response.isSuccessful) {
                                val loginResponse = response.body()

                                sessionManager.saveAuthToken(loginResponse!!.jwt)
                                val test = findViewById<TextView>(R.id.test)
                                test.text = loginResponse.jwt
                            } else {
                                Log.d("tett", "reject")
                            }
                        }
                    })
            }
        }

    }
}