

class Pasien{
  String nama;
  String username;
  String email;
  int saldo;
  int umur;
  List<String>? kodeAppointment;

  Pasien({
    required this.nama,
    required this.username,
    required this.email,
    required this.saldo,
    required this.umur,
    this.kodeAppointment
  });

}