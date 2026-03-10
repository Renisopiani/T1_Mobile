// ================================
// TUGAS 1 - SISTEM PENILAIAN
// Nama : Reni Sopiani
// NIM  : F1D02310090
// Topik: Control Flow (if, when), Range
// ================================

fun main() {

    // Menampilkan judul program
    println("===== SISTEM PENILAIAN =====\n")

    // ----------------------------------------
    // BAGIAN 1: INPUT DATA DARI USER
    // readLine() = membaca input teks dari keyboard
    // !! = memastikan nilai tidak null (tidak kosong)
    // ----------------------------------------

    print("Masukkan Nama Mahasiswa: ")
    val nama = readLine()!!             // menyimpan nama sebagai String

    print("Masukkan Nilai UTS (0-100): ")
    val uts = readLine()!!.toDouble()   // toDouble() = mengubah teks menjadi angka desimal

    print("Masukkan Nilai UAS (0-100): ")
    val uas = readLine()!!.toDouble()

    print("Masukkan Nilai Tugas (0-100): ")
    val tugas = readLine()!!.toDouble()

    // ----------------------------------------
    // BAGIAN 2: MENGHITUNG NILAI AKHIR
    // Rumus: (UTS x 30%) + (UAS x 40%) + (Tugas x 30%)
    // Contoh: UTS=75, UAS=80, Tugas=85
    //   = (75 * 0.3) + (80 * 0.4) + (85 * 0.3)
    //   = 22.5 + 32.0 + 25.5
    //   = 80.0
    // ----------------------------------------

    val nilaiAkhir = (uts * 0.3) + (uas * 0.4) + (tugas * 0.3)

    // ----------------------------------------
    // BAGIAN 3: KONVERSI NILAI KE GRADE (HURUF)
    // when = seperti switch-case, memilih kondisi yang cocok
    // in 85..100 = range / rentang nilai antara 85 sampai 100
    // toInt() = mengubah desimal ke bulat agar bisa dicek dengan range
    // ----------------------------------------

    val grade = when (nilaiAkhir.toInt()) {
        in 85..100 -> "A"   // nilai 85 sampai 100 = A
        in 70..84  -> "B"   // nilai 70 sampai 84  = B
        in 60..69  -> "C"   // nilai 60 sampai 69  = C
        in 50..59  -> "D"   // nilai 50 sampai 59  = D
        else       -> "E"   // nilai di bawah 50   = E
    }

    // ----------------------------------------
    // BAGIAN 4: KETERANGAN BERDASARKAN GRADE
    // when di sini mencocokkan nilai String grade (A/B/C/D/E)
    // lalu menghasilkan keterangan yang sesuai
    // ----------------------------------------

    val keterangan = when (grade) {
        "A"  -> "Sangat Baik"
        "B"  -> "Baik"
        "C"  -> "Cukup"
        "D"  -> "Kurang"
        else -> "Sangat Kurang"   // else menangkap grade "E"
    }

    // ----------------------------------------
    // BAGIAN 5: CEK STATUS KELULUSAN
    // Menggunakan if-else satu baris (disebut expression)
    // Jika nilaiAkhir >= 60 maka "LULUS", selain itu "TIDAK LULUS"
    // ----------------------------------------

    val status = if (nilaiAkhir >= 60) "LULUS" else "TIDAK LULUS"

    // ----------------------------------------
    // BAGIAN 6: MENAMPILKAN HASIL PENILAIAN
    // $variabel = string template, menyisipkan nilai variabel ke teks
    // Contoh: "Nama : $nama" → "Nama : Reni Sopiani"
    // ----------------------------------------

    println("\n===== HASIL PENILAIAN =====")
    println("Nama        : $nama")
    println("Nilai UTS   : $uts (Bobot 30%)")
    println("Nilai UAS   : $uas (Bobot 40%)")
    println("Nilai Tugas : $tugas (Bobot 30%)")
    println("-----------------------------")
    println("Nilai Akhir : $nilaiAkhir")
    println("Grade       : $grade")
    println("Keterangan  : $keterangan")
    println("Status      : $status")

    // ----------------------------------------
    // BAGIAN 7: PESAN AKHIR BERDASARKAN STATUS
    // if-else blok biasa untuk menampilkan pesan berbeda
    // sesuai hasil kelulusan mahasiswa
    // ----------------------------------------

    if (nilaiAkhir >= 60) {
        println("\nSelamat! Anda dinyatakan LULUS.")
    } else {
        println("\nMaaf, Anda dinyatakan TIDAK LULUS.")
    }
}