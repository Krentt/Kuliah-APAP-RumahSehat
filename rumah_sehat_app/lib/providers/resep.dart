import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';

import '../models/obat.dart';

class Reseps with ChangeNotifier{
  String? token;

  String? id;
  String? namaApoteker;
  bool? isDone;
  String? namaDokter;
  String? namaPasien;

  List<Obat> _allObats = [];
  List<Obat> get allObats => _allObats;

  void updateData(tokenData){
    token = tokenData;
    notifyListeners();
  }

  Future<void> inisialData(String kode) async {
    Uri url = Uri.parse("http://10.0.2.2:8080/resep/view/$kode");
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
        _allObats.clear();
        var data = json.decode(response.body);
        print(data);

        id = data["id"].toString();
        namaApoteker = data["namaApoteker"];

        var dataObat = data["listObat"] as List<dynamic>;
        dataObat.forEach((element) {
          Obat obat = new Obat(nama: element["namaObat"], kuantitas: int.parse(element["jumlah"]));
          _allObats.add(obat);
        });
        namaDokter = data["namaDokter"];
        namaPasien = data["namaPasien"];
        isDone = data["done"];

      }

    } catch (err){
      rethrow;
    }
  }
}