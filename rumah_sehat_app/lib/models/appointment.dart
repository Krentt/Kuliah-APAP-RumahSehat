import 'package:flutter/material.dart';

class Appointment{
  String? kode;
  DateTime? waktuAwal;
  bool? isDone;
  String? pasien;
  String? dokter;

  Appointment({
    @required this.kode,
    @required this.waktuAwal,
    @required this.isDone,
    @required this.pasien,
    @required this.dokter
  });

}