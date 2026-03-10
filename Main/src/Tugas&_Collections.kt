
// TUGAS 2 - COLLECTIONS
// Nama : Reni Sopiani
// NIM  : F1D02310090
// Topik: List, Map, Collection Operations

// DATA CLASS
// data class = class khusus untuk menyimpan data
// Kotlin otomatis buatkan: toString(), equals(), copy()
// val = variabel yang tidak bisa diubah setelah diisi
data class NilaiMahasiswa(
    val nim: String,        // nomor induk mahasiswa
    val nama: String,       // nama lengkap mahasiswa
    val mataKuliah: String, // nama mata kuliah
    val nilai: Int          // nilai akhir (tipe Int = bilangan bulat)
)

// FUNGSI KONVERSI NILAI KE GRADE
// Fungsi ini dipanggil berkali-kali, jadi dibuat terpisah
// agar tidak perlu menulis ulang kode yang sama
// Parameter: nilai (Int) → Return: grade (String)
fun getGrade(nilai: Int): String {
    return when (nilai) {
        in 85..100 -> "A"   // range 85 sampai 100
        in 70..84  -> "B"   // range 70 sampai 84
        in 60..69  -> "C"   // range 60 sampai 69
        in 50..59  -> "D"   // range 50 sampai 59
        else       -> "E"   // semua nilai di bawah 50
    }
}

fun main() {

    // BAGIAN 1: MEMBUAT DATA MAHASISWA
    // listOf() = membuat list yang isinya TIDAK bisa diubah (immutable)
    // Setiap baris adalah 1 objek NilaiMahasiswa
    val mahasiswa = listOf(
        NilaiMahasiswa("2024001", "Reni Sopiani",  "Pemrograman Mobile", 85),
        NilaiMahasiswa("2024002", "Zila Islahul Ummah",    "Pemrograman Mobile", 92),
        NilaiMahasiswa("2024003", "Citra Dewi",    "Pemrograman Mobile", 68),
        NilaiMahasiswa("2024004", "Dani Pratama",  "Pemrograman Mobile", 45),
        NilaiMahasiswa("2024005", "Eka Putri",     "Pemrograman Mobile", 78),
        NilaiMahasiswa("2024006", "Fajar Nugroho", "Pemrograman Mobile", 55),
        NilaiMahasiswa("2024007", "Devi Ramdani",  "Pemrograman Mobile", 70),
        NilaiMahasiswa("2024008", "Hendra Kurnia", "Pemrograman Mobile", 88),
        NilaiMahasiswa("2024009", "Indah Sari",    "Pemrograman Mobile", 62),
        NilaiMahasiswa("2024010", "Joko Pratama",  "Pemrograman Mobile", 49)
    )

    // BAGIAN 2: TAMPILKAN SEMUA DATA
    // forEachIndexed = loop sambil membawa nomor urut (index)
    // i = index mulai dari 0, m = objek NilaiMahasiswa
    // padEnd(18) = tambah spasi di kanan sampai total 18 karakter
    //              supaya tampilan kolom jadi rapi
    println("===== DATA NILAI MAHASISWA =====\n")
    println("No   NIM       Nama              Nilai")
    println("-".repeat(45))     // cetak 45 tanda "-" sebagai garis pemisah

    mahasiswa.forEachIndexed { i, m ->
        println("${i+1}    ${m.nim}  ${m.nama.padEnd(18)}  ${m.nilai}")
        // i+1 karena index mulai dari 0, kita mau tampilkan mulai dari 1
    }

    // BAGIAN 3: STATISTIK NILAI
    // .map { it.nilai }  = ambil hanya nilai dari setiap mahasiswa → jadi List<Int>
    // .average()         = hitung rata-rata dari list angka
    // .maxByOrNull {}    = cari objek dengan nilai terbesar, return null jika list kosong
    // .minByOrNull {}    = cari objek dengan nilai terkecil, return null jika list kosong
    val rataRata  = mahasiswa.map { it.nilai }.average()
    val tertinggi = mahasiswa.maxByOrNull { it.nilai }
    val terendah  = mahasiswa.minByOrNull { it.nilai }

    println("\n===== STATISTIK =====")
    println("Total Mahasiswa : ${mahasiswa.size}")                    // .size = jumlah elemen
    println("Rata-rata Nilai : ${"%.1f".format(rataRata)}")           // %.1f = 1 angka desimal
    println("Nilai Tertinggi : ${tertinggi?.nilai} (${tertinggi?.nama})")
    println("Nilai Terendah  : ${terendah?.nilai} (${terendah?.nama})")
    // tanda ? setelah variabel = safe call, aman jika nilainya null

    // BAGIAN 4: FILTER MAHASISWA LULUS
    // .filter {} = menyaring data yang memenuhi kondisi
    // Hanya mahasiswa dengan nilai >= 70 yang masuk ke list lulus
    println("\n===== MAHASISWA LULUS =====")
    val lulus = mahasiswa.filter { it.nilai >= 70 }

    lulus.forEachIndexed { i, m ->
        println("${i+1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    // BAGIAN 5: FILTER MAHASISWA TIDAK LULUS
    // Sama seperti filter lulus, tapi kondisinya dibalik (< 70)
    println("\n===== MAHASISWA TIDAK LULUS =====")
    val tidakLulus = mahasiswa.filter { it.nilai < 70 }

    tidakLulus.forEachIndexed { i, m ->
        println("${i+1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    // BAGIAN 6: URUTAN ASCENDING (kecil → besar)
    // .sortedBy {} = mengurutkan dari nilai terkecil ke terbesar
    // List asli (mahasiswa) TIDAK berubah, hasilnya list baru
    println("\n===== URUTAN NILAI ASCENDING =====")
    mahasiswa.sortedBy { it.nilai }.forEachIndexed { i, m ->
        println("${i+1}. ${m.nama} - ${m.nilai}")
    }

    // BAGIAN 7: URUTAN DESCENDING (besar → kecil)
    // .sortedByDescending {} = kebalikan dari sortedBy
    println("\n===== URUTAN NILAI DESCENDING =====")
    mahasiswa.sortedByDescending { it.nilai }.forEachIndexed { i, m ->
        println("${i+1}. ${m.nama} - ${m.nilai}")
    }

    // BAGIAN 8: KELOMPOKKAN BERDASARKAN GRADE
    // .groupBy {} = mengelompokkan data berdasarkan hasil fungsi
    // Hasilnya berupa Map: key = grade, value = list mahasiswa
    // Contoh: {"A" -> [Ani, Hendra], "B" -> [Budi, Eka], ...}
    // perGrade[grade] = ambil list mahasiswa untuk grade tertentu
    // ?: emptyList() = jika grade tidak ada datanya, pakai list kosong
    println("\n===== KELOMPOK PER GRADE =====")
    val perGrade = mahasiswa.groupBy { getGrade(it.nilai) }

    listOf("A", "B", "C", "D", "E").forEach { grade ->
        val list = perGrade[grade] ?: emptyList()
        println("Grade $grade: ${list.size} mahasiswa")
        list.forEach { m -> println("   - ${m.nama} (${m.nilai})") }
    }

    // BAGIAN 9: CARI MAHASISWA BERDASARKAN NAMA
    // .contains(keyword, ignoreCase = true) = cek apakah nama
    //   mengandung kata kunci, ignoreCase = tidak peduli huruf besar/kecil
    // Contoh: keyword "budi" akan cocok dengan "Budi Santoso"
    println("\n===== CARI MAHASISWA =====")
    print("Masukkan kata kunci nama: ")
    val keyword = readLine()!!      // baca input dari keyboard

    val hasil = mahasiswa.filter { it.nama.contains(keyword, ignoreCase = true) }

    if (hasil.isEmpty()) {
        println("Mahasiswa tidak ditemukan.")
    } else {
        hasil.forEach { m -> println("- ${m.nama} | ${m.nim} | ${m.nilai} (${getGrade(m.nilai)})") }
    }
}