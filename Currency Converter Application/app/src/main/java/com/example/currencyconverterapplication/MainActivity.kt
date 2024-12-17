package com.example.currencyconverterapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.currencyconverterapplication.ui.theme.CurrencyConverterApplicationTheme
import com.example.currencyconverterapplication.view_model.CurrencyViewModel
import com.example.currencyconverterapplication.R
import com.example.currencyconverterapplication.databinding.ActivityMainBinding
import com.example.currencyconverterapplication.helper.Endpoint
import com.example.currencyconverterapplication.helper.Resource
import com.example.currencyconverterapplication.helper.Utiltity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Currency
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Declare all variables

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var selectedCurrency1: String? = "VND"
    private var selectedCurrency2: String? = "USD"

    //View model
    private val viewModel: CurrencyViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Utiltity.makeStatusBarTransparent(this)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Init spinner
        initSpinner()

        setUpClickListener()
    }

    private fun initSpinner() {
        val spinner1 = binding.spnFirstCountry
        spinner1.setItems(getAllCountry())
        spinner1.setOnClickListener() {
            Utiltity.hideKeyboard(this)
        }
        spinner1.setOnItemSelectedListener { _, _, _, item ->
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedCurrency1 = currencySymbol
            binding.txtFirstCurrencyName.text = selectedCurrency1
        }

        val spinner2 = binding.spnSecondCountry
        spinner2.setItems(getAllCountry())
        spinner2.setOnClickListener() {
            Utiltity.hideKeyboard(this)
        }
        spinner2.setOnItemSelectedListener { _, _, _, item ->
            val countryCode = getCountryCode(item.toString())
            val currencySymbol = getSymbol(countryCode)
            selectedCurrency2 = currencySymbol
            binding.txtFirstCurrencyName.text = selectedCurrency2
        }
    }

    private fun getSymbol(countryCode: String?): String? {
        val availableLocales = Locale.getAvailableLocales()
        for (i in availableLocales.indices) {
            if (availableLocales[i].country == countryCode
            ) return Currency.getInstance(availableLocales[i]).currencyCode
        }
        return ""
    }

    private fun getCountryCode(countryName: String) = Locale.getISOCountries().find { Locale("", it).displayCountry == countryName }

    private fun getAllCountry(): ArrayList<String> {
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.displayCountry
            if (country.trim { it <= ' ' }.isNotEmpty() && !countries.contains(country)) {
                countries.add(country)
            }
        }
        countries.sort()
        return countries
    }

    private fun setUpClickListener(){
        //Convert button clicked - check for empty string and internet then do the conversion
        binding.btnConvert.setOnClickListener {

            //check if the input is empty
            val numberToConvert = binding.etFirstCurrency.text.toString()

            if(numberToConvert.isEmpty() || numberToConvert == "0"){
                Snackbar.make(binding.mainLayout,"Input a value in the first text field, result will be shown in the second text field", Snackbar.LENGTH_LONG)
                    .withColor(ContextCompat.getColor(this, R.color.dark_red))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }

            //check if internet is available
            else if (!Utiltity.isNetworkAvailable(this)){
                Snackbar.make(binding.mainLayout,"You are not connected to the internet", Snackbar.LENGTH_LONG)
                    .withColor(ContextCompat.getColor(this, R.color.dark_red))
                    .setTextColor(ContextCompat.getColor(this, R.color.white))
                    .show()
            }

            //carry on and convert the value
            else{
                doConversion()
            }
        }
    }

    private fun doConversion(){

        //hide keyboard
        Utiltity.hideKeyboard(this)

        //make progress bar visible
        binding.prgLoading.visibility = View.VISIBLE

        //make button invisible
        binding.btnConvert.visibility = View.GONE

        //Get the data inputed
        val apiKey = Endpoint.API_KEY

        //do the conversion
        viewModel.getConvertedData(apiKey)

        //observe for changes in UI
        observeUi()
    }

    /**
     * Using coroutines flow, changes are observed and responses gotten from the API
     *
     */

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun observeUi() {
        viewModel.data.observe(this, androidx.lifecycle.Observer {result ->
            when(result.status){
                Resource.Status.SUCCESS -> {
                    if (result.data?.success == true){

                        val map: Map<String, Double> = result.data.rates

                        map.keys.forEach {
                            val rateForAmount = map[it]

                            viewModel.data.value?.data?.rates = map

                            //format the result obtained e.g 1000 = 1,000
                            val formattedString = String.format("%,.2f", viewModel.data.value)

                            //set the value in the second edit text field
                            binding.etSecondCurrency.setText(formattedString)

                        }


                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.btnConvert.visibility = View.VISIBLE
                    }
                    else if(result.data?.success != true){
                        val layout = binding.mainLayout
                        Snackbar.make(layout,"Oops! something went wrong, Try again", Snackbar.LENGTH_LONG)
                            .withColor(ContextCompat.getColor(this, R.color.dark_red))
                            .setTextColor(ContextCompat.getColor(this, R.color.white))
                            .show()

                        //stop progress bar
                        binding.prgLoading.visibility = View.GONE
                        //show button
                        binding.btnConvert.visibility = View.VISIBLE
                    }
                }
                Resource.Status.ERROR -> {

                    val layout = binding.mainLayout
                    Snackbar.make(layout,  "Oops! Something went wrong, Try again", Snackbar.LENGTH_LONG)
                        .withColor(ContextCompat.getColor(this, R.color.dark_red))
                        .setTextColor(ContextCompat.getColor(this, R.color.white))
                        .show()
                    //stop progress bar
                    binding.prgLoading.visibility = View.GONE
                    //show button
                    binding.btnConvert.visibility = View.VISIBLE
                }

                Resource.Status.LOADING -> {
                    //stop progress bar
                    binding.prgLoading.visibility = View.VISIBLE
                    //show button
                    binding.btnConvert.visibility = View.GONE
                }

                Resource.Status.SUCCESS -> TODO()
                Resource.Status.ERROR -> TODO()
                Resource.Status.LOADING -> TODO()
            }
        })
    }

    private fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar {
        this.view.setBackgroundColor(colorInt)
        return this
    }
}