import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';
import 'package:jwt_decode/jwt_decode.dart';
import 'package:rumah_sehat_app/models/tagihan.dart';

import '../models/appointment.dart';
import '../models/pasien.dart';

class TagihanProvider with ChangeNotifier{
  String? token;

  void updateData(tokenData){
    token = tokenData;
    notifyListeners();
  }

  late Tagihan _tagihan;
  Tagihan get getTagihan => _tagihan; /// GETTER for Tagihan data

  final List<Tagihan> _allTagihan = [];
  List<Tagihan> get getAllTagihan => _allTagihan; /// GETTER for all Tagihan pasien


  /// TODO: Single Tagihan Page
  Future<void> tagihanDetails() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/tagihan/{id}/detail"); /// API Endpoint URL

    /// JWT Token
    String? finalToken = "Bearer " + token.toString();
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());

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
        String header = json.decode(response.body)["header"];
        if (header == "Success"){

          /// JSON Data Sent to API
          Tagihan data = Tagihan(
            ///TODO
          );
          _tagihan = data; /// Data sent to _pasien for Getter
          notifyListeners();
        }
      }
    } catch (err){
      rethrow;
    }
  }

  /// TODO: Tagihan List Page
  Future<void> inisialData() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/tagihan/list");
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
        _allTagihan.clear();

        /// read API JSON into data variable list
        var data = json.decode(response.body) as List<dynamic>;
        print(data);
        if (data != null){
          /// If Data is List, Iterate over Elements
          data.forEach((element) {
            print(element);

            /// Parses JSON Data into model
            Tagihan tagihan = Tagihan(
                id: element["id"],
                tanggalTerbuat: element["tanggalTerbuat"],
                tanggalBayar: element["tanggalBayar"],
                isPaid: element["isPaid"],
                total: element["total"]);

            _allTagihan.add(tagihan); /// Data sent to _allTagihan for Getter
          });
        }
      }
      print(_allTagihan);
    } catch (err){
      rethrow;
    }
  }

  // /// TODO: Change Tagihan to PAID
  // void deleteAppointment(String kode) async {
  //   Uri url = Uri.parse("http://10.0.2.2:8080/appointment/delete/$kode");
  //   String? finalToken = "Bearer " + token.toString();
  //
  //   try {
  //     var response = await http.delete(url,
  //         headers: {
  //           "Content-Type" : "application/json",
  //           "Authorization" : finalToken
  //     });
  //
  //     if (response.statusCode > 300 || response.statusCode < 200) {
  //       throw (response.statusCode);
  //     } else {
  //       // _allTagihan.removeWhere((element) => element.kode == kode);
  //       notifyListeners();
  //     }
  //   } catch (err){
  //     rethrow;
  //   }
  // }
}