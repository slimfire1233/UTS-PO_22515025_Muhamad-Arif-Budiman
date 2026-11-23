

public class Hotel {

    // Atribut
    private String namaHotel;
    private SortedList<Kamar> daftarKamar;
    private SortedList<Tamu> daftarTamu;
    private SortedList<Reservasi> daftarReservasi;
    
    public Hotel() {
        this("Xian Hotel", 10);
    }
    public Hotel(String namaHotel, int kapasitas) {

        if (namaHotel == null || namaHotel.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama hotel tidak boleh kosong."
            );
        }

        if (kapasitas <= 0) {
            throw new IllegalArgumentException(
                "Kapasitas harus lebih dari 0."
            );
        }

        this.namaHotel = namaHotel;

        this.daftarKamar = new SortedList<>(
            kapasitas,
            (k1, k2) -> Integer.compare(
                k1.getNomorKamar(),
                k2.getNomorKamar()
            )
        );

        this.daftarTamu = new SortedList<>(
            kapasitas,
            (t1, t2) -> Integer.compare(
                t1.getIdTamu(),
                t2.getIdTamu()
            )
        );

        this.daftarReservasi = new SortedList<>(
            kapasitas,
            (r1, r2) -> Integer.compare(
                r1.getNomorReservasi(),
                r2.getNomorReservasi()
            )
        );
    }
    
    public boolean tambahKamar(Kamar kamar) {
        try {
            if (kamar == null) {
                System.out.println("Data kamar tidak boleh kosong.");
                return false;
            }

            if (daftarKamar.search(kamar) != -1) {
                System.out.println("Nomor kamar sudah digunakan.");
                return false;
            }

            if (daftarKamar.isFull()) {
                System.out.println("Daftar kamar sudah penuh.");
                return false;
            }

            return daftarKamar.insert(kamar);

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal menambahkan kamar: " + e.getMessage()
            );
            return false;
        }
    }

    
    public boolean tambahTamu(Tamu tamu) {
        try {
            if (tamu == null) {
                System.out.println("Data tamu tidak boleh kosong.");
                return false;
            }

            if (daftarTamu.search(tamu) != -1) {
                System.out.println("ID tamu sudah digunakan.");
                return false;
            }

            if (daftarTamu.isFull()) {
                System.out.println("Daftar tamu sudah penuh.");
                return false;
            }

            return daftarTamu.insert(tamu);

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal menambahkan tamu: " + e.getMessage()
            );
            return false;
        }
    }

 
    public Kamar cariKamar(int nomor) {
        try {
            Kamar kunci = new Kamar(
                nomor, "Standard", 1.0, "Tersedia"
            );

            int indeks = daftarKamar.search(kunci);

            if (indeks == -1) {
                System.out.println("Kamar tidak ditemukan.");
                return null;
            }

            return daftarKamar.get(indeks);

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal mencari kamar: " + e.getMessage()
            );
            return null;
        }
    }

    public boolean buatReservasi(Reservasi reservasi) {
        try {
            if (reservasi == null) {
                System.out.println(
                    "Data reservasi tidak boleh kosong."
                );
                return false;
            }

            if (daftarReservasi.search(reservasi) != -1) {
                System.out.println(
                    "Nomor reservasi sudah digunakan."
                );
                return false;
            }

            
            if (daftarReservasi.isFull()) {
                System.out.println(
                    "Daftar reservasi sudah penuh."
                );
                return false;
            }

          
            Kamar kamar = reservasi.getKamar();

            if (kamar == null) {
                System.out.println(
                    "Kamar reservasi tidak valid."
                );
                return false;
            }

            int indeksKamar = daftarKamar.search(kamar);

            if (indeksKamar == -1 ||
                daftarKamar.get(indeksKamar) != kamar) {

                System.out.println(
                    "Kamar belum terdaftar di hotel."
                );
                return false;
            }

           
            Tamu tamu = reservasi.getTamu();

            if (tamu == null) {
                System.out.println(
                    "Data tamu tidak valid."
                );
                return false;
            }

            int indeksTamu = daftarTamu.search(tamu);

            if (indeksTamu == -1 ||
                daftarTamu.get(indeksTamu) != tamu) {

                System.out.println(
                    "Tamu belum terdaftar di hotel."
                );
                return false;
            }

            
            if (!reservasi.konfirmasi()) {
                System.out.println(
                    "Reservasi gagal dikonfirmasi. " +
                    "Kamar mungkin tidak tersedia atau data tidak valid."
                );
                return false;
            }

          
            if (daftarReservasi.insert(reservasi)) {
                System.out.println(
                    "Reservasi berhasil dibuat."
                );
                return true;
            }

          
            reservasi.batalkan();

            System.out.println(
                "Reservasi gagal dimasukkan ke daftar."
            );
            return false;

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal membuat reservasi: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean batalkanReservasi(int nomor) {
        try {
            for (int i = 0; i < daftarReservasi.size(); i++) {

                Reservasi reservasi = daftarReservasi.get(i);

                if (reservasi.getNomorReservasi() == nomor) {

                    if ("Dibatalkan".equals(
                            reservasi.getStatusReservasi())) {

                        System.out.println(
                            "Reservasi sudah dibatalkan."
                        );
                        return false;
                    }

                    if (!reservasi.batalkan()) {
                        System.out.println(
                            "Reservasi gagal dibatalkan. " +
                            "Periksa status reservasi."
                        );
                        return false;
                    }

                    System.out.println(
                        "Reservasi berhasil dibatalkan. " +
                        "Kamar telah dikembalikan menjadi tersedia."
                    );

                    return true;
                }
            }

            System.out.println(
                "Nomor reservasi tidak ditemukan."
            );
            return false;

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal membatalkan reservasi: " + e.getMessage()
            );
            return false;
        }
    }

    public boolean hapusReservasi(int nomor) {
        try {
            for (int i = 0; i < daftarReservasi.size(); i++) {

                Reservasi reservasi = daftarReservasi.get(i);

                if (reservasi.getNomorReservasi() == nomor) {

                    if (!"Dibatalkan".equals(
                            reservasi.getStatusReservasi())) {

                        if (!reservasi.batalkan()) {
                            System.out.println(
                                "Reservasi tidak dapat dibatalkan."
                            );
                            return false;
                        }
                    }

                    if (daftarReservasi.remove(reservasi)) {
                        System.out.println(
                            "Reservasi berhasil dihapus."
                        );
                        return true;
                    }

                    return false;
                }
            }

            System.out.println(
                "Reservasi tidak ditemukan."
            );
            return false;

        } catch (IllegalArgumentException |
                 IndexOutOfBoundsException |
                 IllegalStateException e) {
            System.out.println(
                "Gagal menghapus reservasi: " + e.getMessage()
            );
            return false;
        }
    }

    public void tampilkanKamar() {
        System.out.println(
            "\n===== DAFTAR KAMAR " + namaHotel + " ====="
        );

        if (daftarKamar.isEmpty()) {
            System.out.println("Belum ada data kamar.");
            return;
        }

        for (int i = 0; i < daftarKamar.size(); i++) {
            try {
                daftarKamar.get(i).tampilkanInfo();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                    "Kesalahan: " + e.getMessage()
                );
            }
        }
    }

    public void tampilkanTamu() {
        System.out.println("\n===== DAFTAR TAMU =====");

        if (daftarTamu.isEmpty()) {
            System.out.println("Belum ada data tamu.");
            return;
        }

        for (int i = 0; i < daftarTamu.size(); i++) {
            try {
                daftarTamu.get(i).tampilkanInfo();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                    "Kesalahan: " + e.getMessage()
                );
            }
        }
    }

    public void tampilkanReservasi() {
        System.out.println("\n===== DAFTAR RESERVASI =====");

        if (daftarReservasi.isEmpty()) {
            System.out.println("Belum ada data reservasi.");
            return;
        }

        for (int i = 0; i < daftarReservasi.size(); i++) {
            try {
                daftarReservasi.get(i).tampilkanInfo();
            } catch (IndexOutOfBoundsException e) {
                System.out.println(
                    "Kesalahan: " + e.getMessage()
                );
            }
        }
    }

    public void tampilkanRingkasan() {

        int kamarTersedia = 0;
        int kamarDibooking = 0;
        int reservasiDikonfirmasi = 0;
        int reservasiDibatalkan = 0;

        double totalPendapatan = 0;

        // Menghitung jumlah kamar
        for (int i = 0; i < daftarKamar.size(); i++) {

            Kamar kamar = daftarKamar.get(i);

            if (kamar.tersedia()) {
                kamarTersedia++;
            } else {
                kamarDibooking++;
            }
        }

        for (int i = 0; i < daftarReservasi.size(); i++) {

            Reservasi reservasi = daftarReservasi.get(i);

            if ("Dikonfirmasi".equals(
                    reservasi.getStatusReservasi())) {

                reservasiDikonfirmasi++;

                totalPendapatan += reservasi.hitungTotal();

            } else if ("Dibatalkan".equals(
                    reservasi.getStatusReservasi())) {

                reservasiDibatalkan++;
            }
        }

        System.out.println(
            "\n===== RINGKASAN " + namaHotel + " ====="
        );

        System.out.println(
            "Jumlah kamar              : " + daftarKamar.size()
        );

        System.out.println(
            "Kamar tersedia            : " + kamarTersedia
        );

        System.out.println(
            "Kamar dibooking           : " + kamarDibooking
        );

        System.out.println(
            "Jumlah tamu               : " + daftarTamu.size()
        );

        System.out.println(
            "Jumlah reservasi          : " + daftarReservasi.size()
        );

        System.out.println(
            "Reservasi dikonfirmasi    : " + reservasiDikonfirmasi
        );

        System.out.println(
            "Reservasi dibatalkan      : " + reservasiDibatalkan
        );

        System.out.println("------------------------------------");

        System.out.printf(
            "Total biaya reservasi     : Rp %.2f%n",
            totalPendapatan
        );

        System.out.println(
            "===================================="
        );
    }
}
