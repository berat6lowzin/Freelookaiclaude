# Freelook Mod (Fabric, Minecraft 1.21.1) — Bulutta Derleme Rehberi

Bu paket artık **tam bir Gradle projesi** + bir **GitHub Actions dosyası**
içeriyor. Yani hiçbir şey kurmadan, GitHub üzerinden (telefondan bile)
gerçek bir `.jar` dosyası üretebilirsin.

## Adım 1 — GitHub'da yeni bir repo oluştur

1. https://github.com adresine git, hesabın yoksa ücretsiz oluştur.
2. Sağ üstten **+ > New repository**.
3. İsim ver (örn. `freelook-mod`), **Public** seç, "Create repository"
   ye bas. Başka bir şey işaretlemene gerek yok.

## Adım 2 — Bu paketin içeriğini repo'ya yükle

En kolay yol (tamamen telefon/tarayıcı üzerinden):

1. Az önce indirdiğin `freelook-mod.zip` dosyasını aç/çıkart, içindeki
   **tüm dosya ve klasörleri** al (README hariç fark etmez, o da gidebilir).
2. GitHub'daki repo sayfasında **"Add file" > "Upload files"** butonuna bas.
3. Çıkarttığın dosyaları (klasör yapısıyla birlikte — `.github`,
   `src`, `build.gradle`, `settings.gradle`, `gradle.properties`)
   sürükleyip bırak. (Bazı tarayıcılarda tek tek dosya seçmek yerine
   klasörü sürüklemek daha kolay olur; GitHub'ın mobil tarayıcı yükleme
   arayüzü klasör sürüklemeyi desteklemeyebilir — desteklemezse bir
   bilgisayardan yapman gerekebilir, ya da GitHub Desktop / Termux gibi
   bir araçla `git push` edebilirsin.)
4. Alt kısımda "Commit changes" ile onayla.

**Önemli:** `.github/workflows/build.yml` dosyasının repo'da doğru yolda
olduğundan emin ol — yani repo kökünde `.github` klasörü, onun içinde
`workflows` klasörü, onun içinde `build.yml` olmalı.

## Adım 3 — Otomatik derlemeyi başlat

Dosyaları yükleyip "Commit" dediğin an GitHub Actions **otomatik olarak
tetiklenir** (çünkü workflow `push` olayında da çalışacak şekilde
ayarlı). İstersen elle de tetikleyebilirsin:

1. Repo sayfasında üstteki **"Actions"** sekmesine gir.
2. Soldan **"Build Freelook Mod"** workflow'unu seç.
3. Sağda **"Run workflow"** butonuna bas (elle tetiklemek istersen).
4. Birkaç dakika bekle — yeşil tik (✓) çıkarsa derleme başarılı demektir.
   Kırmızı çarpı (✗) çıkarsa, o çalıştırmaya tıklayıp hata logunu bana
   yapıştırabilirsin, birlikte düzeltiriz.

## Adım 4 — Jar dosyasını indir

1. Başarılı olan workflow çalıştırmasına (yeşil tikli olana) tıkla.
2. Sayfanın altında **"Artifacts"** bölümünde **"freelook-mod-jar"**
   göreceksin — ona tıklayınca bir zip iner, içinde asıl `.jar`
   dosyası var (dosya adı `freelook-1.0.0.jar` gibi bir şey olacak —
   `-sources.jar` **değil**, normal olanı al).
3. O `.jar` dosyasını telefonuna indir.

## Adım 5 — Zalith Launcher'a kur

1. Zalith Launcher'da 1.21.1 Fabric profili oluştur (Fabric Loader
   kurulu olmalı).
2. **Fabric API** modunu da indirip aynı yere koymalısın (bizim modun
   çalışması buna bağımlı) — Modrinth'ten "Fabric API" ara, 1.21.1
   Fabric sürümünü indir.
3. Her iki `.jar` dosyasını da (Fabric API + bizim `freelook-1.0.0.jar`)
   Zalith'in mods klasörüne kopyala.
4. Oyunu başlat, backslash (`\`) tuşuna bas — freelook açılacak.

## Nasıl çalışıyor? (davranış)

- Tuşa basınca (varsayılan: `\`) kamera karakterden bağımsız serbestçe
  döner, WASD hareketi ve karakterin gerçek yönü etkilenmez.
- Tekrar basıp kapattığında karakter **anında** en son freelook ile
  baktığın yöne döner (snap) — istediğin davranış tam olarak bu.
- Tuşu Options > Controls > Freelook menüsünden değiştirebilirsin.
