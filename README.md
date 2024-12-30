# Döviz Takip ve Çeviri Uygulaması

Bu proje, kullanıcıların döviz kurlarını takip edebileceği ve döviz çevirisi yapabileceği bir **Clean Architecture** yaklaşımıyla geliştirilmiş modern bir Android uygulamasıdır. Hilt, Retrofit ve Lottie gibi popüler kütüphaneleri kullanarak yapılandırılmıştır.

---

## 📱 Özellikler

- **Döviz Kuru Takibi:** Güncel döviz kurlarını anlık olarak görüntüleyebilirsiniz.
- **Altın Fiyatları:** Altın fiyatlarını kolayca takip edin.
- **Döviz Çevirici:** Farklı para birimleri arasında dönüşüm yapabilirsiniz.
- **Lottie Animasyonları:** Splash ekranında dinamik animasyonlar.
- **Clean Architecture:** Modüler ve kolay yönetilebilir bir kod yapısı.
- **Hilt ile Dependency Injection:** Daha temiz ve test edilebilir kod.

---



## 🛠️ Kullanılan Teknolojiler ve Kütüphaneler

- **Dil:** Kotlin
- **Mimari:** Clean Architecture (Domain, Data, Presentation)
- **DI (Dependency Injection):** Hilt
- **HTTP İstekleri:** Retrofit, Gson Converter
- **Navigasyon:** Jetpack Navigation
- **Arayüz:** Material Design Bileşenleri, RecyclerView, CardView
- **Animasyonlar:** Lottie
- **Splash Screen:** AndroidX Core SplashScreen
- **Bağlama:** ViewBinding

### 📦 Kütüphaneler

| Kütüphane | Versiyon |
|-----------|----------|
| Hilt | `2.x` |
| Retrofit | `2.x` |
| Gson Converter | `2.x` |
| Navigation | `2.x` |
| RecyclerView | `1.x` |
| CardView | `1.x` |
| Material Design | `1.6.0` |
| Lottie | `5.x` |
| SplashScreen | `1.x` |

---

## 📂 Clean Architecture Katmanları

**Clean Architecture** yaklaşımı, proje yönetimini kolaylaştırır ve modüler bir yapı sağlar.

### 1️⃣ **Data Katmanı**
- Veri kaynakları (Remote ve Local)
- API çağrıları için Retrofit entegrasyonu
- Model ve DTO dönüşümleri
- Repository implementasyonları

### 2️⃣ **Domain Katmanı**
- İş mantığını temsil eder
- **UseCase** sınıfları (ör. Döviz kurlarını alma)
- Uygulama bağımsızlığı sağlar

### 3️⃣ **Presentation Katmanı**
- ViewModel'lar ve UI (Fragment, Activity)
- **ViewBinding** ve UI için RecyclerView, CardView gibi bileşenler kullanılır
- Lottie animasyonları ve Material tasarımı ile kullanıcı dostu bir deneyim sağlar

---

├── data

│   ├── model           # Veri modelleri ve DTO'lar

│   ├── remote          # Retrofit API ve veri kaynakları

│   ├── repository      # Repository implementasyonları

├── domain

│   ├── model           # Domain modelleri

│   ├── usecase         # UseCase sınıfları

├── presentation

│   ├── view            # Fragment'lar ve Activity'ler

│   ├── viewmodel       # ViewModel sınıfları

│   ├── adapter         # RecyclerView Adapter'ları

├── utils               # Yardımcı sınıflar ve uzantılar

├── res

│   ├── layout          # XML dosyaları

│   ├── values          # Strings, colors, styles

│   ├── raw             # Lottie animasyon dosyaları


Projeyi klonlayın:
   
   git clone https://github.com/kullanici-adi/doviz-uygulamasi.git

📞 İletişim
Sorularınız veya önerileriniz için benimle iletişime geçebilirsiniz: nebicanplanali@gmail.com

## Ekran kaydı



https://github.com/user-attachments/assets/67159c3c-b38d-4ffa-8ed9-9930db9e38f3

