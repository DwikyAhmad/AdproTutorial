Link Deployment: https://tutorial-1-dwikyahmad.koyeb.app/

# Modul 3

## Reflection 1
Principle yang telah saya implementasikan pada proyek ini adalah:
- SRP, Single Reponsibility Principle, saya memisahkan fungsionalitas dari class CarController dengan ProductController menjadi modul yang terpisah, dengan ini masing-masing modul pada Controller memiliki satu tanggung jawab saja yang akan meningkatkan readability, maintainability, dan testability.
- OCP, Open-Closed Principle, saya membuat sebuah class baru berupa DataRepository yang nantiny akan di extend oleh CarRepository dan ProductRepository, hal ini memungkinkan adanya modifikasi dari method tanpa mengubah kode asli dari DataRepository.
- DIP, Dependency Inversion Principle, saya mengganti dependency pada CarController dari menggunakan concrete class menjadi interface dari CarService yang akan meningkatkan fleksibilitas.

## Reflection 2
Keuntungan yang didapatkan dari menerapkan SOLID Principle adalah:
- Meningkatkan readability,
Pada prinsip SRP dengan memindahkan class menjadi module yang singular responsibility, kita dapat lebih mudah mengetahui struktur sebuah proyek dan apa yang dilakukan oleh masing-masing modul tersebut. Contoh pada pemisahan modul Controller pada CarController dan ProductController.
- Kode menjadi lebih maintainable,
Menggunakan prinsip OCP, kita hanya perlu menambahkan extension dari sebuah class sehingga kode aslinya tidak dirubah dan tidak mengganggu fungsionalitasnya, maka testing akan menjadi lebih mudah karena hanya perlu melakukan testing pada implementasi yang hanya baru dibuat. Contoh penggunaan class DataRepository untuk dilakukan extension pada CarRepository dan ProdcutRepository
- Kode menjadi lebih fleksibel terhadap perubahan,
Dengan menerapkan SOLID principle kode akan menjadi lebih fleksibel terhadap modifikasi karena implementasinya yang membuat sebuah struktur di mana kode lama tidak akan ikut terpengaruh dalam pembuatan sebuah fitur baru. Contoh pada penerapan OCP kita hanya cukup menambahkan sebuah class baru tanpa harus mengubah class yang lama sehingga hanya perlu fokus pada fitur yang baru.

## Reflection 3
Kerugian yang mungkin dialami jika tidak menerapi SOLID principles:
- Adanya duplikasi pada kode,
Tidak menerapkan SOLID principle, seperti Open-Closed Principle, memungkinkan adanya duplikasi kode, hal ini akan mengurangi kualitas dari kode kita. Contoh repository yang tidak menerapkan OCP akan mengalami duplikasi pada method create dan findAll
- Kode sulit diuji
Kode akan menjadi sulit diuji karena kertegantungan yang kuat antar komponen sehingga sulit untuk memisahkannya untuk dilakukan unit testing. Contoh testing create product dengan create car, jika berada pada class yang sama akan menyulitkan dalam melakukan unit testing.
- Kode sulit dipahami
Struktur kode akan menjadi lebih rumit dan sulit dibaca karena tidak mengikuti prinsip SOLID, sebagai contoh jika CarController tidak dipisah dari ProductController, maka akan susah untuk membedakan fungsionalitas yang ada pada modul tersebut.

# Modul 2

## Reflection 1
Berdasarkan pada scanning code dari PMD, telah ditemukan issue pada codebase saya, yaitu menggunakan modifier public yang tidak diperlukan pada method-method di ProductService interface. hal ini mengurangi kualitas clean code saya, maka dari itu saya menghapus semua access modifier public di setiap method ProductService untuk menghilangkan issue tersebut. Pada dasarnya ProductService merupakan sebuah interface yang di mana semua methodnya dianggap memiliki aksesibilitas public. Maka dari itu, penggunaan access modifier public tidak diperlukan dan hanya akan menjadi redundant pada codebase dan sebaiknya dihapuskan. Dengan hal ini saya menjadi lebih teliti dalam memberikan sebuah akses modifier kepada method-method yang ada di sebuah class.

## Reflection 2
Ya, workflow yang telah diterapkan membuat setiap push pada branch akan melakukan proses building, unit testing, dan deployement secara otomatis. Hal ini akan membuat proses yang berulang-ulang dari menyiapkan sebuah environment untuk aplikasi, melakukan integrasi dan deployment sebagai tahap akhir dari proses releasenya sebuah version aplikasi. CI/CD workflows ini akan melakukan proses tersebut setiap adanya perubahan pada code di repository github. Implementasi ini juga sesuai dengan konsep CI/CD karena sudah menggunakan script automation untuk melakukan repetitive tasks. Maka dari itu implementasi tersebut sudah memenuhi kriteria dari Continuous Integration and Continuous Deployment.

# Modul 1

## Reflection 1

Clean code yang telah diterapkan pada kode saya yaitu pada file `ProductController` function menggunakan nama yang meaningful dan hanya melakukan satu task saja, pada file-file lainnya, layout juga diberikan jarak blank lines untuk memisahkan deklarasi package, import dan setiap function, pada file `ProductService` code menerapkan abstraction dengan memisahkan abstract class dengan implementasinya dengan tujuan untuk menyembunyikan data yang tidak perlu dipaparkan dari object. Kode ini juga memastikan tidak ada kode yang di commented-out, dan tidak menggunakan comment yang tidak diperlukan. Pada secure coding practices, beberapa variabel menggunakan access modifier private yang memastikan variabel tidak bisa sembarangan untuk diakses. Adapun kesalahan pada kode yang perlu dikoreksi pada tahap exercise 1 ini ialah kurangnya keamanan dalam kode, pada kode ini belum adanya diimplementasikan fitur seperti validasi input, output data encoding, autentikasi, dan otorisasi untuk mengurangi resiko dari keamanan sebuah aplikasi.

## Reflection 2

1. - Setelah menulis unit test, saya merasa kode yang saya buat menjadi jauh lebih menarik dan lebih aman dari bug karena fungsi fungsi yang telah saya buat telah teruji implementasinya, dengan membuat unit test saya jadi lebih pasti tentang apa yang saya buat karena melakukan verifikasi dari bagian-bagian terkecil dalam aplikasi.
   - Unit test yang dibuat akan bergantung terhadap berapa method yang menggunakan logika berpotensi mengalami bug, jika sebuah class, misalnya hanya memiliki getter dan setter saja tanpa logika di dalam methodnya, sebaiknya tidak memerlukan testing.
   - Menurut saya, untuk memastikan unit test cukup untuk memverifikasi program kita adalah kita harus memastikan testing kita sudah mencakup semua bagian yang testable seperti function, method, dan class. Segala hal yang membutuhkan proses logika seharusnya dibuat unit testingnya, membuat testing untuk kondisi negatif seperti error, dan melakukan testing untuk kondisi yang memiliki batasan tertentu.
   - Memiliki code coverage 100% tidak menyimpulkan bahwa kode yang dimiliki sudah bebas dari bug dan error, karena metrik tersebut hanya mengukur cakupan hanya dari baris kode apa saja yang telah dilakukan testing, tetapi kode testing tersebut belum tentu mencakup segala kemungkinan yang terjadi pada logika kode yang dijalankan.


2. Menurut saya itu akan menurunkan kualitas dari clean code, karena akan terjadi banyak pengulangan kode yang akan membuat kode-kode kita akan menjadi berantakan dan akan susah untuk di kelola kedepannya, hal yang mungkin dapat dilakukan untuk memperbaiki potensi masalah clean code adalah memisahkan semua functional test dengan satu class sebagai setup untuk selenium pada localhost dengan class sisanya hanya difokuskan untuk menulis test spesifiknya saja, dengan hal ini kita hanya memerlukan setup satu kali saja dengan melakukan test yang methodnya diambil dari class lainnya. Setelah itu kita juga dapat memisahkan pembuatan instance variable pada class tersendiri, dengan hal ini functional test yang menggunakan instance variable yang sama tidak perlu mengulang penulisan instance variable.