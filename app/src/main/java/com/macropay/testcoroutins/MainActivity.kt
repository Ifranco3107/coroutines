package com.macropay.testcoroutins

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
class MainActivity : AppCompatActivity() {
//Para ejemplo[2], con el ejemplo [3], ya no se necesita.
/*
class MainActivity : AppCompatActivity(),CoroutineScope {
 override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main +job

    private lateinit var job:Job*/

private lateinit var vm: MainViewModel

   // https://www.youtube.com/watch?v=KqLtW8d8PXY&t=3087s
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    //Para ejemplo[2], con el ejemplo [3], ya no se necesita.
     //   job = SupervisorJob()

       //vm = ViewModelProvider(this).get()
       vm = ViewModelProvider(this)[MainViewModel::class.java]
       vm.loginResult.observe(this, Observer { sucess ->
           toast(if (sucess) "Success" else "failure")
       })

        submit.setOnClickListener {
            //Dispatcher.IO,    Para acceso a archivos, BD, llamadas a http,etc.
            //Diapatcher.Main   El hilo principal de android.
            //Dispatcher.Default. Para operacion que requieren bastante uso de la cpu
            //Sino se define, es Default.

            //GlobalScope. Esta bien para rutinas que queremos que ejecutan durante toda la aplicacion.


            /* Ejemplo [1] con GlobalScope.
            GlobalScope.launch (Dispatchers.Main){
                //withContext, ejecuta en un contexto distinto al que se esta ejecutando la corroutina.
                val success = withContext(Dispatchers.IO){

                    validateLogin(username.text.toString(), password.text.toString())
                }
                //Se ejecuta hasta que termina
                toast(if (success) " Success " else " Failure ")

            }*/

/*
           //Ejemplo [2] con CoroutineScope, con scope de activity.
            //Para Scope de activity.
            //Se debe usar CourineScope [25:00]

           launch{
                val success = withContext(Dispatchers.IO){
                    validateLogin(username.text.toString(), password.text.toString())
                }
                //Se ejecuta hasta que termina
                toast(if (success) " Success " else " Failure ")
            }*/


            /*
            //Ejemplo [3] .[30.00]
            //Se agrega una nueva dependencia:
            //implementation( 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1')
*/
/*            lifecycleScope.launch{
                val success = withContext(Dispatchers.IO){
                    validateLogin(username.text.toString(), password.text.toString())
                }
                //Se ejecuta hasta que termina
                toast(if (success) " Success " else " Failure ")
            }*/


            /*
            //Ejemplo 4: [35:00]
            // para enlazar 2 o mas rutinas, y se ejecuten en
            // para secuencia.
            // para paralelo. se agrega la async. en lugar del withContext
            lifecycleScope.launch{
                val success1 = async(Dispatchers.IO){
                    validateLogin(username.text.toString(), password.text.toString())
                }
                val success2 = async(Dispatchers.IO){
                    validateLogin2(username.text.toString(), password.text.toString())
                }
                //Se ejecuta hasta que termina
                //Ejemplo[4] .. en secuencia con withContext -- Tardaria 4 segundos
                //toast(if (success1 && success2) " Success " else " Failure ")
                //Ejemplo[4] .. en Paralelo con Async --Tardaria 2 secuencia.
                toast(if (success1.await() && success2.await()) " Success " else " Failure ")

            }
*/

            //Ejemplo 5: [39:30] -Viewmodel
            //Se cambia la dependencia de:
            //implementation( 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1')
            //a:
            //implementation( 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1')
            vm.onSubmitClicked(username.text.toString(), password.text.toString())

        }
    }

    fun validateLogin(user: String, pass: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }
    fun validateLogin2(user: String, pass: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && pass.isNotEmpty()
    }
    /*
    //Para ejemplo[2], con el ejemplo [3], ya no se necesita.
    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }*/
}

private fun Context. toast (message: String ) {
    Toast.makeText ( this , message , Toast.LENGTH_SHORT ) .show ( )
}





