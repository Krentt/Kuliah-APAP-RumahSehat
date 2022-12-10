import 'dart:collection';
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:jwt_decode/jwt_decode.dart';

import '../models/pasien.dart';

class PasienProvider with ChangeNotifier{
  String? token;

  void updateData(tokenData){
    token = tokenData;
    notifyListeners();
  }

  Pasien _pasienL = Pasien(nama: "nama",
      username: "username",
      email: "email",
      saldo: -1,
      umur: -1);
  List<Pasien> _pasien = [];
  List<Pasien> get getPasien => _pasien; /// GETTER for Pasien data

  final int _saldo = -1;
  int get getSaldo => _saldo; /// GETTER for Saldo

  /// Function Gets Pasien profile
  Future<void> getPasienProfile() async {
    print("getPasienProfile");

    /// JWT Token
    String? finalToken = "Bearer " + token.toString();
    // print(finalToken); ///Sudah bisa
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());

    Uri url = Uri.parse("http://10.0.2.2:8080/pasien/profile"); /// API Endpoint URL


    try{
      var response = await http.get(
        url,
        headers: {
          "Content-Type" : "application/json",
          "Authorization" : finalToken
        },
      );

      if (response.statusCode > 300 || response.statusCode < 200) {
        throw (response.statusCode);
      } else {
        print ("response.body: " + response.body); ///Sudah Bisa
        _pasien.clear();
        // String header = json.decode(response.body)["header"];
        // if (header == "Success"){

          /// read API JSON into data variable list
          Map<String, dynamic> data = json.decode(response.body);
          print("data json decoded: " + data.toString()); ///Sudah bisa

          Pasien pasienData = Pasien(
            nama: payload["NAMA"],
            username: payload["USERNAME"],
            email: payload["EMAIL"],
            saldo: data["saldoPasien"],
            umur: data["umurPasien"],
            kodeAppointment: [] //data["kodeAppointment"]
          );
          print("pasienData" + pasienData.toString());
          _pasien.add(pasienData); /// Data sent to _pasien for Getter
          // notifyListeners();
          print("_pasien" + _pasien.toString());
      }
    } catch (err){
      rethrow;
    }
  }

  /// Function gets pasien saldo
  Future<void> isiSaldo() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/pasien/saldo");

    /// JWT Token
    String? finalToken = "Bearer " + token.toString();
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());

    try{
      var response = await http.get(url,
          headers: {
            "Content-Type" : "application/json",
            "Authorization" : finalToken
          });

      if (response.statusCode > 300 || response.statusCode < 200) {
        throw (response.statusCode);
      } else {
        _pasien.clear();
        String header = json.decode(response.body)["header"];
        if (header == "Success"){

          /// read API JSON into data variable list
          var data = json.decode(response.body) as dynamic;
          // print(data);

          if (data != null){
            /// If Data is List, Iterate over Elements
            for (var element in data) {
              // print(element);

              /// Parses JSON Data into model
              Pasien data = Pasien(
                nama: payload["NAMA"],
                username: payload["USERNAME"],
                email: payload["EMAIL"],
                saldo: element["saldo"],
                umur: element["umur"],
                kodeAppointment: element["kodeAppointment"],
              );
              _pasien.add(data); /// Data sent to _pasien for Getter
              notifyListeners();
            }
          }
        }
      }
    } catch (err){
      rethrow;
    }
  }
}