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
        String header = json.decode(response.body)["header"];
        if (header == "Success"){
          Appointment data = Appointment(
            kode: json.decode(response.body)["body"]["kode"].toString(),
            isDone: false,
            waktuAwal: waktuAwal,
            pasien: payload["USERNAME"],
            dokter: dokter,
          );
          _allAppointments.add(data);
          notifyListeners();
        } else {
          String userTrouble = json.decode(response.body)["userTrouble"];
          String waktuTabrak = json.decode(response.body)["body"]["waktuAwal"];
          DateTime dateTime = DateTime.parse(waktuTabrak);
          String msg = "[DUARRR!!] " + userTrouble + " memiliki jadwal lain pada waktu " + dateTime.toString();
          throw(msg);
        }
      }
    } catch (err){
      rethrow;
    }
  }

  Future<void> inisialData() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/appointment/pasien-view-all");
    String? finalToken = "Bearer " + token.toString();

    try{
      var response = await http.get(url,
          headers: {
            "Content-Type" : "application/json",
            "Authorization" : finalToken
          });

      if (response.statusCode >= 300 && response.statusCode < 200){
        throw (response.statusCode);
      } else {
        _allAppointments.clear();
        var data = json.decode(response.body) as List<dynamic>;
        print(data);
        if (data != null){
          data.forEach((element) {
            print(element);

            Appointment appointment = Appointment(
                kode: element["kode"],
                waktuAwal: DateFormat("dd-MM-yyyy hh:mm").parse(element["waktuAwal"]),
                dokter: element["dokterName"],
                isDone: element["done"],
                pasien: element["pasienName"]
            );

              _allAppointments.add(appointment);
          });
        }
      }
      print(_allAppointments);
    } catch (err){
      rethrow;
    }
  }

  void deleteAppointment(String kode) async {
    Uri url = Uri.parse("http://10.0.2.2:8080/appointment/delete/$kode");
    String? finalToken = "Bearer " + token.toString();

    try {
      var response = await http.delete(url,
          headers: {
            "Content-Type" : "application/json",
            "Authorization" : finalToken
      });

      if (response.statusCode > 300 || response.statusCode < 200) {
        throw (response.statusCode);
      } else {
        _allAppointments.removeWhere((element) => element.kode == kode);
        notifyListeners();
      }
    } catch (err){
      rethrow;
    }
  }
}