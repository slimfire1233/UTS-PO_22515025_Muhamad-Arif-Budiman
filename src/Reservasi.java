public class Reservasi {

    // Konstanta status reservasi
    private static final String MENUNGGU = "Menunggu";
    private static final String DIKONFIRMASI = "Dikonfirmasi";
    private static final String DIBATALKAN = "Dibatalkan";

    // Atribut
    private int nomorReservasi;
    private Tamu tamu;
    private Kamar kamar;
    private int lamaMenginap;
    private double totalHarga;
    private String statusReservasi;

    // Constructor default
    public Reservasi() {
        this.nomorReservasi = 0;
        this.tamu = null;
        this.kamar = null;
        this.lamaMenginap = 0;
        this.totalHarga = 0.0;
        this.statusReservasi = MENUNGGU;
    }

    // Constructor berparameter
    public Reservasi(int nomorReservasi, Tamu tamu,
                    Kamar kamar, int lamaMenginap) {
        this();

        if (nomorReservasi <= 0) {
            throw new IllegalArgumentException(
                    "Nomor reservasi harus lebih dari 0.");
        }

        if (tamu == null) {
            throw new IllegalArgumentException(
                    "Data tamu tidak boleh null.");
        }

        if (kamar == null) {
            throw new IllegalArgumentException(
                    "Data kamar tidak boleh null.");
        }

        if (lamaMenginap <= 0) {
            throw new IllegalArgumentException(
                    "Lama menginap harus lebih dari 0.");
        }

        this.nomorReservasi = nomorReservasi;
        this.tamu = tamu;
        this.kamar = kamar;
        this.lamaMenginap = lamaMenginap;

        hitungTotal();
    }

    // Validasi internal data reservasi
    private boolean validasiData() {
        return nomorReservasi > 0
                && tamu != null
                && kamar != null
                && lamaMenginap > 0
                && totalHarga > 0;
    }

    // Menghitung total harga reservasi
    public double hitungTotal() {
        if (kamar != null && lamaMenginap > 0) {
            totalHarga = kamar.getHarga() * lamaMenginap;
        } else {
            totalHarga = 0.0;
        }

        return totalHarga;
    }

    // Mengonfirmasi reservasi
    public boolean konfirmasi() {
        if (!validasiData()) {
            return false;
        }

        if (!MENUNGGU.equals(statusReservasi)) {
            return false;
        }

        if (!kamar.tersedia()) {
            return false;
        }

        if (kamar.ubahStatus("Dibooking")) {
            statusReservasi = DIKONFIRMASI;
            return true;
        }

        return false;
    }

    // Membatalkan reservasi
    public boolean batalkan() {
        if (DIBATALKAN.equals(statusReservasi)) {
            return false;
        }

        if (DIKONFIRMASI.equals(statusReservasi)) {
            if (!kamar.ubahStatus("Tersedia")) {
                return false;
            }
        }

        statusReservasi = DIBATALKAN;
        return true;
    }

    // Menampilkan informasi reservasi
    public void tampilkanInfo() {
        System.out.println("=== INFORMASI RESERVASI ===");
        System.out.println("Nomor Reservasi : " + nomorReservasi);

        if (tamu != null) {
            System.out.println("Nama Tamu       : " + tamu.getNama());
        }

        if (kamar != null) {
            System.out.println("Nomor Kamar     : " + kamar.getNomorKamar());
        }

        System.out.println("Lama Menginap   : " + lamaMenginap + " malam");
        System.out.println("Status Reservasi: " + statusReservasi);

        System.out.printf("Total Harga     : Rp%,.0f%n", totalHarga);
    }

    // Getter
    public int getNomorReservasi() {
        return nomorReservasi;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public String getStatusReservasi() {
        return statusReservasi;
    }
}


