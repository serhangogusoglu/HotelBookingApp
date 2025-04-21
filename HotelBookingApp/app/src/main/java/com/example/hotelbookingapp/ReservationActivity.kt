package com.example.hotelbookingapp

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity() {

    private lateinit var customerContainer: LinearLayout
    private var customerCount = 1 // Başlangıçta 2 müşteri var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        customerContainer = findViewById(R.id.customerContainer)
        val addCustomerButton = findViewById<Button>(R.id.addCustomerButton)
        val confirmButton = findViewById<Button>(R.id.confirmButton)

        // İlk 2 müşteri alanlarını ekleyelim
        addCustomerFields(1)
       // addCustomerFields(2)

        // Müşteri Ekle butonu
        addCustomerButton.setOnClickListener {
            customerCount++
            addCustomerFields(customerCount)
        }

        // Rezervasyon Onayla butonu
        confirmButton.setOnClickListener {
            // Tüm müşteri alanlarını kontrol edelim
            val allFieldsValid = validateCustomerFields()

            if (allFieldsValid) {
                // Rezervasyon başarılı olduğunda yapılacak işlem
                Toast.makeText(this, "Rezervasyon tamamlandı", Toast.LENGTH_SHORT).show()
            } else {
                // Hata mesajı
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Dinamik olarak müşteri giriş alanlarını ekler
    private fun addCustomerFields(customerNumber: Int) {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 24, 0, 12)

        // Başlık
        val title = TextView(this).apply {
            text = "$customerNumber. Müşteri Bilgileri"
            textSize = 18f
            setPadding(0, 12, 0, 12)
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        customerContainer.addView(title, params)

        // Ad
        val name = EditText(this).apply {
            hint = "Ad"
        }
        customerContainer.addView(name, params)

        // Soyad
        val surname = EditText(this).apply {
            hint = "Soyad"
        }
        customerContainer.addView(surname, params)

        // Telefon
        val phone = EditText(this).apply {
            hint = "Telefon"
            inputType = android.text.InputType.TYPE_CLASS_PHONE
        }
        customerContainer.addView(phone, params)

        // E-posta
        val email = EditText(this).apply {
            hint = "E-posta"
            inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
        customerContainer.addView(email, params)
    }

    // Tüm müşteri alanlarını kontrol eder
    private fun validateCustomerFields(): Boolean {
        var isValid = true

        // Her müşteri için alanları kontrol et
        for (i in 0 until customerContainer.childCount) {
            val child = customerContainer.getChildAt(i)

            // Eğer bu bir EditText ise
            if (child is EditText) {
                if (child.text.isEmpty()) {
                    // Alan boşsa, geçersiz işaretle
                    child.error = "Bu alan boş bırakılamaz"
                    isValid = false
                }
            }
        }

        return isValid
    }
}
