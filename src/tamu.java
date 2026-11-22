 public class Tamu {
    private int idTamu;
    private String nama;
    private String noTelepon;
    private String alamat;

    // Default
    public Tamu() {
        this.idTamu = 1;
        this.nama = "Nama Tamu";
        this.noTelepon = "081234567890";
        this.alamat = "Belum diisi";
    }

    // parameter
    public Tamu(int idTamu, String nama,
               String noTelepon, String alamat) {

        // ID tamu harus positif sebagai identitas tamu.
        if (idTamu <= 0) {
            throw new IllegalArgumentException(
                "ID tamu harus lebih dari 0."
            );
        }

        this.idTamu = idTamu;

        // Validasi data lainnya dilakukan melalui setter.
        setNama(nama);
        setNoTelepon(noTelepon);
        setAlamat(alamat);
    }

    // Getter
    public int getIdTamu() {
        return idTamu;
    }

    public String getNama() {
        return nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    // Validasi Dasar
    private boolean namaValid(String nama) {
        return nama != null && !nama.trim().isEmpty();
    }

    private boolean teleponValid(String noTelepon) {
        return noTelepon != null
                && noTelepon.matches("[0-9]{10,15}");
    }

    private boolean alamatValid(String alamat) {
        return alamat != null && !alamat.trim().isEmpty();
    }

    // Setter Nama
    public void setNama(String nama) {

        // Nama wajib diisi dan tidak boleh hanya spasi.
        if (!namaValid(nama)) {
            throw new IllegalArgumentException(
                "Nama tamu tidak boleh kosong."
            );
        }

        this.nama = nama;
    }

    // Setter Nomor Telepon
    public void setNoTelepon(String noTelepon) {

        // Nomor telepon harus terdiri dari 10 sampai 15 digit angka.
        if (!teleponValid(noTelepon)) {
            throw new IllegalArgumentException(
                "Nomor telepon harus 10-15 digit angka."
            );
        }

        this.noTelepon = noTelepon;
    }

    // Setter Alamat
    public void setAlamat(String alamat) {

        // Alamat wajib diisi dan tidak boleh hanya spasi.
        if (!alamatValid(alamat)) {
            throw new IllegalArgumentException(
                "Alamat tamu tidak boleh kosong."
            );
        }

        this.alamat = alamat;
    }

    // Validasi data 
    public boolean validasiData() {
        return idTamu > 0
                && namaValid(nama)
                && teleponValid(noTelepon)
                && alamatValid(alamat);
    }

    public boolean ubahKontak(String noTelepon) {

        // Try-catch menangani nomor yang tidak sesuai aturan.
        try {
            setNoTelepon(noTelepon);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("----------------------------------");
        System.out.println("           DATA TAMU");
        System.out.println("----------------------------------");
        System.out.println("ID Tamu      : " + idTamu);
        System.out.println("Nama         : " + nama);
        System.out.println("No. Telepon  : " + noTelepon);
        System.out.println("Alamat       : " + alamat);
        System.out.println("----------------------------------");
    }
}

