import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'package:jwt_decode/jwt_decode.dart';

import '../models/appointment.dart';

class Appointments with ChangeNotifier{
  String? token;

  void updateData(tokenData){
    token = tokenData;
    notifyListeners();
  }

  List<Appointment> _allAppointments = [];
  List<Appointment> get allAppointments => _allAppointments;

  Future<void> addAppointment(String dokter, DateTime waktuAwal) async {
    Uri url = Uri.parse("http://10.0.2.2:8080/appointment/add");
    final f = DateFormat('dd-MM-yyyy hh:mm');
    String? finalToken = "Bearer " + token.toString();
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());

    try{
      final msg = jsonEncode({
        "waktuAwal" : f.format(waktuAwal).toString(),
        "pasienName" : payload["USERNAME"],
        "dokterName" : dokter
      });
      var response = await http.post(
        url,
        headers: {
          "Content-Type" : "application/json",
          "Authorization" : finalToken
        },
        body: msg
      );

      if (response.statusCode > 300 || response.statusCode < 200) {
        throw (response.statusCode);
      } else {
        print(json.decode(response.body)["header"]);
        Appointment data = Appointment(
          kode: json.decode(response.body)["kode"].toString(),
          isDone: false,
          waktuAwal: waktuAwal,
          pasien: payload["USERNAME"],
          dokter: dokter,
        );
        _allAppointments.add(data);
        notifyListeners();
      }
    } catch (err){
      rethrow;
    }
  }
}