import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;
import 'package:jwt_decode/jwt_decode.dart';
import 'package:rumah_sehat_app/models/tagihan.dart';

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


  /// Single Tagihan Page
  Future<void> tagihanDetails() async {
    Uri url = Uri.parse("http://10.0.2.2:8080/tagihan/{id}/detail");

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
        /// read API JSON into data variable list
        Map<String, dynamic> data = json.decode(response.body);
        print("data json decoded: " + data.toString()); ///Sudah bisa


        /// Parse JSON Data Sent by API
        Tagihan tagihanData = Tagihan(
            id: data["id"],
            tanggalTerbuat: data["tanggalTerbuat"],
            tanggalBayar: data["tanggalBayar"],
            isPaid: data["isPaid"],
            total: data["total"]
        );
        _tagihan = tagihanData; /// Data sent to _pasien for Getter
      }
    } catch (err){
      rethrow;
    }
  }

  /// Tagihan List Page
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

  /// Function to Select Tagihan by ID
  Tagihan selectById(String id){
    return _allTagihan.firstWhere((element) => element.id == id);
  }


  /// Function updates tagihan & cuts saldo
  Future<void> bayar(String id) async {
    print("======(LOG) Bayar Tagihan =====");
    // print("Backend Saldo: " + saldo.toString());
    Uri url = Uri.parse("http://10.0.2.2:8080/tagihan/detail");

    /// JWT Token
    String? finalToken = "Bearer " + token.toString();
    Map<String, dynamic> payload = Jwt.parseJwt(token.toString());

    try{
      /// Sent Data (POST)
      final msg = jsonEncode({
        "id" : id
      });
      print(msg.toString());
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
        // print("response.body: " + response.body);

        /// read API JSON into data variable list
        Map<String, dynamic> data = json.decode(response.body);
        // print("data json decoded: " + data.toString());

        Tagihan tagihan = Tagihan(
            id: data["id"],
            tanggalTerbuat: data["tanggalTerbuat"],
            tanggalBayar: data["tanggalBayar"],
            isPaid: data["isPaid"],
            total: data["total"]);

        // _pasien[0] = pasienData;
        notifyListeners();
        // print("Isi Saldo _pasien" + _pasien.toString());
      }
    } catch (err){
      rethrow;
    }
  }
}