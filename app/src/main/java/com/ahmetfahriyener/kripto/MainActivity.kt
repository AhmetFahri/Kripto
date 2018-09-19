package com.ahmetfahriyener.kripto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var word : EditText
    lateinit var n : EditText
    lateinit var kripto : Button
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        word = findViewById(R.id.et_string) as EditText
        n = findViewById(R.id.et_n) as EditText
        kripto = findViewById(R.id.button) as Button
        result = findViewById(R.id.result) as TextView

        kripto.setOnClickListener(View.OnClickListener {

            var n_string = n.text
            var n : Int = n_string.toString().toInt()
            var array = CharArray(0)
            var i = 0

            if (word.text != null) {                    // Girilen stringin boş olmadığını kontrol ediyorum
                if (n <= 0)                             // Girilen n sayısının 0 ya da daha küçük olmadığını kontrol ediyorum
                {
                    Toast.makeText(this," 0'dan büyük bir sayı giriniz!", Toast.LENGTH_LONG).show()
                    return@OnClickListener
                }
                else {
                    array = CharArray(word.text.length)                 // Aldığım stringi charArray e alıyorum. ( Muhtemelen bunun daha kolay yolu vardır ama dile yabancı olunca bu şekilde yaptım)
                    for (c in word.text) {
                        array[i] = c
                        i++
                    }
                }
            }
            else
            {
                Toast.makeText(this,"Lütfen Boş Bırakmayınız!", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }

            kripto_func(array, n)                   // Gerekli yerleri bulmak ve yıldızlamak için fonksiyona gönderiyorum
        })

    }

    fun kripto_func (array: CharArray, n: Int) {
        var i: Int = 0


        while (i <= array.size - n) {          // Girilen stringden 'n' eksik olacak kadar dizide geziyorum çünkü zaten sonda 'n' den az karakter kaldıysa zaten bakmama gerek yok
            var count: Int = 1                 // İlk tuttuğum karakteri sayaca ekliyorum
            var word: Char = array[i]          // İlk karakteri char olarak da tutuyorum

            for (j in (i + 1)..((array.size)-1)) {          // Bir sonraki karaktere bakmak için for
                if (word == array[j]) {                     // Tuttuğum ve arkasındaki aynı olduğu zaman ikisinde de arttırıyoru
                    count++                                 // Kaç tane arka arkaya olduğunu tutuyorum
                } else {
                    break                                   // Aynı karakter gelmediğinde çıkıyorum döngüden
                }
            }

            if (count >= n) {                               // Karakterleri saydığım sayaç 'n' e eşit mi ya da büyük mü kontrolü yapıyorum
                while (word == array[i]) {                  // Uyugnsa while a grip ilk başta da tuttuğum ve kurala uyan karakterleri * ile değiştiriyorum
                    array[i] = '*'
                    i++
                    if (i >= array.size)                    // Sanırım kotlinde dizi sonunda '\0' gibi bir karakter yok bu nedenle dizi boyutunu aşmasın i diye bu şartı koydum
                    {
                        break
                    }
                }
            }
            else                                            // Ardarda gelen karakterler 'n' sayısında az ise döngüyü arttırıp devam ediyorum bakmaya
            {
                i++
            }
        }

        for (x in 0..array.size - 1) {
            val str: String = String(array);                // Son halini alan dizimi Stringe atıyorum ve yazdırıyorum.
            result.text = str
        }
    }

}
