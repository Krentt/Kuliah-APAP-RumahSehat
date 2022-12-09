import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:rumah_sehat_app/models/dokter.dart';
import 'package:http/http.dart' as http;

class Dokters with ChangeNotifier{

  String? token;

  void updateData(tokenData){
    token = tokenData;
    notifyListeners();
  }

  List<Dokter> _allDokter = [];
  List<Dokter> get allDokter => _allDokter;
  
  Future<void> inisialData() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/dokter/get-all");
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
        var data = json.decode(response.body) as List<dynamic>;
        _allDokter.clear();
        if (data != null){
          data.forEach((element) {
            Dokter dokter = Dokter(
              nama: element["nama"],
              username: element["username"],
              tarif: element["tarifDokter"]
            );
            _allDokter.add(dokter);
          });
        }
      }
    } catch (err){
      rethrow;
    }
  }
}