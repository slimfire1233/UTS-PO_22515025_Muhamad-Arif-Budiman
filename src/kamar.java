
// Ini class kamar
public class Kamar {

    // Atributnya ini
    private int nomorKamar;
    private String tipeKamar;
    private double harga;
    private String status;

    // constructornya itu harus default
    public Kamar() {
        this.nomorKamar = 1;
        this.tipeKamar = "Standard";
        this.harga = 100000;
        this.status = "Tersedia";
    }

    // Parameternya
    public Kamar(int nomorKamar, String tipeKamar,
                 double harga, String status) {

        // Nomor kamar harus positif sebagai identitas kamar
        if (nomorKamar <= 0) {
            throw new IllegalArgumentException(
                "Nomor kamar harus lebih dari 0."
            );
        }

        // Tipe kamar wajib diisi agar data kamar jelas.
        if (tipeKamar == null || tipeKamar.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Tipe kamar tidak boleh kosong."
            );
        }

        // Nomor dan tipe ditetapkan saat objek dibuat.
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;

        // Harga dan status menggunakan setter agar validasinya terpusat.
        setHarga(harga);
        setStatus(status);
    }

    // Getter
    public int getNomorKamar() {
        return nomorKamar;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public double getHarga() {
        return harga;
    }

    public String getStatus() {
        return status;
    }

    // Setter harga 
    public void setHarga(double harga) {

        // Memastikan harga tidak nol atau bernilai negatif.
        if (harga <= 0) {
            throw new IllegalArgumentException(
                "Harga harus lebih dari 0."
            );
        }

        this.harga = harga;
    }

    // Setter status
    public void setStatus(String status) {

        if (status == null ||
            (!status.equals("Tersedia") &&
             !status.equals("Dibooking") &&
             !status.equals("Terisi"))) {

            throw new IllegalArgumentException(
                "Status kamar tidak valid."
            );
        }

        this.status = status;
    }

    // BEHAVIOR 1 CEK KETERSEDIAAN    
    public boolean tersedia() {
        return status.equals("Tersedia");
    }

    // Behavior 2 ubah status
    public boolean ubahStatus(String status) {

        // Try-catch digunakan agar kesalahan status dapat ditangani
        // tanpa langsung menghentikan program.
        try {
            setStatus(status);
            return true;

        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // menampilkan status
    public void tampilkanInfo() {
        System.out.println("----------------------------------");
        System.out.println("         INFORMASI KAMAR");
        System.out.println("----------------------------------");
        System.out.println("Nomor Kamar : " + nomorKamar);
        System.out.println("Tipe Kamar  : " + tipeKamar);
        System.out.printf("Harga       : Rp%.0f%n", harga);
        System.out.println("Status      : " + status);
        System.out.println("----------------------------------");
    }
}

