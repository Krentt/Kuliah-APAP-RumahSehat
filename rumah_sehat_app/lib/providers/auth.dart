import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Authh with ChangeNotifier{
  String? _idToken;

  String? _tempidToken;

  void tempData(){
    _idToken = _tempidToken;
    notifyListeners();
  }

  bool get isAuth{
    return token != null;
  }

  String? get token{
    return _idToken;
  }


  Future<void> signup(String? username, String? password, String? email, String? umur, String? nama) async{
    Uri url = Uri.parse("https://apap-057.cs.ui.ac.id/signup");

    try{
      if (username == null || password == null || email == null || umur == null || nama == null){
        throw("Data diri tidak boleh kosong!");
      }

      var response = await http.post(url,
          headers: {
        "Content-Type" : "application/json"
      },
          body: json.encode({
          "username": username,
          "nama": nama,
          "password": password,
          "email": email,
          "umurPasien" : umur
      }));
      var resp = json.decode(response.body);

      if (resp["error"] != null){
        throw resp["error"];
      }

      _tempidToken = resp["jwttoken"];
      notifyListeners();

    } catch (error){
      rethrow;
    }
  }

  Future<void> login(String? username, String? password) async{
    Uri url = Uri.parse("https://apap-057.cs.ui.ac.id/authenticate");

    try{
      var response = await http.post(url,
          headers: {
            "Content-Type" : "application/json"
          },
          body: json.encode({
            "username": username,
            "password": password,
          }));
      var resp = json.decode(response.body);

      if (resp["error"] != null){
        throw resp["error"];
      }

      _tempidToken = resp["jwttoken"];
      notifyListeners();

    } catch (error) {
      rethrow;
    }
  }


  void logout(){
    _idToken = null;
    notifyListeners();
  }
}