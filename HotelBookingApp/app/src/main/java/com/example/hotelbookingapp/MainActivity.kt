package com.example.hotelbookingapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.provider.CalendarContract.CalendarEntity
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbookingapp.database.Hotel
import com.example.hotelbookingapp.database.HotelAdapter
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var hotelRecyclerView: RecyclerView
    private lateinit var entryDate: EditText
    private lateinit var exitDate: EditText
    private lateinit var personCount: EditText
    private lateinit var searchButton: Button

    private val hotels = listOf(
        Hotel(name = "Luxe Hotel",
            city = "İstanbul",
            district = "Kadıköy",
            price = 130.0,
            rating = 4.5f,
            imageResId = R.drawable.kadikoyotel,
            features = "İstanbul’un en canlı semtlerinden Kadıköy’ün kalbinde yer alan otelimiz, hem şehrin enerjisini hissetmek isteyenler hem de konforlu bir konaklama arayanlar için ideal bir tercihtir.\n" +
                    "\n" +
                    "Modern ve şık tasarıma sahip odalarımızda ücretsiz Wi-Fi, klima, minibar ve akıllı TV gibi olanaklar sunulmaktadır. Güne zengin açık büfe kahvaltımızla başlayabilir, gün boyu otelimizde bulunan kafe ve restoranımızda lezzetli seçeneklerin tadını çıkarabilirsiniz.\n" +
                    "\n" +
                    "Ayrıca otelimiz, kolay ulaşım imkânına sahip olup metro, vapur ve otobüs duraklarına yürüme mesafesindedir. İş veya tatil amaçlı konaklamalarınızda, misafir memnuniyetini ön planda tutan profesyonel ekibimizle hizmetinizdeyiz.\n" +
                    "\n" +
                    "Kadıköy’ün eşsiz atmosferini yaşamak için sizi bekliyoruz!"),

        Hotel(name = "Sirius Hotel",
            city = "İstanbul",
            district = "Beşiktaş",
            price = 180.0,
            rating = 4.7f,
            imageResId = R.drawable.besiktas,
            features = "İstanbul’un merkezi ve hareketli semti Beşiktaş’ta yer alan otelimiz, şehrin tarihi dokusunu modern konforla birleştirerek misafirlerine eşsiz bir konaklama deneyimi sunuyor.\n" +
                    "\n" +
                    "Zarif şekilde tasarlanmış odalarımızda ücretsiz Wi-Fi, LCD TV, minibar, klima ve şehir manzaralı balkon seçenekleri mevcuttur. Sabahları zengin açık büfe kahvaltımız ile güne enerjik bir başlangıç yapabilir, günün yorgunluğunu ise otelimizde bulunan spa, fitness salonu ve sauna imkanlarıyla atabilirsiniz.\n" +
                    "\n" +
                    "Otelimiz, Boğaz’a ve Dolmabahçe Sarayı’na yürüme mesafesinde olup, toplu taşıma noktalarına kolay erişim sağlar. Ayrıca iş seyahatleriniz için donanımlı toplantı salonlarımız ve 24 saat resepsiyon hizmetimizle sizlere kusursuz bir konaklama sunmaktayız.\n" +
                    "\n" +
                    "İstanbul’un kalbinde, hem iş hem de keyifli bir tatil için sizleri Beşiktaş’ın ayrıcalığını yaşamaya davet ediyoruz."),

        Hotel(name = "Sertil Deluxe Hotel",
            city = "Muğla",
            district = "Fethiye",
            price = 210.0,
            rating = 4.7f,
            imageResId = R.drawable.sertilfethiye,
            features = "Fethiye’nin en gözde bölgelerinden birinde, denize sadece birkaç adım mesafede konumlanan Blue Wave Hotel, deniz ve güneş tatili yapmak isteyen misafirler için ideal bir seçenektir.\n" +
                    "\n" +
                    "Modern tasarımlı, deniz manzaralı odalarımızda konforunuz için her detay düşünülmüştür: klima, ücretsiz Wi-Fi, LCD TV ve balkon. Otelimizin özel plaj alanında güneşin tadını çıkarabilir, açık yüzme havuzumuzda serinleyebilirsiniz.\n" +
                    "\n" +
                    "Ayrıca otelimizde yer alan spa merkezi, sauna, masaj hizmetleri ve spor salonu sayesinde hem dinlenip hem yenilenebilirsiniz. Akşamları ise otel terasında gün batımına karşı eşsiz bir akşam yemeği keyfi sizleri bekliyor.\n" +
                    "\n" +
                    "Fethiye’nin mavisiyle buluşmak için Blue Wave Hotel’de yerinizi ayırtın!" ),

        Hotel(name = "Manaspark Otel",
            city = "Muğla",
            district = "Fethiye",
            price = 130.0,
            rating = 4.4f,
            imageResId = R.drawable.manasparkfethiye,
            features = "Fethiye’nin eşsiz doğasıyla iç içe konumlanan Manaspar otel, huzurlu bir tatil arayan misafirler için mükemmel bir tercihtir. Geniş yeşil alanlar içerisinde yer alan otelimizde, konforlu ve modern dekore edilmiş odalarımızda klima, ücretsiz Wi-Fi, minibar ve balkon bulunmaktadır.\n" +
                    "\n" +
                    "Açık yüzme havuzu, çocuk havuzu ve özel plaj alanımız sayesinde dilediğiniz gibi güneşin tadını çıkarabilirsiniz. Ayrıca otelimizde organik ürünlerle hazırlanan açık büfe kahvaltımız, Türk ve dünya mutfağından lezzetler sunan restoranımızla hizmetinizdeyiz.\n" +
                    "\n" +
                    "Doğa yürüyüşleri, bisiklet turları ve çevredeki tarihi alanlara düzenlenen turlar ile tatilinizi daha keyifli hale getirebilirsiniz. Fethiye’nin sakin atmosferinde rahatlamaya hazır olun!"),

        Hotel(name = "Swissotel Büyük Efes İzmir",
            city = "İzmir",
            district = "Konak",
            price = 170.0,
            rating = 4.1f,
            imageResId = R.drawable.izmir,
            features = "İzmir’in kalbinde, deniz kıyısına sadece birkaç adım mesafede yer alan Swissotel Büyük Efes İzmir, lüks ve konforun mükemmel uyumunu sunuyor. Modern tasarıma sahip, ferah odalarımızda misafirlerimizin konforu için ücretsiz Wi-Fi, klima, minibar, çalışma alanı ve etkileyici İzmir Körfezi manzarası standart olarak sunulmaktadır.\n" +
                    "\n" +
                    "Otelimizin geniş açık yüzme havuzu, kapalı havuzu, tam donanımlı fitness merkezi, sauna ve ünlü Pürovel Spa & Wellness merkezi ile şehir hayatının stresinden uzaklaşıp yenilenebilirsiniz.\n" +
                    "\n" +
                    "Gastronomi tutkunları için ise farklı lezzetler sunan restoranlarımızda Ege mutfağının taze ve yerel tatlarını deneyimleyebilir, teras barımızda gün batımının keyfini çıkarabilirsiniz.\n" +
                    "\n" +
                    "İzmir’in iş ve alışveriş merkezlerine, kültürel mekanlarına yürüme mesafesindeki eşsiz konumuyla Swissotel Büyük Efes İzmir, hem iş hem tatil amaçlı konaklamalarınızda şehri keşfetmek için ideal bir noktada sizleri bekliyor." ),

        Hotel(name = "BW Plus Hotel",
            city = "İzmir",
            district = "Konak",
            price = 180.0,
            rating = 4.0f,
            imageResId = R.drawable.izmiriki,
            features = "İzmir’in kalbinde, deniz kıyısına sadece birkaç adım mesafede yer alan BW Plus Hotel, lüks ve konforun mükemmel uyumunu sunuyor. Modern tasarıma sahip, ferah odalarımızda misafirlerimizin konforu için ücretsiz Wi-Fi, klima, minibar, çalışma alanı ve etkileyici İzmir Körfezi manzarası standart olarak sunulmaktadır.\n" +
                    "\n" +
                    "Otelimizin geniş açık yüzme havuzu, kapalı havuzu, tam donanımlı fitness merkezi, sauna ve ünlü Pürovel Spa & Wellness merkezi ile şehir hayatının stresinden uzaklaşıp yenilenebilirsiniz.\n" +
                    "\n" +
                    "Gastronomi tutkunları için ise farklı lezzetler sunan restoranlarımızda Ege mutfağının taze ve yerel tatlarını deneyimleyebilir, teras barımızda gün batımının keyfini çıkarabilirsiniz.\n" +
                    "\n" +
                    "İzmir’in iş ve alışveriş merkezlerine, kültürel mekanlarına yürüme mesafesindeki eşsiz konumuyla Swissotel Büyük Efes İzmir, hem iş hem tatil amaçlı konaklamalarınızda şehri keşfetmek için ideal bir noktada sizleri bekliyor."),

        Hotel(name = "Anemaon Aydın Otel",
            city = "Aydın",
            district = "Kuşadası",
            price = 120.0,
            rating = 4.5f,
            imageResId = R.drawable.anemonl,
            features = "Aydın şehir merkezinde konumlanan Anemon Aydın Otel, konforlu ve kaliteli hizmet anlayışıyla misafirlerine unutulmaz bir konaklama deneyimi sunuyor. Modern ve şık dekore edilmiş odalarımızda klima, ücretsiz Wi-Fi, minibar, LCD TV ve çalışma masası gibi tüm detaylar düşünülmüştür.\n" +
                    "\n" +
                    "Otelimizde güne zengin açık büfe kahvaltı ile başlayabilir, gün boyu Türk ve dünya mutfağından lezzetler sunan restoranımızda keyifli bir yemek deneyimi yaşayabilirsiniz. Ayrıca otelimizde açık yüzme havuzu, fitness salonu, sauna ve masaj hizmetleri gibi imkanlarla rahatlayabilir, dinlenebilirsiniz.\n" +
                    "\n" +
                    "Toplantı ve organizasyonlar için tam donanımlı toplantı salonlarımız da hizmetinizdedir. Hem iş seyahatleri hem de tatil amaçlı konaklamalarınızda Aydın’ın merkezi konumunda, konforlu bir deneyim için Anemon Aydın Otel sizi bekliyor!\n" +
                    "\n"),

        Hotel(name = "Divan Ankara Otel",
            city = "Ankara",
            district = "Çankaya",
            price = 110.0,
            rating = 4.4f,
            imageResId = R.drawable.otel1,
            features = "Divan Ankara Otel, konfor ve şıklığı bir araya getiren özel tasarımıyla misafirlerine benzersiz bir konaklama deneyimi sunar. Çankaya’nın merkezi konumunda yer alan otelimiz, iş veya tatil amaçlı seyahat eden misafirler için ideal bir tercihtir.\n" +
                    "\n" +
                    "Otelimizde modern tasarımlı ferah odalar, ücretsiz yüksek hızlı Wi-Fi, 24 saat oda servisi ve zengin açık büfe kahvaltı imkânı sunulmaktadır. Ayrıca spa merkezi, fitness salonu, sauna, kapalı yüzme havuzu ve profesyonel toplantı salonları ile konaklamanızı daha keyifli hale getiriyoruz.\n" +
                    "\n" +
                    "Misafir memnuniyetini ön planda tutan güler yüzlü personelimiz ile Divan kalitesini Ankara’da yaşayın!"),

        Hotel(name = "Aqua Blue Kuşadası Hotel",
            city = "Aydın",
            district = "Kuşadası",
            price = 210.0,
            rating = 4.8f,
            imageResId = R.drawable.otel2,
            features = "Ege Denizi’nin masmavi sularına bakan eşsiz konumuyla Aqua Blue Kuşadası Hotel, konforlu ve keyifli bir tatil deneyimi sunuyor. Denize sıfır konumda yer alan otelimizde, özel plaj alanımızda güneşin tadını çıkarabilir, açık yüzme havuzumuzda serinleyebilirsiniz.\n" +
                    "\n" +
                    "Modern ve ferah odalarımızda balkon, klima, ücretsiz Wi-Fi, minibar ve deniz manzarası bulunmaktadır. Sabahları taze ve çeşitli ürünlerden oluşan açık büfe kahvaltımızla güne enerjik başlayabilir, akşamları ise Türk ve dünya mutfağından lezzetleri restoranımızda tadabilirsiniz.\n" +
                    "\n" +
                    "Ayrıca otelimizde fitness merkezi, spa & masaj hizmetleri, çocuklar için oyun alanı ve toplantı salonları gibi imkanlar da sunulmaktadır. Kuşadası’nın merkezi noktalarına yakın konumu sayesinde alışveriş, eğlence ve tarihi yerlere kolay erişim sağlarsınız.\n" +
                    "\n" +
                    "Unutulmaz bir tatil için sizleri Aqua Blue Kuşadası Hotel’e bekliyoruz!"),

        Hotel(name = "Marmaris Grand Resort Hotel",
            city = "Muğla",
            district = "Marmaris",
            price = 170.0,
            rating = 4.6f,
            imageResId = R.drawable.buyukotel,
            features = "Marmaris'in eşsiz doğasıyla iç içe, denize sıfır konumlanan Marmaris Grand Resort Hotel, tatiliniz boyunca rahatlık ve huzur sunuyor. Modern tasarıma sahip odalarımızda, deniz manzarası, balkon, klima, ücretsiz Wi-Fi, minibar ve LCD TV gibi olanaklar mevcut.\n" +
                    "\n" +
                    "Oteldeki geniş açık yüzme havuzları, çocuk havuzları ve özel plaj alanımızda güneşin tadını çıkarabilirsiniz. Ayrıca, tatiliniz boyunca rahatlamak için spa merkezi, sauna ve masaj hizmetlerinden faydalanabilirsiniz. Zengin açık büfe kahvaltı ile güne başlayabilir, otelimizdeki restoranlarda Ege mutfağının taze lezzetlerini deneyebilirsiniz.\n" +
                    "\n" +
                    "Marmaris'in tarihi ve kültürel zenginliklerini keşfetmek için otelden kolayca ulaşabileceğiniz turlar düzenlenmektedir. Ayrıca otelimizin spor salonu, tenis kortları ve su sporları aktiviteleri ile tatilinizi daha aktif hale getirebilirsiniz.\n" +
                    "\n" +
                    "Marmaris Grand Resort Hotel, tatilinizi unutulmaz kılmak için her detayı ile sizleri bekliyor!"),

        Hotel(name = "Karasu Beach Resort Hotel",
            city = "Sakarya",
            district = "Karasu",
            price = 90.0,
            rating = 3.1f,
            imageResId = R.drawable.otel,
            features = "Sakarya Karasu'da denizle iç içe, doğal güzelliklerle çevrili bir konumda yer alan Karasu Beach Resort Hotel, huzurlu bir tatil geçirmek isteyenler için mükemmel bir seçenektir. Modern ve ferah odalarımızda balkon, deniz manzarası, klima, ücretsiz Wi-Fi, minibar ve LCD TV gibi konforlu olanaklar sunulmaktadır.\n" +
                    "\n" +
                    "Otelimizde zengin açık büfe kahvaltılarla güne başlayabilir, Ege mutfağının en taze lezzetlerini sunan restoranlarımızda öğle ve akşam yemeklerinizi yiyebilirsiniz. Ayrıca, açık yüzme havuzumuzda serinleyebilir, denize sıfır konumuyla plajda güneşin tadını çıkarabilirsiniz.\n" +
                    "\n" +
                    "Aktif bir tatil yapmak isteyenler için fitness merkezi, su sporları ve tenis kortları gibi olanaklar da mevcuttur. Karasu'nun doğal güzelliklerini keşfetmek isteyenler için otelimizden çeşitli turlar düzenlenmektedir.\n" +
                    "\n" +
                    "Karasu Beach Resort Hotel, hem dinlenmek hem de eğlenmek isteyen misafirleri için tüm imkanları sunuyor. Sizi unutulmaz bir tatil deneyimi için bekliyoruz!"),

        Hotel(name = "Alaçatı Windmill Hotel",
            city = "İzmir",
            district = "Alaçatı",
            price = 115.0,
            rating = 3.9f,
            imageResId = R.drawable.miraclehotel,
            features = "İzmir Alaçatı'da sakin ve huzurlu bir tatil geçirmek isteyenler için ideal bir seçenek olan Alaçatı Windmill Hotel, şirin taş evleri ve rüzgar değirmeni manzarasıyla öne çıkıyor. Odamızda klima, ücretsiz Wi-Fi, minibar, balkon ve Alaçatı'nın eşsiz manzarasını görebileceğiniz geniş pencereler sunulmaktadır.\n" +
                    "\n" +
                    "Açık yüzme havuzumuzda serinleyebilir, geleneksel Ege kahvaltımızla güne başlayabilirsiniz. Restoranımızda Alaçatı'nın yerel lezzetlerinden tatlar ve organik ürünlerle hazırlanan yemekler sunulmaktadır. Ayrıca, otelimizdeki wellness merkezi, yoga alanı ve doğal bahçemizde dinlenebilirsiniz.\n" +
                    "\n" +
                    "İzmir’in gözde tatil beldesi Alaçatı'nın renkli sokaklarına ve plajlarına kolayca ulaşabileceğiniz konumuyla, konforlu ve keyifli bir tatil için Alaçatı Windmill Hotel sizi bekliyor."),

        Hotel(name = "Silifke Paradise Resort",
            city = "Mersin",
            district = "Silifke",
            price = 124.0,
            rating = 3.5f,
            imageResId = R.drawable.otel3,
            features = "Mersin'in Silifke ilçesinde, doğal güzelliklerle çevrili bir cennette yer alan Silifke Paradise Resort, denizin ve doğanın tadını çıkarabileceğiniz bir tatil sunuyor. Şık ve konforlu odalarımızda deniz manzaralı balkon, klima, Wi-Fi, minibar ve LED TV bulunmaktadır.\n" +
                    "\n" +
                    "Otelimizde açık yüzme havuzu, özel plaj alanı, çocuk havuzu ve spor salonu gibi imkanlar sunulmaktadır. Ayrıca, taze deniz ürünleri ve Akdeniz mutfağından zengin bir menüyle hizmet veren restoranımızda lezzetli yemekler yiyebilirsiniz. Spa merkezimizde rahatlatıcı masajlar ve saunalarla kendinizi yenileyebilirsiniz.\n" +
                    "\n" +
                    "Mersin'in tarihi ve doğal güzelliklerini keşfetmek için otelimizden düzenlenen turlar, ziyaretçilere unutulmaz bir deneyim yaşatacaktır."),

        Hotel(name = " Alanya Paradise Beach Hotel",
            city = "Antalya",
            district = "Alanya",
            price = 147.0,
            rating = 4.5f,
            imageResId = R.drawable.otel4,
            features = "Antalya'nın Alanya ilçesinde, benzersiz plajlara yakın konumda yer alan Alanya Paradise Beach Hotel, lüks ve konforu bir arada sunuyor. Geniş ve modern odalarımızda deniz manzarası, klima, ücretsiz Wi-Fi, minibar ve LCD TV bulunmaktadır.\n" +
                    "\n" +
                    "Açık yüzme havuzumuzda serinleyebilir, deniz kenarındaki plajda güneşin tadını çıkarabilirsiniz. Günün her saati açık olan restoranımızda zengin bir açık büfe kahvaltı, öğle ve akşam yemekleri servis edilmektedir. Ayrıca otelimizde spa, sauna, masaj hizmetleri ve fitness merkezi de bulunmaktadır.\n" +
                    "\n" +
                    "Alanya'nın ünlü tarihi yerlerine ve eğlence alanlarına kolay erişim imkanı sunan otelimiz, unutulmaz bir tatil deneyimi arayan tüm misafirler için mükemmel bir tercihtir.\n" +
                    "\n"),

        Hotel(name = "Datça Blue Bay Resort",
            city = "Muğla",
            district = "Datça",
            price = 119.0,
            rating = 4.2f,
            imageResId = R.drawable.otel5,
            features = "Muğla'nın Datça ilçesinde, doğal güzelliklerle çevrili, huzurlu ve sakin bir tatil beldesi olan Datça Blue Bay Resort, Ege'nin berrak sularına sıfır konumlanmıştır. Konforlu odalarımızda deniz manzarası, klima, ücretsiz Wi-Fi, minibar ve LCD TV gibi olanaklar sunulmaktadır.\n" +
                    "\n" +
                    "Otelimizde geniş açık yüzme havuzları, özel plaj alanı, spa merkezi, fitness salonu ve su sporları aktiviteleri ile rahatlayabilir ve eğlenebilirsiniz. Ege mutfağından zengin lezzetler sunan restoranlarımızda yemeklerinizi keyifle yiyebilirsiniz. Ayrıca, otelimizde yoga ve meditasyon seansları ile stres atabilir, doğa yürüyüşleri yapabilirsiniz.\n" +
                    "\n" +
                    "Datça'nın sakin atmosferi ve benzersiz doğası eşliğinde, Datça Blue Bay Resort'ta huzurlu bir tatil sizi bekliyor.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // RecyclerView'ı initialize et
        hotelRecyclerView = findViewById(R.id.hotelRecyclerView)
        entryDate = findViewById(R.id.entryDate)
        exitDate = findViewById(R.id.exitDate)
        personCount = findViewById(R.id.personCount)
        searchButton = findViewById(R.id.searchButton)

        hotelRecyclerView.layoutManager = LinearLayoutManager(this)

        // Fake Data ile Adapter oluştur
        hotelAdapter = HotelAdapter(hotels)
        hotelRecyclerView.adapter = hotelAdapter


        entryDate.setOnClickListener {
            showDatePicker { date -> entryDate.setText(date) }
        }

        exitDate.setOnClickListener {
            showDatePicker { date -> exitDate.setText(date) }
        }
    }

    private fun showDatePicker(onDateSelected: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            { _, year, month, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${month+1}/$year"
                onDateSelected(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.window?.setBackgroundDrawableResource(android.R.color.transparent)
        datePicker.show()
    }
}
