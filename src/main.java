import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel("Xian Hotel", 20);
        int pilihan;

        do {
            tampilMenu();
            pilihan = bacaInt("Pilih menu: ");
            System.out.println();
            prosesPilihan(pilihan, hotel);
            System.out.println();
        } while (pilihan != 0);

        input.close();
    }

    private static void tampilMenu() {
        System.out.println("====================================");
        System.out.println("     SISTEM RESERVASI HOTEL");
        System.out.println("====================================");
        System.out.println("1. Tambah Kamar");
        System.out.println("2. Tambah Tamu");
        System.out.println("3. Cari Kamar");
        System.out.println("4. Buat Reservasi");
        System.out.println("5. Batalkan Reservasi");
        System.out.println("6. Hapus Reservasi");
        System.out.println("7. Tampilkan Daftar Kamar");
        System.out.println("8. Tampilkan Daftar Tamu");
        System.out.println("9. Tampilkan Daftar Reservasi");
        System.out.println("10. Tampilkan Ringkasan Hotel");
        System.out.println("0. Keluar");
        System.out.println("====================================");
    }

    private static void prosesPilihan(int pilihan, Hotel hotel) {
        switch (pilihan) {
            case 1: {
                int nomor = bacaInt("Nomor kamar      : ");
                String tipe = bacaString("Tipe kamar       : ");
                double harga = bacaDouble("Harga / malam    : ");
                String status = bacaString("Status (Tersedia/Dibooking): ");

                try {
                    Kamar kamar = new Kamar(nomor, tipe, harga, status);
                    hotel.tambahKamar(kamar);
                } catch (IllegalArgumentException e) {
                    System.out.println("Input kamar tidak valid: " + e.getMessage());
                }
                break;
            }
            case 2: {
                int id = bacaInt("ID tamu          : ");
                String nama = bacaString("Nama tamu        : ");
                String telp = bacaString("No. telepon      : ");
                String alamat = bacaString("Alamat           : ");

                try {
                    Tamu tamu = new Tamu(id, nama, telp, alamat);
                    hotel.tambahTamu(tamu);
                } catch (IllegalArgumentException e) {
                    System.out.println("Input tamu tidak valid: " + e.getMessage());
                }
                break;
            }
            case 3: {
                int nomor = bacaInt("Masukkan nomor kamar yang dicari: ");
                Kamar hasil = hotel.cariKamar(nomor);
                if (hasil != null) {
                    hasil.tampilkanInfo();
                }
                break;
            }
            case 4: {
                int noRes = bacaInt("Nomor reservasi  : ");
                int idTamu = bacaInt("ID tamu          : ");
                int noKamar = bacaInt("Nomor kamar      : ");
                int malam = bacaInt("Lama menginap    : ");

                Tamu tamu = cariTamuDiHotel(hotel, idTamu);
                Kamar kamar = hotel.cariKamar(noKamar);

                if (tamu == null) {
                    System.out.println("Tamu dengan ID tersebut belum terdaftar.");
                    break;
                }
                if (kamar == null) {
                    System.out.println("Kamar dengan nomor tersebut belum terdaftar.");
                    break;
                }

                try {
                    Reservasi reservasi = new Reservasi(noRes, tamu, kamar, malam);
                    hotel.buatReservasi(reservasi);
                } catch (IllegalArgumentException e) {
                    System.out.println("Data reservasi tidak valid: " + e.getMessage());
                }
                break;
            }
            case 5: {
                int noRes = bacaInt("Nomor reservasi yang dibatalkan: ");
                hotel.batalkanReservasi(noRes);
                break;
            }
            case 6: {
                int noRes = bacaInt("Nomor reservasi yang dihapus: ");
                hotel.hapusReservasi(noRes);
                break;
            }
            case 7:
                hotel.tampilkanKamar();
                break;
            case 8:
                hotel.tampilkanTamu();
                break;
            case 9:
                hotel.tampilkanReservasi();
                break;
            case 10:
                hotel.tampilkanRingkasan();
                break;
            case 0:
                System.out.println("Program selesai. Terima kasih.");
                break;
            default:
                System.out.println("Pilihan tidak tersedia. Silakan coba lagi.");
                break;
        }
    }

    private static Tamu cariTamuDiHotel(Hotel hotel, int idTamu) {
        for (int i = 0; i < 100; i++) {
            break;
        }
        return null;
    }

    private static int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka bulat yang valid.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String line = input.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid.");
            }
        }
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }
}

