import 'package:flutter/material.dart';

class Tagihan{
  String? id;
  String? tanggalTerbuat;
  String? tanggalBayar;
  bool? isPaid;
  int? total;
  String? kodeAppointment;

  Tagihan({
    @required this.id,
    @required this.tanggalTerbuat,
    @required this.tanggalBayar,
    @required this.isPaid,
    @required this.total,
    this.kodeAppointment
  });

}